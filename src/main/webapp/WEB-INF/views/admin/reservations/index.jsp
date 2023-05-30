<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8">
  <link rel="icon" href="images/database/logo.png">

  <title>Danh sách đặt phòng</title>
</head>

<body>
  <div id="viewport">
    <jsp:include page="../../../layout/admin/sidebar.jsp" />
    <div id="content">
      <jsp:include page="../../../layout/admin/narbar.jsp" />
      <div class="container-fluid">
        <h1>Danh sách đặt phòng</h1>
        <div class="col-md-12 mb_large">
          <div class="card">
            <form action="/admin/reservations" style="width: 100%;">
              <div class="row" style="margin-bottom: 20px;">
                <div class="col-md-4">
                  <label>Tên khách hàng</label>
                  <input class="form-control" name="name" value="${ name }">
                </div>
                <div class="col-md-4">
                  <label>Từ ngày</label>
                  <input class="form-control" type="datetime-local" name="fromDate" value="${ fromDate }">
                </div>
                <div class="col-md-4">
                  <label>Đến ngày</label>
                  <input class="form-control" type="datetime-local" name="toDate" value="${ toDate }">
                </div>
              </div>
              <div class="d-flex">
                <button style="margin-left: auto; margin-right: 10px;" class="btn btn-info">Tìm kiếm</button>
                <a href="/admin/reservations">
                  <button class="btn btn-danger" type="button">Làm mới</button>
                </a>
              </div>
            </form>
          </div>
        </div>
        <div class="col-md-12">
          <div class="card">
            <table class="table">
              <tr>
                <th style="width: 15%;">Ngày nhận phòng</th>
                <th style="width: 15%;">Ngày trả phòng</th>
                <th style="width: 15%;">Tên</th>
                <th style="width: 8%;">Người lớn</th>
                <th style="width: 7%;">Trẻ con</th>
                <th style="width: 10%;">Phòng</th>
                <th style="width: 10%;">Loại phòng</th>
                <th style="width: 20%;">Dịch vụ</th>
              </tr>
              <c:forEach items="${ reservations }" var="reservation">
                <tr>
                  <td>${ reservation.getStartTime() }</td>
                  <td>${ reservation.getEndTime() }</td>
                  <td>${ reservation.getCustomer().getName() }</td>
                  <td>${ reservation.getNumberOfAdults() }</td>
                  <td>${ reservation.getNumberOfChildren() }</td>
                  <td>
                    <c:forEach items="${ reservation.getRooms() }" var="room">
                      ${ room.getName() } 
                    </c:forEach>
                  </td>
                  <td>${ reservation.getRooms().toArray()[0].getRoomCategory().getName() }</td>
                  <td>
                    <c:forEach items="${ reservation.getServices() }" var="service">
                      ${ service.getName() }
                    </c:forEach>
                  </td>
                </tr>
              </c:forEach>
            </table>
          </div>
        </div>
        </div>
      </div>
    </div>
  </div>
</body>

</html>