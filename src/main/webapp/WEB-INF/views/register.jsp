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
				<form id="login_form" class="form_class" action="/register" method="post" novalidate>
					<div class="form_div">
						<label>Name: (*)</label>
						<input type="text" name="name" class="field_class" id="name" placeholder="Enter name" value="${ name }">
						<c:if test="${ errors.name != null }">
							<div class="errors">
								<span >${ errors.name }</span>
							</div>
						</c:if>
						<label>Email: (*)</label>
						<input type="email" class="field_class" id="email" placeholder="Enter email" name="email" value="${ email }">
						<c:if test="${ errors.email != null }">
							<div class="errors">
								<span >${ errors.email }</span>
							</div>
						</c:if>
						<label>Phone:</label>
						<input type="tel" name="tel" class="field_class" id="phone" placeholder="Enter phone" value="${ tel }">
						<c:if test="${ errors.tel != null }">
							<div class="errors">
								<span >${ errors.tel }</span>
							</div>
						</c:if>
						<label>Password: (*)</label>
						<input type="password" class="field_class" id="password" placeholder="Enter password" name="password" value="${ password }">
						<c:if test="${ errors.password != null }">
							<div class="errors">
								<span >${ errors.password }</span>
							</div>
						</c:if>
						<label>RePassword: (*)</label>
						<input type="password" class="field_class" id="rePassword" placeholder="Enter confirm password" name="rePassword">
						<c:if test="${ errors.rePassword != null }">
							<div class="errors">
								<span >${ errors.rePassword }</span>
							</div>
						</c:if>
						<button type="submit" class="submit_class">SIGN UP</button>
					</div>
				</form>
			</div>
		</div>

		<jsp:include page="../layout/users/footer.jsp" />
	</body>

	</html>