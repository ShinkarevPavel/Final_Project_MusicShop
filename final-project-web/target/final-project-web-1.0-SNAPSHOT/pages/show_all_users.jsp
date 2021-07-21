<%--
  Created by IntelliJ IDEA.
  User: pavel
  Date: 29.06.2021
  Time: 22:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="abs_path">${pageContext.request.contextPath}</c:set>
<html>
<head>
    <title>Admin page</title>
</head>
<body>
<%@include file="/include/header.jsp" %>
    <table border="4">
        <tr>
            <th>User_id</th>
            <th>Login</th>
            <th>Email</th>
            <th>Role</th>
            <th>Status</th>
        </tr>
        <c:forEach items="${users}" var="user">
        <tr>
            <td>
                <a href="${abs_path}/controller?command=user_info&userId=${user.id}">${user.id}</a>
            </td>
            <td>${user.login}</td>
            <td>${user.email}</td>
            <td>${user.role}</td>
            <td>${user.status}</td>
            </c:forEach>
    </table>
</body>
</html>
