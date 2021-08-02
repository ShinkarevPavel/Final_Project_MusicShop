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
<%@include file="/include/admin_header.jsp" %>

Information about User: ${user.nickname}
<br>
<div class="spinner-border text-primary" role="status">
    <span class="visually-hidden">Loading...</span>
</div>
<table border="2" class="table table-striped">
    <tr>
        <th>Login</th>
        <th>Nickname</th>
        <th>Name</th>
        <th>Surename</th>
        <th>Status</th>
    </tr>
    <td>${user.login}</td>
    <td>${user.nickname}</td>
    <td>${user.name}</td>
    <td>${user.surename}</td>
    <td>${user.role}</td>


</table>
<button type="submit"><fmt:message key="page.admin.update"/></button>
</body>
</html>
