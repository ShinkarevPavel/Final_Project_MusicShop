<%--
  Created by IntelliJ IDEA.
  User: Pavel_Shinkarev
  Date: 21.07.2021
  Time: 20:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="abs_path">${pageContext.request.contextPath}</c:set>
<html lang="en">
<head>
    <%@include file="/include/header.jsp" %>
    <br>
    <br>
    <br>
    <link rel="stylesheet" href="https://www.markuptag.com/bootstrap/5/css/bootstrap.min.css">
</head>
<body>
<form method="post" action="${abs_path}/controller?command=registration">
    <section class="card" style="background-color: #cedeea;">
        <div class="container h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-xl-9">
                    <h1 class="text-white mb-4"><fmt:message key="page.registration.head"/></h1>
                    <div class="card" style="border-radius: 15px;">
                        <div class="card-body">
                            <div class="row align-items-center pt-4 pb-3">
                                <div class="col-md-3 ps-5">
                                    <h6 class="mb-0" style="color: #084298"><fmt:message key="page.registration.login"/></h6>
                                </div>
                                <div class="col-md-9 pe-5">
                                    <input type="text" class="form-control form-control-lg"
                                           name="login" pattern="^[\w@#$%^&+=]{7,25}$"
                                           value="${registrationValues.login}"
                                           placeholder="<fmt:message key="page.registration.login"/>" required/>
                                    <strong>
                                        <p class="text-danger">${errors.loginError}</p>
                                    </strong>
                                    <h6 class="mb-0" style="color: #b1b1b3">
                                        <fmt:message key="page.registration.login.requirements"/>
                                    </h6>
                                </div>
                            </div>
                            <hr class="mx-n3">
                            <div class="row align-items-center py-3">
                                <div class="col-md-3 ps-5">
                                    <h6 class="mb-0" style="color: #084298"><fmt:message
                                            key="page.registration.email"/></h6>
                                </div>
                                <div class="col-md-9 pe-5">
                                    <input type="email" class="form-control form-control-lg"
                                           placeholder="example@example.com" name="email"
                                           pattern="^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$"
                                           value="${registrationValues.email}" required/>
                                    <strong>
                                        <p class="text-danger">${errors.emailError}</p>
                                    </strong>
                                </div>
                            </div>

                            <hr class="mx-n3">
                            <div class="row align-items-center py-3">
                                <div class="col-md-3 ps-5">
                                    <h6 class="mb-0" style="color: #084298"><fmt:message
                                            key="page.registration.password"/></h6>
                                </div>
                                <div class="col-md-9 pe-5">
                                    <input type="password" class="form-control form-control-lg" name="password"
                                           pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{12,45}$"
                                           placeholder="<fmt:message key="page.registration.password"/>" required>
                                    <strong>
                                        <p class="text-danger">${errors.passwordError}</p>
                                    </strong>
                                    <h6 class="mb-0" style="color: #b1b1b3">
                                        <fmt:message key="page.registration.password.requirements"/>
                                    </h6>
                                </div>
                            </div>
                            <hr class="mx-n3">
                            <div class="row align-items-center py-3">
                                <div class="col-md-3 ps-5">
                                    <h6 class="mb-0" style="color: #084298"><fmt:message
                                            key="page.registration.confirm_password"/></h6>
                                </div>
                                <div class="col-md-9 pe-5">
                                    <input type="password"
                                           class="form-control form-control-lg"
                                           name="checkPassword"
                                           placeholder="<fmt:message key="page.registration.confirm_password"/>"
                                           required>
                                </div>
                            </div>
                            <hr class="mx-n3">
                            <div class="row align-items-center py-3">
                                <div class="col-md-3 ps-5">
                                    <h6 class="mb-0" style="color: #084298"><fmt:message
                                            key="page.registration.nickname"/></h6>
                                </div>
                                <div class="col-md-9 pe-5">
                                    <input type="text" class="form-control form-control-lg"
                                           name="nickname"
                                           placeholder="<fmt:message key="page.registration.nickname"/>"
                                           pattern="^[\w@#$%^&+=]{2,30}$"
                                           value="${registrationValues.nickname}" required>
                                    <strong>
                                        <p class="text-danger">${errors.nicknameError}</p>
                                    </strong>
                                    <h6 class="mb-0" style="color: #b1b1b3">
                                        <fmt:message key="page.registration.nickname.requirements"/>
                                    </h6>
                                </div>
                            </div>

                            <hr class="mx-n3">
                            <div class="row align-items-center py-3">
                                <div class="col-md-3 ps-5">
                                    <h6 class="mb-0" style="color: #084298"><fmt:message
                                            key="page.registration.name"/></h6>
                                </div>
                                <div class="col-md-9 pe-5">
                                    <input type="text"
                                           class="form-control form-control-lg"
                                           name="name"
                                           value="${registrationValues.name}"
                                           placeholder="<fmt:message key="page.registration.name"/>" required>
                                    <h6 class="mb-0" style="color: #b1b1b3">
                                        <fmt:message key="page.registration.name.requirements"/>
                                    </h6>
                                </div>
                            </div>

                            <hr class="mx-n3">
                            <div class="row align-items-center py-3">
                                <div class="col-md-3 ps-5">
                                    <h6 class="mb-0" style="color: #084298"><fmt:message
                                            key="page.registration.surename"/></h6>
                                </div>
                                <div class="col-md-9 pe-5">
                                    <input type="text"
                                           class="form-control form-control-lg"
                                           name="surename"
                                           value="${registrationValues.surename}"
                                           placeholder="<fmt:message key="page.registration.surename"/>" required>
                                    <h6 class="mb-0" style="color: #b1b1b3">
                                        <fmt:message key="page.registration.surename.requirements"/>
                                    </h6>
                                </div>
                            </div>

                            <hr class="mx-n3">
                            <div class="px-5 py-4">
                                <button type="submit" class="btn btn-primary btn-lg">
                                    <fmt:message key="page.registration.sign_up"/>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</form>
</body>
</html>

