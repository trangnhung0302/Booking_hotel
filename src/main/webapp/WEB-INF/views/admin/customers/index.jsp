<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html>

    <head>
      <meta charset="UTF-8">
      <link rel="icon" href="images/database/logo.png">

      <title>Khách hàng</title>
    </head>

    <body>
      <div id="viewport">
        <jsp:include page="../../../layout/admin/sidebar.jsp" />
        <div id="content">
          <jsp:include page="../../../layout/admin/narbar.jsp" />
          <div class="container-fluid">
            <h1>Khách hàng</h1>
            <div class="col-md-12 mb_large">
              <div class="card">
                <form action="/admin/customers" style="width: 100%;">
                  <div class="row" style="margin-bottom: 20px;">
                    <div class="col-md-4">
                      <label>Tên</label>
                      <input class="form-control" name="name" value="${ name }">
                    </div>
                    <div class="col-md-4">
                      <label>Email</label>
                      <input class="form-control" name="email" value="${ email }">
                    </div>
                    <div class="col-md-4">
                      <label>Số điện thoại</label>
                      <input class="form-control" name="tel" value="${ tel }">
                    </div>
                  </div>
                  <div class="d-flex">
                    <button style="margin-left: auto; margin-right: 10px;" class="btn btn-info">Tìm kiếm</button>
                    <a href="/admin/customers">
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
                    <th>Tên</th>
                    <th>Email</th>
                    <th>Số điện thoại</th>
                  </tr>
                  <c:forEach items="${ customers }" var="customer">
                    <tr>
                      <td>${ customer.getName() }</td>
                      <td>${ customer.getEmail() }</td>
                      <td>${ customer.getTel() }</td>
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