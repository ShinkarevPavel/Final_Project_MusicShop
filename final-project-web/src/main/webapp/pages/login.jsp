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
<fmt:setLocale value="${curr_lang}" scope="request"/>
<fmt:setBundle basename="localization"/>
<html>
<head>
    <link rel="stylesheet" href="https://www.markuptag.com/bootstrap/5/css/bootstrap.min.css">
</head>
<body style="background-image: url(https://cdn.hipwallpaper.com/i/95/86/BSezOZ.jpg);
background-repeat: no-repeat;
background-position: center center;
background-size: cover">

<%@include file="/include/header.jsp" %>

<section class="vh-100" >
    <div class="row">
        <div class="col-md-3 offset-md-4">
            <div class="login-form bg-light mt-4 p-4">
                <form action="${abs_path}/controller?command=login" method="post" class="row g-3">
                    <h4 style="color: #084298"><fmt:message key="page.head.login"/></h4>
                    <div class="col-12">
                        <label><fmt:message key="page.login.login"/></label>
                        <input type="text" name="login" class="form-control" placeholder="<fmt:message key="page.login.login"/>" required>
                        <input type="hidden" name="command"  value="login">
                    </div>
                    <div class="col-12">
                        <label><fmt:message key="page.login.password"/></label>
                        <input type="password" name="password" class="form-control" placeholder="<fmt:message key="page.login.password"/>" required>
                    </div>
                    <div>
                        <span>
                            <strong>
                                <p class="text-danger">${loginError}</p>
                            </strong>
                        </span>
                    </div>
                    <div class="col-12">
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" id="rememberMe">
                            <label class="form-check-label" for="rememberMe"><fmt:message key="page.head.remember_me"/></label>
                        </div>
                    </div>
                    <div class="col-12">
                        <input type="hidden" name="command" value="login">
                        <button type="submit"><fmt:message key="page.login.sign_in.button"/></button>
                    </div>
                </form>
                <hr class="mt-4">
                <div class="col-12">
                    <p class="text-center mb-0">
                        <fmt:message key="page.head.registration_text"/>
                        <a href="${abs_path}/pages/registration.jsp">
                            <fmt:message key="page.login.register"/>
                        </a>
                    </p>
                </div>
            </div>
        </div>
    </div>
    <br>
    <br>
    <br>
    <br>
</section>
<script src="https://www.markuptag.com/bootstrap/5/js/bootstrap.bundle.min.js"></script>
</body>
</html>
