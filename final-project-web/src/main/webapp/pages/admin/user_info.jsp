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
<%@include file="/include/admin_header.jsp" %>
<body>

<div class="container rounded bg-white mt-5 mb-5">
    <div class="row">
        <div class="col-md-3 border-right">
            <div class="d-flex flex-column align-items-center text-center p-3 py-5">
                <img class="rounded-circle mt-5" width="150px"
                     src="https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg">
                <span class="font-weight-bold">
                    ${user.nickname}
                </span>
                <span class="text-black-50">${user.role}</span>
                <span>
                </span>
            </div>
        </div>
        <div class="col-md-5 border-right">
            <div class="p-3 py-5">
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h4 class="text-right">Profile Settings</h4>
                </div>

                <div class="col-md-12"><label class="labels">User Login:</label>
                    <label class="form-control">${user.login}</label>
                </div>
                <br>
                <div class="col-md-12"><label class="labels">User email:</label>
                    <label class="form-control">${user.email}</label>
                </div>
                <br>
                <div class="col-md-12"><label class="labels">User nickname:</label>
                    <label class="form-control">${user.nickname}</label>
                </div>
                <div class="col-md-12"><label class="labels">User name:</label>
                    <label class="form-control">${user.name}</label>
                </div>
                <div class="col-md-12"><label class="labels">User surename:</label>
                    <label class="form-control">${user.surename}</label>
                </div>
                <div class="col-md-12"><label class="labels">User status:</label>
                    <label class="form-control">${user.status}</label>
                </div>
            </div>
        </div>


        <%--Buttons--%>
        <div class="col-md-4">

            <c:if test="${empty requestScope.details}">
                <div class="p-3 py-5">
                    <div class="d-flex justify-content-between align-items-center experience">
                        <span>My Orders</span>

                        <div class="dropdown">
                            <button class="btn btn-success dropdown-toggle" type="button" id="dropdownMenuButton1"
                                    data-bs-toggle="dropdown" aria-expanded="false">
                                Orders
                            </button>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                                <li><a class="dropdown-item"
                                       href="${abs_path}/controller?command=find_order_command&userId=${user.id}&new_type=CREATED">CREATED</a>
                                </li>
                                <li><a class="dropdown-item"
                                       href="${abs_path}/controller?command=find_order_command&userId=${user.id}&new_type=PROCESSING">PROCESSING</a>
                                </li>
                                <li><a class="dropdown-item"
                                       href="${abs_path}/controller?command=find_order_command&userId=${user.id}&new_type=ACCEPTED">ACCEPTED</a>
                                </li>
                                <li><a class="dropdown-item"
                                       href="${abs_path}/controller?command=find_order_command&userId=${user.id}&new_type=ON_DELIVERY">ON_DELIVERY</a>
                                </li>
                                <li><a class="dropdown-item"
                                       href="${abs_path}/controller?command=find_order_command&userId=${user.id}&new_type=DELIVERED">DELIVERED</a>
                                </li>
                            </ul>
                        </div>

                    </div>
                </div>
                <br>
                <div class="col-md-12">
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">Number</th>
                            <th scope="col">Status</th>
                            <th scope="col">Data</th>
                            <th scope="col">Items quantity</th>
                            <th scope="col">Details</th>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach items="${orders}" var="order">
                            <tr>
                                <th scope="row">${order.id}</th>
                                <td>${order.status}</td>
                                <td>${order.orderDate}</td>
                                <td>${order.items.size()}</td>
                                <td>
                                    <a href="${abs_path}/controller?command=show_order_details_command&userId=${user.id}&orderId=${order.id}">details</a>
                                </td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                        ${message}
                </div>
            </c:if>

            <br>


            <c:if test="${not empty requestScope.details}">
            <div class="p-3 py-5">
                <div class="d-flex justify-content-between align-items-center experience">
                    <span>My Orders</span>

                    <div class="dropdown">
                        <button class="btn btn-success dropdown-toggle" type="button" id="dropdownMenuButton2"
                                data-bs-toggle="dropdown" aria-expanded="false">
                            Orders
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                            <li><a class="dropdown-item"
                                   href="${abs_path}/controller?command=find_order_command&userId=${user.id}&new_type=CREATED">CREATED</a>
                            </li>
                            <li><a class="dropdown-item"
                                   href="${abs_path}/controller?command=find_order_command&userId=${user.id}&new_type=PROCESSING">PROCESSING</a>
                            </li>
                            <li><a class="dropdown-item"
                                   href="${abs_path}/controller?command=find_order_command&userId=${user.id}&new_type=ACCEPTED">ACCEPTED</a>
                            </li>
                            <li><a class="dropdown-item"
                                   href="${abs_path}/controller?command=find_order_command&userId=${user.id}&new_type=ON_DELIVERY">ON_DELIVERY</a>
                            </li>
                            <li><a class="dropdown-item"
                                   href="${abs_path}/controller?command=find_order_command&userId=${user.id}&new_type=DELIVERED">DELIVERED</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <br>
            <div class="col">
                <label class="labels">Order id:</label>
                <label class="form-control">${order.id}</label>
                <label class="labels">Status:</label>
                <label class="form-control">${order.status}</label>
                <label class="labels">Price:</label>
                <label class="form-control">${order.price}</label>
                <label class="labels">Payment:</label>
                <label class="form-control">${order.payment}</label>
                <label class="labels">Address:</label>
                <label class="form-control">${order.address}</label>
                <br>
                <div class="col-md-12">
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">Instrument id</th>
                            <th scope="col">Name</th>
                            <th scope="col">Brand</th>
                            <th scope="col">Price</th>
                            <th scope="col">Quantity</th>
                        </tr>
                        </thead>
                        <tbody>


                        <c:forEach items="${order.items}" var="item">
                            <tr>
                                <th scope="row">
                                    <a class="link-primary"
                                       href="${abs_path}/controller?command=show_instrument_details_command&instrumentId=${item.key.instrument_id}">${item.key.instrument_id}</a>
                                </th>
                                <td>${item.key.name}</td>
                                <td>${item.key.brand}</td>
                                <td>${item.key.price}</td>
                                <td>${item.value}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                </c:if>
            </div>
        </div>
    </div>
</div>
</body>
</html>
