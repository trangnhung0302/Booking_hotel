<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html>

    <head>
      <meta charset="UTF-8">
      <title>Lịch sử</title>
      <link rel="stylesheet" href="/css/show.css">
      <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
      <script src="/js/index.js"></script>
    </head>

    <body>
      <jsp:include page="../../layout/users/navbar.jsp" />
      <div class="container">
        <div class="row justify-content-center">
          <div class="col-12 col-lg-10 col-xl-8 mx-auto">
            <h2 class="h3 mb-4 page-title">Settings</h2>
            <div class="my-4">
              <ul class="nav nav-tabs mb-4" id="myTab" role="tablist">
                <li class="nav-item">
                  <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab"
                    aria-controls="home" aria-selected="false">Profile</a>
                </li>
              </ul>
              <form action="/profile" method="post">
                <div class="row mt-5 align-items-center">
                  <div class="col-md-6">
                    <h1 class="">Thông tin người dùng</h1>
                  </div>
                </div>
                <c:if test="${ message != null }">
                  <div class="col-md-12">
                    <div class="message_success">
                      ${ message }
                    </div>
                  </div>
                </c:if>
                <hr class="my-4" />
                <div class="form-group">
                  <label for="name">Name</label>
                  <input type="text" class="form-control" id="name" placeholder="Royal Hotel"
                    value="${ customer.getName() }" name="name" />
                  <c:if test="${ errors.name != null }">
                    <div class="errors">
                      <span>${ errors.name }</span>
                    </div>
                  </c:if>
                </div>
                <div class="form-group">
                  <label for="inputEmail">Email</label>
                  <input type="email" class="form-control disabled" id="inputEmail" name="email"
                    value="${ customer.getEmail() }" />
                </div>
                <div class="form-group">
                  <label for="inputZip5">Phone</label>
                  <input type="text" class="form-control" id="inputZip5" placeholder="0123456789"
                    value="${ customer.getTel() }" name="tel" />
                  <c:if test="${ errors.tel != null }">
                    <div class="errors">
                      <span>${ errors.tel }</span>
                    </div>
                  </c:if>
                </div>
                <hr class="my-4" />
                <div class="row mb-4">
                  <div class="col-md-6">
                    <div class="form-group">
                      <label for="inputPassword5">New Password</label>
                      <input type="password" class="form-control" id="inputPassword5" name="password" />
                      <c:if test="${ errors.password != null }">
                        <div class="errors">
                          <span>${ errors.password }</span>
                        </div>
                      </c:if>
                    </div>
                    <div class="form-group">
                      <label for="inputPassword6">Confirm Password</label>
                      <input type="password" class="form-control" id="inputPassword6" name="rePassword" />
                      <c:if test="${ errors.rePassword != null }">
                        <div class="errors">
                          <span>${ errors.rePassword }</span>
                        </div>
                      </c:if>
                    </div>
                  </div>
                </div>
                <button type="submit" class="btn btn-primary">Chỉnh sửa</button>
              </form>
            </div>
          </div>
        </div>
      </div>
      <jsp:include page="../../layout/users/footer.jsp" />
    </body>

    </html>