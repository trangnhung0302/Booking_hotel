<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
		<!DOCTYPE html>
		<html>

		<head>
			<meta charset="UTF-8">
			<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
			<link rel="stylesheet" href="/css/custom.css">
		</head>

		<body>
			<div class="w3-top">
				<div class="w3-bar w3-white w3-padding w3-card" style="letter-spacing:4px;">
					<a href="#home" class="w3-bar-item w3-button">ROYAL HOTEL</a>
					<!-- Right-sided navbar links. Hide them on small screens -->
					<div class="w3-right w3-hide-small">
						<a href="/" class="w3-bar-item w3-button">Trang chủ</a>
						<a href="/booking" class="w3-bar-item w3-button">Đặt phòng</a>
						<security:authorize access="isAuthenticated()">
							<div class="w3-dropdown-hover">
								<a class="w3-bar-item w3-button" href="#">Cá nhân </a>
								<div class="w3-dropdown-content w3-bar-block w3-border">
									<a href="/profile" class="w3-bar-item w3-button">Thông tin cá nhân</a>
									<a href="/history" class="w3-bar-item w3-button">Lịch sử</a>
									<a href="/logout" class="w3-bar-item w3-button">Đăng xuất</a>
								</div>
							</div>
						</security:authorize>
						<security:authorize access="!isAuthenticated()">
							<a href="/login" class="w3-bar-item w3-button">Đăng nhập</a>
						</security:authorize>
					</div>
				</div>
			</div>
		</body>

		</html>