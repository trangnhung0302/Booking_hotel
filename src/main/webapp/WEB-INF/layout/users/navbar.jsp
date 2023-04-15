<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="css/custom.css">
</head>
<body>
	<div class="w3-top">
	  <div class="w3-bar w3-white w3-padding w3-card" style="letter-spacing:4px;">
	    <a href="#home" class="w3-bar-item w3-button">LTWEB8 HOTEL</a>
	    <!-- Right-sided navbar links. Hide them on small screens -->
	    <div class="w3-right w3-hide-small">
	      <a href="/" class="w3-bar-item w3-button">TRANG CHỦ</a>
	      <a href="/booking" class="w3-bar-item w3-button">BOOKING</a>
				<security:authorize access="isAuthenticated()">
					<a href="/logout" class="w3-bar-item w3-button">LOGOUT</a>
				</security:authorize>
				<security:authorize access="!isAuthenticated()">
					<a href="/login" class="w3-bar-item w3-button">LOGIN</a>
				</security:authorize>
	    </div>
	  </div>
	</div>
</body>
</html>