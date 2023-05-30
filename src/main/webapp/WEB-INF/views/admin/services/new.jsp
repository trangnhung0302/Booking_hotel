<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html>

    <head>
      <meta charset="UTF-8">
      <link rel="icon" href="images/database/logo.png">

      <title>Thêm mới dịch vụ</title>
    </head>

    <body>
      <div id="viewport">
        <jsp:include page="../../../layout/admin/sidebar.jsp" />
        <div id="content">
          <jsp:include page="../../../layout/admin/narbar.jsp" />
          <div class="container-fluid">
            <h1>Tạo mới</h1>
            <div class="col-md-12 mb_large">
              <div class="card">
                <form action="/admin/services" method="post" style="width: 100%;" enctype="multipart/form-data">
                  <div class="form-group">
                    <label>Ảnh (*)</label>
                    <input type="file" name="image" accept="image/png, image/jpeg" class="form-control"/>
                    <c:if test="${ errors.image != null }">
                      <div class="errors">
                        <span >${ errors.image }</span>
                      </div>
                    </c:if>
                  </div>
                  <div class="form-group">
                    <label>Tên (*)</label>
                    <input type="text" name="name" value="${ name }" class="form-control"/>
                    <c:if test="${ errors.name != null }">
                      <div class="errors">
                        <span >${ errors.name }</span>
                      </div>
                    </c:if>
                  </div>
                  <div class="form-group">
                    <label>Giá (*)</label>
                    <input type="text" name="price" value="${ price }" class="form-control"/>
                    <c:if test="${ errors.price != null }">
                      <div class="errors">
                        <span >${ errors.price }</span>
                      </div>
                    </c:if>
                  </div>
                  <div class="form-group">
                    <label>Trạng thái (*)</label>
                    <div>
                      <div>
                        <input type="radio" name="status" value="10" ${ status == 10 ? 'checked' : ''}>Hoạt động
                      </div>
                      <div>
                        <input type="radio" name="status" value="90" ${ status == 90 ? 'checked' : ''}>Tạm ngưng
                      </div>
                    </div>
                    <c:if test="${ errors.status != null }">
                      <div class="errors">
                        <span >${ errors.status }</span>
                      </div>
                    </c:if>
                  </div>
                  <div class="form-group">
                    <label>Ghi chú</label>
                    <textarea type="text" name="remark" class="form-control">${ remark }</textarea>
                    <c:if test="${ errors.remark != null }">
                      <div class="errors">
                        <span >${ errors.remark }</span>
                      </div>
                    </c:if>
                  </div>
                  <div class="row">
                    <button class="btn btn-primary">Thêm mới</button>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
      </div>
    </body>

    </html>
