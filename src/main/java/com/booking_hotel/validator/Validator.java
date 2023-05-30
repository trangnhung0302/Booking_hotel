package com.booking_hotel.validator;
import java.time.format.DateTimeFormatter;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Hashtable;
import java.util.regex.*;

public class Validator {
  public Hashtable<String, String> checkRequire(String attrKey, String value, String attrName, Hashtable<String, String> errors) {
    String message = "Vui lòng nhập " + attrName + "!";
    if (value == null || value.isEmpty()) {
      errors.put(attrKey, message);
    }
    return errors;
  }

  public Hashtable<String, String> checkPeriodTime(String attrKey, String firstTime, String lastTime, Hashtable<String, String> errors) {
    String message = "Thời gian trả phòng phải lớn hơn thời gian nhận phòng!";
    if (firstTime.compareTo(lastTime) >= 0) {
      errors.put(attrKey, message);
    }
    return errors;
  }

  public Hashtable<String, String> checkCurrentTime(String attrKey, String value, String attrName, Hashtable<String, String> errors) throws ParseException {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm");
    LocalDateTime now = LocalDateTime.now();
    String message = attrName + " phải lớn hơn thời gian hiện tại!";
    if (value.replace("T", " ").compareTo(dtf.format(now)) < 0) {
      errors.put(attrKey, message);
    }
    return errors;
  }

  public Hashtable<String, String> checkEmail(String attrKey, String email, Hashtable<String, String> errors) {
    String message = "Vui lòng nhập đúng định dạng Email!";
    String pattern = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    Matcher matcher = Pattern.compile(pattern).matcher(email);
    if (!matcher.matches()) {
      errors.put(attrKey, message);
    }
    return errors;
  }

  public Hashtable<String, String> checkMaxLength(String attrKey, String value, Integer max, String attrName, Hashtable<String, String> errors) {
    String message = attrName + " tối đa " + max.toString() + " kí tự!";
    if (!value.isBlank() && value.length() > max) {
      errors.put(attrKey, message);
    }
    return errors;
  }

  public Hashtable<String, String> checkMinLength(String attrKey, String value, Integer min, String attrName, Hashtable<String, String> errors) {
    String message = attrName + " tối thiểu " + min.toString() + " kí tự!";
    if (!value.isBlank() && value.length() < min) {
      errors.put(attrKey, message);
    }
    return errors;
  }

  public Hashtable<String, String> checkPhone(String attrKey, String tel, Hashtable<String, String> errors) {
    String message = "Số điện thoại là chuỗi có 10 chữ số!";
    String pattern = "^\\d{10}";
    Matcher matcher = Pattern.compile(pattern).matcher(tel);
    if (!tel.isBlank() && !matcher.matches()) {
      errors.put(attrKey, message);
    }
    return errors;
  }

  public Hashtable<String, String> checkUniformityPassword(String attrKey, String password, String rePassword, Hashtable<String, String> errors) {
    String message = "Mật khẩu xác nhận không đúng!";
    if (!password.equals(rePassword)) {
      errors.put(attrKey, message);
    }
    return errors;
  }

  public Hashtable<String, String> checkNumber(String attrKey, String value, String attrName, Hashtable<String, String> errors) {
    String message = attrName + " là số nguyên lớn hơn 0!";
    String pattern = "^?\\d+$";
    Matcher matcher = Pattern.compile(pattern).matcher(value);
    if (!value.isBlank() && (!matcher.matches() || Integer.parseInt(value) <= 0)) {
      errors.put(attrKey, message);
    }
    return errors;
  }

  public Hashtable<String, String> checkNumberHasZezo(String attrKey, String value, String attrName, Hashtable<String, String> errors) {
    String message = attrName + " là số nguyên lớn hơn hoặc bằng 0!";
    String pattern = "^?\\d+$";
    Matcher matcher = Pattern.compile(pattern).matcher(value);
    if (!value.isBlank() && !matcher.matches()) {
      errors.put(attrKey, message);
    }
    return errors;
  }
}
