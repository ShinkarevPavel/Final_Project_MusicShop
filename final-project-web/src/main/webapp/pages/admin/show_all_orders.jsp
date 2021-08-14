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
<body style="background-image: url(https://wallpaperaccess.com/full/2086727.jpg);
background-repeat: no-repeat;
background-position: center center;
background-size: cover">

<table border="4" class="table table-striped" style="background-color: white">
    <tr>
        <th>Id</th>
        <th>Date</th>
        <th>User id</th>
        <th>Address</th>
        <th>Payment</th>
        <th>Status</th>
        <th>Details</th>
    </tr>
    <c:forEach items="${orders}" var="order">
    <tr>
        <td>${order.id}</td>
        <td>${order.orderDate}</td>
        <td>${order.userId}</td>
        <td>${order.payment}</td>
        <td>${order.payment}</td>
        <td>
            <div class="dropdown">
                <button class="btn btn-primary dropdown-toggle" name="${order.id}" value="${order.id}" type="button"
                        id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
                        ${order.status}
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                    <li><a class="dropdown-item"
                           href="${abs_path}/controller?command=change_order_status_command&orderId=${order.id}&new_type=CREATED">CREATED</a>
                    </li>
                    <li><a class="dropdown-item"
                           href="${abs_path}/controller?command=change_order_status_command&orderId=${order.id}&new_type=PROCESSING">PROCESSING</a>
                    </li>
                    <li><a class="dropdown-item"
                           href="${abs_path}/controller?command=change_order_status_command&orderId=${order.id}&new_type=ACCEPTED">ACCEPTED</a>
                    </li>
                    <li><a class="dropdown-item"
                           href="${abs_path}/controller?command=change_order_status_command&orderId=${order.id}&new_type=ON_DELIVERY">ON_DELIVERY</a>
                    </li>
                    <li><a class="dropdown-item"
                           href="${abs_path}/controller?command=change_order_status_command&orderId=${order.id}&new_type=DELIVERED">DELIVERED</a>
                    </li>
                </ul>
            </div>
        </td>
        <td>
            <a href="${abs_path}/controller?command=show_order_details_command&userId=${order.userId}&orderId=${order.id}">Details</a>
        </td>
        </c:forEach>
</table>
<span style="color: white">${message}</span>

<nav aria-label="Page navigation area">
    <ul class="pagination justify-content-center">
        <li class="page-item ${pageable.isFirstPage() ? 'disabled': ''}">
            <a class="page-link"
               href="${abs_path}/controller?command=show_all_orders_command&page=${pageable.currentPage - 1}" tabindex="-1">Previous</a>
        </li>
        <c:forEach var="i" begin="1" end="${pageable.pageCount()}">
            <li class="page-item ${pageable.currentPage eq i ? 'active': ''}">
                <a class="page-link" href="${abs_path}/controller?command=show_all_orders_command&page=${i}">${i}</a></li>
        </c:forEach>
        <li class="page-item ${pageable.isLastPage() ? 'disabled': ''}">
            <a class="page-link"
               href="${abs_path}/controller?command=show_all_orders_command&page=${pageable.currentPage + 1}">Next</a>
        </li>
    </ul>
</nav>

</body>
</html>
