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
import org.springframework.data.domain.Sort;
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
import com.booking_hotel.model.Service;
import com.booking_hotel.repositories.ServiceRepository;
import com.booking_hotel.validator.Validator;

@Controller
public class ServiceController extends ApplicationController {

  public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/images/database";

  @Autowired
  private ServiceRepository serviceRepository;

  @GetMapping("/admin/services")
  public String index(Model model, Authentication authentication, HttpServletRequest request) {
    if (!isAdmin(authentication)) {
      return "notFound";
    }
    model.addAttribute("name", request.getParameter("name"));
    model.addAttribute("minPrice", request.getParameter("minPrice"));
    model.addAttribute("maxPrice", request.getParameter("maxPrice"));
    model.addAttribute("status", request.getParameter("status"));

    List<Service> services = new ArrayList<>();
    Integer minPrice = 0;
    Integer maxPrice = 999999999;
    Integer status = 0;
    String name = "";
    if (request.getParameter("name") != null) {
      name = request.getParameter("name");
    }
    if (request.getParameter("minPrice") != null && !request.getParameter("minPrice").isBlank()) {
      minPrice = Integer.parseInt(request.getParameter("minPrice"));
    }
    if (request.getParameter("maxPrice") != null && !request.getParameter("maxPrice").isBlank()) {
      maxPrice = Integer.parseInt(request.getParameter("maxPrice"));
    }
    if (request.getParameter("status") != null && !request.getParameter("status").isBlank()) {
      status = Integer.parseInt(request.getParameter("status"));
      services = serviceRepository
          .findByNameContainingAndPriceGreaterThanEqualAndPriceLessThanEqualAndStatus(name,
              minPrice, maxPrice, status);
    } else {
      services = serviceRepository.findByNameContainingAndPriceGreaterThanEqualAndPriceLessThanEqual(name,
          minPrice, maxPrice);
    }
    model.addAttribute("services", services);
    return "admin/services/index";
  }

  @GetMapping("/admin/services/new")
  public String newService(Authentication authentication) {
    if (!isAdmin(authentication)) {
      return "notFound";
    }
    return "admin/services/new";
  }

  @PostMapping("/admin/services")
  public String create(HttpServletRequest request, HttpServletResponse response,
      @RequestParam("image") MultipartFile file, Model model, RedirectAttributes redirAttrs)
      throws ServletException, IOException, ParseException {

    Hashtable<String, String> errors = this.validateParams(request, file.getOriginalFilename());
    if (errors.size() > 0) {
      this.saveParams(model, request);
      model.addAttribute("errors", errors);
      return "/admin/services/new";
    }

    StringBuilder fileNames = new StringBuilder();
    Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
    fileNames.append(file.getOriginalFilename());
    Files.write(fileNameAndPath, file.getBytes());

    Service service = new Service();
    service = this.setData(service, request);
    String imageUrl = "/images/database/" + file.getOriginalFilename();
    service.setImageUrl(imageUrl);
    serviceRepository.save(service);

    redirAttrs.addFlashAttribute("message", "Tạo mới thành công");
    return "redirect:../admin/services";
  }

  @GetMapping("/admin/services/{id}")
  public String show(@PathVariable Integer id, Model model, Authentication authentication) {
    if (!isAdmin(authentication)) {
      return "notFound";
    }
    Service service = serviceRepository.findById(id).get();
    this.saveParamsEdit(model, service);
    return "admin/services/edit";
  }

  @PostMapping("/admin/services/{id}")
  public String update(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response,
      @RequestParam("image") MultipartFile file, Model model, RedirectAttributes redirAttrs)
      throws ServletException, IOException, ParseException {

    Hashtable<String, String> errors = this.validateParams(request, "fileNameDefault");
    Service service = serviceRepository.findById(id).get();
    if (errors.size() > 0) {
      this.saveParams(model, request);
      model.addAttribute("errors", errors);
      model.addAttribute("imageUrl", service.getImageUrl());
      return "/admin/services/edit";
    }

    if (!file.getOriginalFilename().isBlank()) {
      StringBuilder fileNames = new StringBuilder();
      Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
      fileNames.append(file.getOriginalFilename());
      Files.write(fileNameAndPath, file.getBytes());
      String imageUrl = "/images/database/" + file.getOriginalFilename();
      service.setImageUrl(imageUrl);
    }

    service = this.setData(service, request);
    serviceRepository.save(service);

    redirAttrs.addFlashAttribute("message", "Chỉnh sửa thành công");

    return "redirect:../../admin/services/" + id;
  }

  private Hashtable<String, String> validateParams(HttpServletRequest request, String fileNames) {
    Hashtable<String, String> errors = new Hashtable<String, String>();
    Validator validator = new Validator();

    errors = validator.checkRequire("image", fileNames, "Hình ảnh", errors);

    errors = validator.checkRequire("name", request.getParameter("name"), "tên dịch vụ", errors);
    errors = validator.checkMaxLength("name", request.getParameter("name"), 255, "Tên dịch vụ", errors);

    errors = validator.checkRequire("price", request.getParameter("price"), "giá", errors);
    errors = validator.checkNumber("price", request.getParameter("price"), "Giá", errors);
    errors = validator.checkMaxLength("price", request.getParameter("price"), 9, "Giá", errors);

    errors = validator.checkRequire("status", request.getParameter("status"), "trạng thái", errors);

    errors = validator.checkMaxLength("remark", request.getParameter("remark"), 3000, "Ghi chú", errors);

    return errors;
  }

  private Service setData(Service service, HttpServletRequest request) {
    service.setName(request.getParameter("name"));
    service.setPrice(Integer.parseInt(request.getParameter("price")));
    service.setRemark(request.getParameter("remark"));
    service.setStatus(Integer.parseInt(request.getParameter("status")));
    return service;
  }

  private Model saveParams(Model model, HttpServletRequest request) {
    model.addAttribute("name", request.getParameter("name"));
    model.addAttribute("price", request.getParameter("price"));
    model.addAttribute("remark", request.getParameter("remark"));
    model.addAttribute("status", request.getParameter("status"));
    return model;
  }

  private Model saveParamsEdit(Model model, Service service) {
    model.addAttribute("imageUrl", service.getImageUrl());
    model.addAttribute("name", service.getName());
    model.addAttribute("price", service.getPrice());
    model.addAttribute("remark", service.getRemark());
    model.addAttribute("status", service.getStatus());
    return model;
  }
}
