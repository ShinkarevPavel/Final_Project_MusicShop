<%--
  Created by IntelliJ IDEA.
  User: pavel
  Date: 30.06.2021
  Time: 23:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${curr_lang}" scope="request"/>
<fmt:setBundle basename="localization"/>
<html>
<head>
</head>

<body style="background-image: url(https://cdn.shopify.com/s/files/1/0975/2962/products/image_67736ab5-4f2a-4ba7-b912-b0b3ae367fb9_2048x.jpg?v=1541103676);
background-repeat: no-repeat;
background-position: center center;
background-size: cover">
<%@include file="../../include/common_imports.jspf" %>

<section class="vh-100">
    <div class="row">
        <div class="col-md-3 offset-md-4">
            <div class="login-form bg-light mt-4 p-4">
                <h4 class="offset-xxl-2"><fmt:message key="page.registration_is_done"/></h4>
                <p class="mb-3 center-block"><fmt:message key="page.registration_is_done_text"/></p>
                <form method="post" action="${abs_path}/pages/common/login.jsp">
                    <div class="input-group - offset-xxl-5">
                        <button type="submit" class="input-group-text btn-primary"><fmt:message key="page.login.sign_in.button"/></button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>
</body>
</html>
