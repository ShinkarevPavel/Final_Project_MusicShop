<%--
  Created by IntelliJ IDEA.
  User: Pavel_Shinkarev
  Date: 21.07.2021
  Time: 12:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="abs_path">${pageContext.request.contextPath}</c:set>

<html>
<head>
    <%@include file="/include/header.jsp" %>
    <link rel="stylesheet" href="https://www.markuptag.com/bootstrap/5/css/bootstrap.min.css">
</head>
<body style="background-image: url(https://cdn.hipwallpaper.com/i/95/86/BSezOZ.jpg);
background-repeat: no-repeat;
background-position: center center;
background-size: cover">


<section class="vh-100">
    <div class="row">
        <div class="col-md-3 offset-md-4">
            <div class="login-form bg-light mt-4 p-4">
                <form action="${abs_path}/controller?command=forgot_password_command" method="post" class="row g-3">
                    <h4 style="color: #084298"><fmt:message key="page.head.password"/></h4>
                    <div class="col-12">
                        <label><fmt:message key="page.change_password.enter_password"/></label>
                        <input type="password" pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{12,45}$"
                               name="password" class="form-control"
                               placeholder="<fmt:message key="page.change_password.enter_password"/>" required>
                    </div>
                    <div class="col-12">
                        <label><fmt:message key="page.change_password.cofirm_password"/></label>
                        <input type="password" pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{12,45}$"
                               name="checkPassword" class="form-control"
                               placeholder="<fmt:message key="page.change_password.cofirm_password"/>" required>
                    </div>
                    <span>
                            <strong>
                                 <p class="text-danger">${errors.passwordError}</p>
                             </strong>
                        </span>
                    <button type="submit" class="btn btn-primary"><fmt:message key="page.send"/></button>
                </form>
                <hr class="mt-4">
            </div>
        </div>
    </div>
    <br>
    <br>
    <br>
    <br>
</section>
</body>
</html>
