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
    <%@include file="/include/admin_header.jsp" %>
</head>
<body>

    <table border="4" class="table table-striped">
        <tr>
            <th>User id</th>
            <th>Login</th>
            <th>Email</th>
            <th>Role</th>
            <th>Status</th>
            <th>User Control</th>
        </tr>
        <strong>
            <p class="text-danger">${message}</p>
        </strong>
        <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.login}</td>
            <td>${user.email}</td>
            <td>
                <div class="dropdown">
                    <button class="btn btn-primary dropdown-toggle" name="${user.id}" value="${user.id}" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
                            ${user.role}
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                        <li><a class="dropdown-item" href="${abs_path}/controller?command=user_role_control_command&userId=${user.id}&new_role=ADMIN">ADMIN</a></li>
                        <li><a class="dropdown-item" href="${abs_path}/controller?command=user_role_control_command&userId=${user.id}&new_role=CLIENT">CLIENT</a></li>
                        <li><a class="dropdown-item" href="${abs_path}/controller?command=user_role_control_command&userId=${user.id}&new_role=GUEST">GUEST</a></li>
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
                <a href="${abs_path}/controller?command=user_info&userId=${user.id}">Control</a>
            </td>
            </c:forEach>
    </table>

    <nav aria-label="Page navigation area">
        <ul class="pagination justify-content-center">
            <li class="page-item ${pageable.isFirstPage() ? 'disabled': ''}">
                <a class="page-link"
                   href="${abs_path}/controller?command=show_all_users&page=${pageable.currentPage - 1}" tabindex="-1">Previous</a>
            </li>
            <c:forEach var="i" begin="1" end="${pageable.pageCount()}">
                <li class="page-item ${pageable.currentPage eq i ? 'active': ''}">
                    <a class="page-link" href="${abs_path}/controller?command=show_all_users&page=${i}">${i}</a></li>
            </c:forEach>
            <li class="page-item ${pageable.isLastPage() ? 'disabled': ''}">
                <a class="page-link"
                   href="${abs_path}/controller?command=show_all_users&page=${pageable.currentPage + 1}">Next</a>
            </li>
        </ul>
    </nav>
</body>
</html>
