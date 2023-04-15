<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Booking</title>
<link rel="stylesheet" href="css/booking.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
<script src="/js/index.js" type="module"></script>
<!-- <script src="/js/pages/booking/index.vue" type="module"></script> -->
</head>
<body>
	<jsp:include page="../layout/users/navbar.jsp" />
		<div class="w3-container w3-padding-64" id="contact">
			<div class="info_booking">
				<h1>BOOKING</h1><br>
				<p></p>
				<p class="w3-text-blue-grey w3-large"><b>LTWEB8 HOTEL, PTIT, HÀ ĐÔNG, HÀ NỘI</b></p>
				<p>You can also contact us by phone 0123456789 or email ltweb8@gmail.com, or you can send us a message here:</p>
				<form action="/action_page.php" target="_blank">
					<p><input class="w3-input w3-padding-16" type="text" placeholder="Name" required name="Name"></p>
					<p><input class="w3-input w3-padding-16" type="datetime-local" placeholder="Ngày Nhận" required name="date"></p>
					<p><input class="w3-input w3-padding-16" type="datetime-local" placeholder="Ngày Trả" required name="date"></p>
					<p><input class="w3-input w3-padding-16" type="number" placeholder="How many people" required name="People"></p>
					<p><input class="w3-input w3-padding-16" type="text" placeholder="Loại Phòng" required name="Message"></p>
					<p><button class="w3-button w3-light-grey w3-section" type="submit">SEND MESSAGE</button></p>
				</form>
			</div>
			<div class="info_room_categories">
				<h2>Loại Phòng</h2>
				<c:forEach items="${ roomCategories }" var="roomCategory" varStatus="varStatus">
					<div class="row mb_large">
						<c:if test="${varStatus.index % 2 == 0}">
							<div class="info_room_categories_left">
								<div class="col-md-6">
									<img class="room_categories_images" src="${ roomCategory.getImage_url() }">
								</div>
								<div class="col-md-6">
									<h5>${ roomCategory.getName() }</h5>
									<div>
										${ roomCategory.getRemark() }
									</div>
								</div>
							</div>
						</c:if>
						<c:if test="${varStatus.index % 2 != 0}">
							<div class="info_room_categories_right">
								<div class="col-md-6">
									<h5>${ roomCategory.getName() }</h5>
									<div>
										${ roomCategory.getRemark() }
									</div>
								</div>
								<div class="col-md-6">
									<img class="room_categories_images" src="${ roomCategory.getImage_url() }">
								</div>
							</div>
						</c:if>
					</div>
				</c:forEach>
			</div>
			<div id="app" class="experiment-block">
			</div>
			<div class="info_service">
				<h2>Dịch vụ</h2>
				<div class="row">
					<c:forEach items="${ services }" var="service">
						<div class="col-md-4">
							<div>
								<img class="service_images" src="${ service.getImage_url() }">
							</div>
							<h5>${ service.getName() }</h5>
							<div>
								${ service.getRemark() }
							</div>
							<input type="button" value="${ service.getId() }">
						</div>
					</c:forEach>
				</div>
			</div>
	  </div>
	<jsp:include page="../layout/users/footer.jsp" />
</body>
</html>
