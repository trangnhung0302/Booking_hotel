<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Booking</title>
<link rel="stylesheet" href="/css/show.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="/js/index.js"></script>
</head>
<body>
	<jsp:include page="../../layout/users/navbar.jsp" />
  <div class="body">
    <div>
      <h2>Chi tiết đặt phòng</h2>
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
        <th>Trạng thái</th>
      </tr>
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
          <span class="${ reservation.getCancelTime() == null ? 'success' : 'cancelled'}">
            ${ reservation.getCancelTime() == null ? 'Thành công' : 'Đã hủy' }
          </span>
        </td>
      </tr>
    </table >
    <h3>Dịch vụ</h3>
    <table class="table">

      <tr>
        <c:forEach items="${ reservation.getServices() }" var="service" varStatus="status">
          ${ service.getName() }
        </c:forEach>
      </tr>
      <c:if test="${ reservation.getServices() == null }">
        <span>Không có dịch vụ được lựa chọn</span>
      </c:if>
    </table>
    <div class="btn-xn">
      <a href="/history"><button type="button" class="btn btn-outline-primary btn-lg">Xác nhận</button></a>
      <c:if test="${ reservation.getCancelTime() == null }">
        <a href="/booking/cancel/${ reservation.getId() }"><button type="button" class="btn btn-outline-dark">Hủy</button></a>
      </c:if>
    </div>
  </div>
  <jsp:include page="../../layout/users/footer.jsp" />
</body>
</html>