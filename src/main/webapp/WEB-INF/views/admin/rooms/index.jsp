<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html>

    <head>
      <meta charset="UTF-8">
      <link rel="icon" href="images/database/logo.png">

      <title>Danh sách phòng</title>
    </head>

    <body>
      <div id="viewport">
        <jsp:include page="../../../layout/admin/sidebar.jsp" />
        <div id="content">
          <jsp:include page="../../../layout/admin/narbar.jsp" />
          <div class="container-fluid">
            <div>
              <a href="/admin/room_categories">
                <button class="btn btn-warning">Quay lại</button>
              </a>
            </div>
            <h1>Danh sách phòng ${ roomCategory.getName() }</h1>
            <div class="col-md-12 btn-create">
              <a style="margin-left: auto;">
                <button class="btn btn-success" id="myBtn">Tạo mới</button>
              </a>
              <div id="myModal" class="modal">

                <!-- Modal content -->
                <div class="modal-content">
                  <span class="close">&times;</span>
                  <p>Nhập thông tin phòng</p>
                  <div class="form-input">
                    <form action="/admin/room_categories/${roomCategory.getId()}/rooms" method="post">
                      <div class="form-group">
                        <label>Tên phòng</label>
                        <input class="form-control" type="text" name="name">
                      </div>
                      <div class="form-group" style="display: flex;">
                        <button class="btn btn-primary" style="margin-left: auto;">Tạo mới</button>
                      </div>
                    </form>
                  </div>
                </div>
              </div>
            </div>
            <c:if test="${ message != null }">
              <div class="col-md-12">
                <div class="message_success">
                  ${ message }
                </div>
              </div>
            </c:if>
            <c:if test="${ messageFail != null }">
              <div class="col-md-12">
                <div class="message_fail">
                  ${ messageFail }
                </div>
              </div>
            </c:if>
            <div class="col-md-12">
              <div class="card">
                <table class="table">
                  <tr>
                    <th>Tên phòng</th>
                    <th>Trạng thái</th>
                  </tr>
                  <c:forEach items="${ rooms }" var="room">
                    <tr>
                      <td>${ room.getName() }</td>
                      <td class="d-flex item_center">
                        <c:if test="${ room.getStatus() == 10 }">
                          <div>
                            <a>
                              <button class="btn btn-info btn_status btn_active">
                                Hoạt động
                              </button>
                            </a>
                          </div>
                          <div>
                            <a href="/admin/room_categories/${roomCategory.getId()}/rooms/${room.getId()}">
                              <button class="btn btn-danger btn_status ml_medium">
                                Bảo trì
                              </button>
                            </a>
                          </div>
                        </c:if>
                        <c:if test="${ room.getStatus() != 10 }">
                          <div>
                            <a href="/admin/room_categories/${roomCategory.getId()}/rooms/${room.getId()}">
                              <button class="btn btn-info btn_status">
                                Hoạt động
                              </button>
                            </a>
                          </div>
                          <div>
                            <a>
                              <button class="btn btn-danger btn_status ml_medium btn_active">
                                Bảo trì
                              </button>
                            </a>
                          </div>
                        </c:if>
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
    <script>
      var modal = document.getElementById("myModal");

      var btn = document.getElementById("myBtn");

      var span = document.getElementsByClassName("close")[0];

      btn.onclick = function() {
        modal.style.display = "block";
      }

      span.onclick = function() {
        modal.style.display = "none";
      }

      window.onclick = function(event) {
        if (event.target == modal) {
          modal.style.display = "none";
        }
      }
    </script>