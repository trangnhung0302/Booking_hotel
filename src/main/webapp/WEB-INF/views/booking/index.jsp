<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Booking</title>
<link rel="stylesheet" href="/css/booking.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="/js/index.js"></script>
</head>
<body>
	<jsp:include page="../../layout/users/navbar.jsp" />
		<div class="w3-container w3-padding-64" id="contact">
			<form action="/booking" method="POST" novalidate>
				<div class="info_booking">
					<h1>BOOKING</h1><br>
					<c:if test="${ errors.roomNumber != null }">
						<div class="errors">
							<span >${ errors.roomNumber }</span>
						</div>
					</c:if>
					<p class="w3-text-blue-grey w3-large"><b>ROYAL HOTEL, PTIT, HÀ ĐÔNG, HÀ NỘI</b></p>
					<p>You can also contact us by phone 0123456789 or email royal@gmail.com, or you can send us a message here:</p>
					<div class="row">
						<div class="col-md-6 mb-3 mb-lg-0 col-lg-3">
							<label for="startTime" class="font-weight-bold text-black">Thời gian nhận phòng</label>
							<div class="field-icon-wrap">
								<div class="icon"><span class="icon-calendar"></span></div>
								<input type="datetime-local" id="startTime" class="form-control" name="startTime" value="${ startTime }">
							</div>
							<c:if test="${ errors.startTime != null }">
								<div class="errors">
									<span >${ errors.startTime }</span>
								</div>
							</c:if>
						</div>
						<div class="col-md-6 mb-3 mb-lg-0 col-lg-3">
							<label for="endTime" class="font-weight-bold text-black">Thời gian trả phòng</label>
							<div class="field-icon-wrap">
								<div class="icon"><span class="icon-calendar"></span></div>
								<input type="datetime-local" id="endTime" class="form-control" name="endTime" value="${ endTime }">
							</div>
							<c:if test="${ errors.endTime != null }">
								<div class="errors">
									<span >${ errors.endTime }</span>
								</div>
							</c:if>
						</div>
						<div class="col-md-6 mb-3 mb-lg-0 col-lg-3">
							<label for="adults" class="font-weight-bold text-black">Số người lớn</label>
							<div class="field-icon-wrap">
								<div class="icon"><span class="ion-ios-arrow-down"></span></div>
								<input class="w3-input w3-padding-16" type="number" placeholder="How many adult" name="numberOfAdults" value="${ numberOfAdults }">
							</div>
							<c:if test="${ errors.numberOfAdults != null }">
								<div class="errors">
									<span >${ errors.numberOfAdults }</span>
								</div>
							</c:if>
						</div>
						<div class="col-md-6 mb-3 mb-lg-0 col-lg-3">
							<label for="children" class="font-weight-bold text-black">Số trẻ em</label>
							<div class="field-icon-wrap">
								<div class="icon"><span class="ion-ios-arrow-down"></span></div>
								<input class="w3-input w3-padding-16" type="number" placeholder="How many children" name="numberOfChildren" value="${ numberOfChildren }">
							</div>
							<c:if test="${ errors.numberOfChildren != null }">
								<div class="errors">
									<span >${ errors.numberOfChildren }</span>
								</div>
							</c:if>
						</div>
					</div>
				</div>
				<div class="info_room_categories">
					<h1>Loại Phòng</h1>
					<c:if test="${ errors.roomCategoryId != null }">
						<div class="errors">
							<span >${ errors.roomCategoryId }</span>
						</div>
					</c:if>
					<c:forEach items="${ roomCategories }" var="roomCategory" varStatus="varStatus">
						<c:if test="${varStatus.index % 2 == 0}">
							<div class="row mb_large info_room_categories_left">
								<div class="col-md-6">
									<img class="room_categories_images" src="${ roomCategory.getImageUrl() }">
								</div>
								<div class="col-md-6">
									<h2>${ roomCategory.getName() }</h2>
									<div class="d-flex">
										<div class="remark">
											${ roomCategory.getRemark() }
										</div>
										<div class="price">
											<table>
												<tr>
													<td>Giá theo ngày</td>
													<td><fmt:formatNumber value="${ roomCategory.getPriceOfDay() }" type="currency" currencyCode="VND" /></td>
												</tr>
												<tr>
													<td>Giá qua đêm</td>
													<td><fmt:formatNumber value="${ roomCategory.getPriceOvernight() }" type="currency" currencyCode="VND" /></td>
												</tr>
												<tr>
													<td>Giá theo giờ</td>
													<td><fmt:formatNumber value="${ roomCategory.getPriceOfHour() }" type="currency" currencyCode="VND" /></td>
												</tr>
												<tr>
													<td>Số người</td>
													<td>${ roomCategory.getMaxNumberOfPeople() }</td>
												</tr>
											</table>
										</div>
									</div>
									<div class="choose">
										<input type="radio" name="roomCategoryId" value="${ roomCategory.getId() }">
									</div>
								</div>
							</div>
						</c:if>
						<c:if test="${varStatus.index % 2 != 0}">
							<div class="row mb_large info_room_categories_right">
								<div class="col-md-6">
									<h2>${ roomCategory.getName() }</h2>
									<div class="d-flex">
										<div class="remark">
											${ roomCategory.getRemark() }
										</div>
										<div class="price">
											<table>
												<tr>
													<td>Giá theo ngày</td>
													<td><fmt:formatNumber value="${ roomCategory.getPriceOfDay() }" type="currency" currencyCode="VND" /></td>
												</tr>
												<tr>
													<td>Giá qua đêm</td>
													<td><fmt:formatNumber value="${ roomCategory.getPriceOvernight() }" type="currency" currencyCode="VND" /></td>
												</tr>
												<tr>
													<td>Giá theo giờ</td>
													<td><fmt:formatNumber value="${ roomCategory.getPriceOfHour() }" type="currency" currencyCode="VND" /></td>
												</tr>
												<tr>
													<td>Số người</td>
													<td>${ roomCategory.getMaxNumberOfPeople() }</td>
												</tr>
											</table>
										</div>
									</div>
									<div class="choose">
										<input type="radio" name="roomCategoryId" value="${ roomCategory.getId() }" ${ roomCategory.getId() == roomCategoryId ? 'checked' : '' }>
									</div>
								</div>
								<div class="col-md-6">
									<img class="room_categories_images" src="${ roomCategory.getImageUrl() }">
								</div>
							</div>
						</c:if>
					</c:forEach>
				</div>
				<div class="info_service">
					<h1>Dịch vụ</h1>
					<div class="row">
						<c:forEach items="${ services }" var="service">
							<div class="col-md-4">
								<div>
									<img class="service_images" src="${ service.getImageUrl() }">
								</div>
								<h5>
									${ service.getName() } 
									(<fmt:formatNumber value="${service.getPrice()}" type="currency" currencyCode="VND" />/Người)
								</h5>
								<div>
									${ service.getRemark() }
								</div>
								<div class="choose-check">
								<input type="checkbox" name="serviceIds" value="${ service.getId() }"
									<c:forEach items="${ serviceIds }" var="serviceId">
										<c:if test="${ serviceId == service.getId() }">
											checked
										</c:if>
									</c:forEach>
								>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
				<div class="book">
					<button class="btn btn-outline-primary btn-lg" type="submit">Đặt chỗ</button>
				</div>
			</form>
	  </div>
	<jsp:include page="../../layout/users/footer.jsp" />
</body>
</html>
