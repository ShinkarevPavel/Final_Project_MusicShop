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

<html>
<head>

    <%@include file="/include/header.jsp" %>
</head>
<body>
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
    <span style="color: red">${loginError}</span>
    <br>
    <a href="${abs_path}/pages/registration.jsp">
    <button type="submit"><fmt:message key="page.login.sign_in.button"/></button>
    </a>
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
