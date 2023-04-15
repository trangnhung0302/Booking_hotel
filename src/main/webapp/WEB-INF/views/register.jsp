<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Đăng ký</title>
		<link rel="stylesheet" href="css/login.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	</head>

	<body>
		<jsp:include page="../layout/users/navbar.jsp" />
		<div class="container">
			<div class="w100pt">
				<form id="login_form" class="form_class" action="/j_spring_security_check" method="post">
					<div class="form_div">
						<label>Name:</label>
						<input type="text" name="name" class="field_class" id="name" placeholder="Enter name">
						<label>Email:</label>
						<input type="email" class="field_class" id="email" placeholder="Enter email" name="email">
						<label>Phone:</label>
						<input type="tel" name="phone" class="field_class" id="phone" placeholder="Enter phone">
						<label>Password:</label>
						<input type="password" id="pass" class="field_class" id="password" placeholder="Enter password" name="password">
						<button type="submit" class="submit_class">SIGN UP</button>
					</div>
				</form>
			</div>
		</div>

		<jsp:include page="../layout/users/footer.jsp" />
	</body>

	</html>