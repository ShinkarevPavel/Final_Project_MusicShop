<%--
  Created by IntelliJ IDEA.
  User: pavel
  Date: 03.07.2021
  Time: 19:31
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
    <%@include file="/include/header.jsp" %>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

</head>
<body>
<div class="container">
    <img src="https://img.kytary.com/eshop_ie/velky_v2/na/637298488625630000/9df4c7ec/64762517/kohala-3-4-size-steel-string-acoustic-guitar.jpg" class="img-responsive" alt="Lights" width="1200" height="452">
</div>
<label><fmt:message key="page.head.login"/></label>
<br/>
<br/>
<form method="post" action="${abs_path}/controller?command=login">
    <label for="login"><fmt:message key="page.login.login"/>
        <input type="hidden" name="command" value="login">
        <input type="text" name="login" id="login" required>
    </label>
    <br>
    <label for="password"><fmt:message key="page.login.password"/>
        <input type="password" name="password" id="password" required>
    </label>
    <span>
        <strong>
            <p class="text-danger">${loginError}</p>
        </strong>
    </span>
    <br>
    <input type="hidden" name="command" value="login">
    <button type="submit"><fmt:message key="page.login.sign_in.button"/></button>
</form>


<table>
    <tr>
        <th>
            <form method="post" action="${abs_path}/pages/registration.jsp">
                <button type="submit"><fmt:message key="page.login.register"/></button>
            </form>
        </th>
        <th>
            <form method="post" action="${abs_path}/pages/guest.jsp">
                <button type="submit"><fmt:message key="page.login.guest"/></button>
            </form>
        </th>
    <tr>
</table>
</body>
</html>
