<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <!DOCTYPE html>
    <html>

    <head>
      <meta charset="UTF-8">
      <link rel="icon" href="images/database/logo.png">

      <title>Dịch vụ</title>
    </head>

    <body>
      <div id="viewport">
        <jsp:include page="../../../layout/admin/sidebar.jsp" />
        <div id="content">
          <jsp:include page="../../../layout/admin/narbar.jsp" />
          <div class="container-fluid">
            <h1>Dịch vụ</h1>
            <div class="col-md-12 mb_large">
              <div class="card">
                <form action="/admin/services" style="width: 100%;">
                  <div class="row" style="margin-bottom: 20px;">
                    <div class="col-md-3">
                      <label>Tên</label>
                      <input class="form-control" name="name" value="${ name }">
                    </div>
                    <div class="col-md-3">
                      <label>Giá min</label>
                      <input class="form-control" name="minPrice" value="${ minPrice }">
                    </div>
                    <div class="col-md-3">
                      <label>Giá max</label>
                      <input class="form-control" name="maxPrice" value="${ maxPrice }">
                    </div>
                    <div class="col-md-3">
                      <label>Trạng thái</label>
                      <select class="form-control" name="status">
                        <option value="">Tất cả</option>
                        <option value="10" ${ status == '10' ? 'selected' : '' }>Hoạt động</option>
                        <option value="90" ${ status == '90' ? 'selected' : '' }>Tạm ngưng</option>
                      </select>
                    </div>
                  </div>
                  <div class="d-flex">
                    <button style="margin-left: auto; margin-right: 10px;" class="btn btn-info">Tìm kiếm</button>
                    <a href="/admin/services">
                      <button class="btn btn-danger" type="button">Làm mới</button>
                    </a>
                  </div>
                </form>
              </div>
            </div>
            <div class="col-md-12 btn-create">
              <a href="/admin/services/new" style="margin-left: auto;">
                <button class="btn btn-success">Tạo mới</button>
              </a>
            </div>
            <c:if test="${ message != null }">
              <div class="col-md-12">
                <div class="message_success">
                  ${ message }
                </div>
              </div>
            </c:if>
            <div class="col-md-12">
              <div class="card">
                <table class="table table_has_image">
                  <tr>
                    <th>Ảnh</th>
                    <th>Tên</th>
                    <th>Giá</th>
                    <th>Trạng thái</th>
                  </tr>
                  <c:forEach items="${ services }" var="service">
                    <tr>
                      <td style="width: 20%;">
                        <a href="/admin/services/${ service.getId() }">
                          <div><img src="${ service.getImageUrl() }" class="table_image"></div>
                        </a>
                      </td>
                      <td>
                        <a href="/admin/services/${ service.getId() }">
                          <div>${ service.getName() }</div>
                        </a>
                      </td>
                      <td>
                        <a href="/admin/services/${ service.getId() }">
                          <div><fmt:formatNumber value="${ service.getPrice() }" type="currency" currencyCode="VND" /></div>
                        </a>
                      </td>
                      <td>
                        <a href="/admin/services/${ service.getId() }">
                          <div>
                            <span class="${ service.getStatus() == '10' ? 'success' : 'cancelled'}">
                              ${ service.getStatus() == '10' ? 'Hoạt động' : 'Tạm ngưng'}
                            </span>
                          </div>
                        </a>
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