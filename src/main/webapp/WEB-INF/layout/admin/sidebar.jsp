<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
  <link rel="stylesheet" href="/css/admin/custom.css">
  <link rel="stylesheet" href="/css/admin/template.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>

<body>
  <div id="sidebar">
    <header>
      <a href="#">Booking</a>
    </header>
    <ul class="nav">
      <li>
        <a href="/admin/reservations">
          <i class="fa fa-calendar" aria-hidden="true"></i> Quản lý đặt phòng
        </a>
      </li>
      <li>
        <a href="/admin/customers">
          <i class="fa fa-user-o" aria-hidden="true"></i> Khách hàng
        </a>
      </li>
      <li>
        <a href="/admin/room_categories">
          <i class="fa fa-calendar-plus-o" aria-hidden="true"></i> Loại phòng
        </a>
      </li>
      <li>
        <a href="/admin/services">
          <i class="fa fa-server" aria-hidden="true"></i> Dịch vụ
        </a>
      </li>
    </ul>
  </div>
</body>
</html>