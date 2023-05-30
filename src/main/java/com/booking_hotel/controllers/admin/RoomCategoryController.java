package com.booking_hotel.controllers.admin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.booking_hotel.controllers.ApplicationController;
import com.booking_hotel.model.RoomCategory;
import com.booking_hotel.repositories.RoomCategoryRepository;
import com.booking_hotel.validator.Validator;

@Controller
public class RoomCategoryController extends ApplicationController {

  public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/images/database";

  @Autowired
  private RoomCategoryRepository roomCategoryRepository;

  @GetMapping("/admin/room_categories")
  public String index(Model model, Authentication authentication, HttpServletRequest request) {
    if (!isAdmin(authentication)) {
      return "notFound";
    }
    model.addAttribute("name", request.getParameter("name"));
    model.addAttribute("numberOfPeople", request.getParameter("numberOfPeople"));
    model.addAttribute("status", request.getParameter("status"));
    Integer typeSearch = 1;
    List<RoomCategory> roomCategories = new ArrayList<>();
    Integer numberOfPeople = 0;
    Integer status = 0;
    String name = "";
    if (request.getParameter("name") != null) {
      name = request.getParameter("name");
    }
    if (request.getParameter("numberOfPeople") != null && !request.getParameter("numberOfPeople").isBlank()) {
      numberOfPeople = Integer.parseInt(request.getParameter("numberOfPeople"));
      typeSearch += 10;
    }
    if (request.getParameter("status") != null && !request.getParameter("status").isBlank()) {
      status = Integer.parseInt(request.getParameter("status"));
      typeSearch += 100;
    }
    switch (typeSearch) {
      case 1:
        roomCategories = roomCategoryRepository.findByNameContaining(name);
        break;
      case 11:
        roomCategories = roomCategoryRepository
            .findByNameContainingAndMaxNumberOfPeople(name, numberOfPeople);
        break;
      case 101:
        roomCategories = roomCategoryRepository.findByNameContainingAndStatus(name, status);
        break;
      case 111:
        roomCategories = roomCategoryRepository
            .findByNameContainingAndMaxNumberOfPeopleAndStatus(name, numberOfPeople, status);
        break;
    }

    model.addAttribute("roomCategories", roomCategories);
    return "admin/roomCategories/index";
  }

  @GetMapping("/admin/room_categories/new")
  public String newRoomCategory(Authentication authentication) {
    if (!isAdmin(authentication)) {
      return "notFound";
    }
    return "admin/roomCategories/new";
  }

  @PostMapping("/admin/room_categories")
  public String create(HttpServletRequest request, HttpServletResponse response,
      @RequestParam("image") MultipartFile file, Model model, RedirectAttributes redirAttrs)
      throws ServletException, IOException, ParseException {

    Hashtable<String, String> errors = this.validateParams(request, file.getOriginalFilename());
    if (errors.size() > 0) {
      this.saveParams(model, request);
      model.addAttribute("errors", errors);
      return "/admin/roomCategories/new";
    }

    StringBuilder fileNames = new StringBuilder();
    Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
    fileNames.append(file.getOriginalFilename());
    Files.write(fileNameAndPath, file.getBytes());

    RoomCategory roomCategory = new RoomCategory();
    roomCategory = this.setData(roomCategory, request);
    String imageUrl = "/images/database/" + file.getOriginalFilename();
    roomCategory.setImageUrl(imageUrl);
    roomCategoryRepository.save(roomCategory);

    redirAttrs.addFlashAttribute("message", "Tạo mới thành công");
    return "redirect:../admin/room_categories";
  }

  @GetMapping("/admin/room_categories/{id}")
  public String show(@PathVariable Integer id, Model model, Authentication authentication) {
    if (!isAdmin(authentication)) {
      return "notFound";
    }
    RoomCategory roomCategory = roomCategoryRepository.findById(id).get();
    this.saveParamsEdit(model, roomCategory);
    return "admin/roomCategories/edit";
  }

  @PostMapping("/admin/room_categories/{id}")
  public String update(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response,
      @RequestParam("image") MultipartFile file, Model model, RedirectAttributes redirAttrs)
      throws ServletException, IOException, ParseException {

    Hashtable<String, String> errors = this.validateParams(request, "fileNameDefault");
    RoomCategory roomCategory = roomCategoryRepository.findById(id).get();
    if (errors.size() > 0) {
      this.saveParams(model, request);
      model.addAttribute("errors", errors);
      model.addAttribute("imageUrl", roomCategory.getImageUrl());
      return "/admin/roomCategories/edit";
    }

    if (!file.getOriginalFilename().isBlank()) {
      StringBuilder fileNames = new StringBuilder();
      Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
      fileNames.append(file.getOriginalFilename());
      Files.write(fileNameAndPath, file.getBytes());
      String imageUrl = "/images/database/" + file.getOriginalFilename();
      roomCategory.setImageUrl(imageUrl);
    }

    roomCategory = this.setData(roomCategory, request);
    roomCategoryRepository.save(roomCategory);

    redirAttrs.addFlashAttribute("message", "Chỉnh sửa thành công");

    return "redirect:../../admin/room_categories/" + id;
  }

