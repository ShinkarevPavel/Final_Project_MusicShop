<%--
  Created by IntelliJ IDEA.
  User: pavel
  Date: 29.06.2021
  Time: 22:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/include/header.jsp" %>
</head>
<body>

    <table border="4" class="table table-striped">
        <tr>
            <th>Login</th>
            <th>Email</th>
            <th>Role</th>
            <th>Status</th>
            <th>User_id</th>
        </tr>
        <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.login}</td>
            <td>${user.email}</td>
            <td>
                <div class="dropdown">
                    <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
                            ${user.role}
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                        <li><a class="dropdown-item" href="${abs_path}/controller?command=user_status_control_command&userId=${user.id}&new_role=ADMIN">ADMIN</a></li>
                        <li><a class="dropdown-item" href="${abs_path}/controller?command=user_status_control_command&userId=${user.id}&new_role=CLIENT">CLIENT</a></li>
                        <li><a class="dropdown-item" href="${abs_path}/controller?command=user_status_control_command&userId=${user.id}&new_role=GUEST">GUEST</a></li>
                    </ul>
                </div>
            </td>
            <td>
                <div class="dropdown">
                    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                        ${user.status}
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                        <li><a class="dropdown-item" href="${abs_path}/controller?command=user_status_control_command&userId=${user.id}&new_state=ACTIVE">ACTIVE</a></li>
                        <li><a class="dropdown-item" href="${abs_path}/controller?command=user_status_control_command&userId=${user.id}&new_state=BLOCKED">BLOCKED</a></li>
                    </ul>
                </div>
            </td>
            <td>
                <a href="${abs_path}/controller?command=user_info&userId=${user.id}">${user.id}</a>
            </td>
            </c:forEach>
    </table>
    <!-- JavaScript Bundle with Popper -->
</body>
</html>
