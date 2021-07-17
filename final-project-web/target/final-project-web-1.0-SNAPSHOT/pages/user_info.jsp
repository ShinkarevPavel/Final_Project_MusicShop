<%--
  Created by IntelliJ IDEA.
  User: Pavel_Shinkarev
  Date: 16.07.2021
  Time: 09:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="/include/header.jsp" %>

Information about User:

<table border="2">
    <td>${user.email}</td>
    <td>${user.nickname}</td>
    <td>${user.name}</td>
    <td>${user.surename}</td>
    <td>${user.role}</td>
    <td>${user.status}</td>
    <td>${user.id}</td>

    <td><select name="role" id="role">
        <c:forEach var="role" items="${requestScope.roles}">
            <option value="${role}">${role}</option>
        </c:forEach>
    </select></td>
    <td><select name="status" id="status">
        <c:forEach var="status" items="${requestScope.statuses}">
            <option value="${status}">${status}</option>
        </c:forEach>
    </select></td>
</table>
<button type="submit"><fmt:message key="page.admin.update"/></button>
</body>
</html>