  private Hashtable<String, String> validateParams(HttpServletRequest request, String fileNames) {
    Hashtable<String, String> errors = new Hashtable<String, String>();
    Validator validator = new Validator();

    errors = validator.checkRequire("image", fileNames, "Hình ảnh", errors);

    errors = validator.checkRequire("name", request.getParameter("name"), "Tên loại phòng", errors);
    errors = validator.checkMaxLength("name", request.getParameter("name"), 255, "Tên loại phòng", errors);

    errors = validator.checkRequire("maxNumberOfPeople", request.getParameter("maxNumberOfPeople"), "Số người", errors);
    errors = validator.checkNumber("maxNumberOfPeople", request.getParameter("maxNumberOfPeople"), "Số người", errors);
    errors = validator.checkMaxLength("maxNumberOfPeople", request.getParameter("maxNumberOfPeople"), 3, "Số người",
        errors);

    errors = validator.checkRequire("priceOfday", request.getParameter("priceOfday"), "Giá theo ngày", errors);
    errors = validator.checkNumber("priceOfday", request.getParameter("priceOfday"), "Giá theo ngày", errors);
    errors = validator.checkMaxLength("priceOfday", request.getParameter("priceOfday"), 9, "Giá theo ngày", errors);

    errors = validator.checkRequire("priceOvernight", request.getParameter("priceOvernight"), "Giá qua đêm", errors);
    errors = validator.checkNumber("priceOvernight", request.getParameter("priceOvernight"), "Giá qua đêm", errors);
    errors = validator.checkMaxLength("priceOvernight", request.getParameter("priceOvernight"), 9, "Giá qua đêm",
        errors);

    errors = validator.checkRequire("priceOfHour", request.getParameter("priceOfHour"), "Giá theo giờ", errors);
    errors = validator.checkNumber("priceOfHour", request.getParameter("priceOfHour"), "Giá theo giờ", errors);
    errors = validator.checkMaxLength("priceOfHour", request.getParameter("priceOfHour"), 9, "Giá theo giờ", errors);

    errors = validator.checkRequire("status", request.getParameter("status"), "trạng thái", errors);

    errors = validator.checkMaxLength("remark", request.getParameter("remark"), 3000, "Ghi chú", errors);

    return errors;
  }

  private RoomCategory setData(RoomCategory roomCategory, HttpServletRequest request) {
    roomCategory.setName(request.getParameter("name"));
    roomCategory.setMaxNumberOfPeople(Integer.parseInt(request.getParameter("maxNumberOfPeople")));
    roomCategory.setPriceOfDay(Integer.parseInt(request.getParameter("priceOfday")));
    roomCategory.setPriceOvernight(Integer.parseInt(request.getParameter("priceOvernight")));
    roomCategory.setPriceOfHour(Integer.parseInt(request.getParameter("priceOfHour")));
    roomCategory.setRemark(request.getParameter("remark"));
    roomCategory.setStatus(Integer.parseInt(request.getParameter("status")));
    return roomCategory;
  }

  private Model saveParams(Model model, HttpServletRequest request) {
    model.addAttribute("name", request.getParameter("name"));
    model.addAttribute("maxNumberOfPeople", request.getParameter("maxNumberOfPeople"));
    model.addAttribute("priceOfday", request.getParameter("priceOfday"));
    model.addAttribute("priceOvernight", request.getParameter("priceOvernight"));
    model.addAttribute("priceOfHour", request.getParameter("priceOfHour"));
    model.addAttribute("remark", request.getParameter("remark"));
    model.addAttribute("status", request.getParameter("status"));
    return model;
  }

  private Model saveParamsEdit(Model model, RoomCategory roomCategory) {
    model.addAttribute("imageUrl", roomCategory.getImageUrl());
    model.addAttribute("name", roomCategory.getName());
    model.addAttribute("maxNumberOfPeople", roomCategory.getMaxNumberOfPeople());
    model.addAttribute("priceOfday", roomCategory.getPriceOfDay());
    model.addAttribute("priceOvernight", roomCategory.getPriceOvernight());
    model.addAttribute("priceOfHour", roomCategory.getPriceOfHour());
    model.addAttribute("remark", roomCategory.getRemark());
    model.addAttribute("status", roomCategory.getStatus());
    return model;
  }

  // public static Specification<RoomCategory> findUsers(String name, String
  // numberOfPeople, String Status) {
  // return new Specification<RoomCategory>() {
  // @Override
  // public Predicate toPredicate(Root<RoomCategory> root, CriteriaQuery<?> query,
  // CriteriaBuilder cb) {
  // List<Predicate> predicates = new ArrayList<>();

  // if (name != null) {
  // predicates.add(cb.equal(root.get("name"), name));
  // }

  // predicates.add(cb.greaterThan(root.get("age"), age));

  // return cb.and(predicates.toArray(new Predicate[predicates.size()]));
  // }
  // };
  // }
}
