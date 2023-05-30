<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lịch sử</title>
<link rel="stylesheet" href="/css/show.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="/js/index.js"></script>
</head>
<body>
	<jsp:include page="../../layout/users/navbar.jsp" />
  <div class="body">
    <div>
      <h2>Lịch sử đặt phòng</h2>
    </div>
    <c:if test="${ message != null }">
      <div class="col-md-12">
        <div class="message_success">
          ${ message }
        </div>
      </div>
    </c:if>
    <table class="table">
      <tr>
        <th>Phòng</th>
        <th>Nhận phòng</th>
        <th>Trả phòng</th>
        <th>Số người</th>
        <th>Dịch vụ</th>
        <th>Trạng thái</th>
        <th></th>
      </tr>
      <c:forEach items="${ reservations }" var="reservation">
        <tr>
          <td>
            <c:forEach items="${ reservation.getRooms() }" var="room" varStatus="status">
              ${ room.getName() }
            </c:forEach>
          </td>
          <td>${ reservation.getStartTime() }</td>
          <td>${ reservation.getEndTime() }</td>
          <td>${ reservation.getNumberOfAdults() + reservation.getNumberOfChildren() }</td>
          <td>
            <c:forEach items="${ reservation.getServices() }" var="service" varStatus="status">
              ${ service.getName() }
            </c:forEach>
          </td>
          <td>
            <span class="${ reservation.getCancelTime() == null ? 'success' : 'cancelled'}">
              ${ reservation.getCancelTime() == null ? 'Thành công' : 'Đã hủy'}
            </span>
          </td>
          <td>
            <a href="/booking/${ reservation.getId() }">
              <button class="btn btn-primary">Chi tiết</button> 
            </a>
          </td>
        </tr>
      </c:forEach>
    </table >
  </div>
  <jsp:include page="../../layout/users/footer.jsp" />
</body>
</html>