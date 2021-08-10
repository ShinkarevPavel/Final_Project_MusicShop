<%--
  Created by IntelliJ IDEA.
  User: Pavel_Shinkarev
  Date: 02.08.2021
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
          crossorigin="anonymous">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/pages/instrumentsStyle.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/pages/ratingStyle.css" rel="stylesheet"/>
    <script type="text/javascript" src=""></script>
    <script type="text/javascript"
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
</head>

<%@include file="/include/header.jsp" %>

<body oncontextmenu="return false" class="snippet-body">
<div class="container mt-5 mb-5">
    <div class="card p-3">
        <div class="row g-0">
            <div class="col-md-6 border-end">
                <div class="d-flex flex-column justify-content-center">

                    <div class="align-content-lg-center">
                        <table class="table table-success table-striped">
                            <thead>
                            <tr>
                                <th scope="col">id</th>
                                <th scope="col">Name</th>
                                <th scope="col">Brand</th>
                                <th scope="col">Price</th>
                                <th scope="col">Quantity</th>
                            </tr>
                            </thead>
                            <c:forEach items="${items}" var="item">
                                <input type="hidden" form="order" name="instrumentId" value="${item.key.instrument_id}">
                                <input type="hidden" form="order" name="quantity" value="${item.value}">
                                <tbody>
                                <tr>
                                    <th scope="row">${item.key.instrument_id}</th>
                                    <td>${item.key.name}</td>
                                    <td>${item.key.brand}</td>
                                    <td>${item.key.price}</td>
                                    <td>${item.value}</td>
                                </tr>
                                </tbody>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>


            <div class="col-md-6">
                <div class="p-3 right-side">
                    <div class="card p-5">
                        <div>
                            <h4 class="heading">Your Order</h4>
                            <p class="text">Please make the payment to start enjoying all the features of our
                                premium plan as soon as possible</p>
                        </div>
                        <div class="pricing p-3 rounded mt-4 d-flex justify-content-between">
                            <div class="images d-flex flex-row align-items-center"><img
                                    src="https://img.mobigama.net/app/6722-real_guitar/1_real_guitar.png"
                                    class="rounded" width="60">
                                <div class="d-flex flex-column ml-4">
                                    <span class="plan">Total</span></div>
                            </div>
                            <!--pricing table-->
                            <div class="d-flex flex-row align-items-center">
                                <span class="amount ml-1 mr-1">$${total}</span>
                                <input type="hidden" form="order" name="total" value="${total}">
                            </div> <!-- /pricing table-->
                        </div>
                        <span class="detail mt-5">Payment details</span>
                        <div class="credit rounded mt-4 d-flex justify-content-between align-items-center">
                            <div class="d-flex flex-row align-items-center"><img
                                    src="https://i2.wp.com/itc.ua/wp-content/uploads/2015/02/MasterCard.png"
                                    class="rounded" width="70">
                                <div class="d-flex flex-column ml-3"><span
                                        class="business">Credit Card to Courier</span> <span
                                        class="plan">1234 XXXX XXXX 2570</span></div>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" form="order" type="radio" name="payment"
                                       value="Cart to courier">
                            </div>

                        </div>
                        <div class="credit rounded mt-2 d-flex justify-content-between align-items-center">
                            <div class="d-flex flex-row align-items-center"><img
                                    src="https://assets.materialup.com/uploads/bcf6dd06-7117-424f-9a6e-4bb795c8fb4d/preview.png"
                                    class="rounded"
                                    width="70">
                                <div class="d-flex flex-column ml-3">
                                    <span class="business">Cash to Courier</span>
                                    <span class="plan">$100</span>
                                </div>
                            </div>

                            <div class="form-check">
                                <input class="form-check-input" form="order" type="radio" name="payment"
                                       value="Cash to courier" checked>
                            </div>


                        </div>
                        <h6 class="mt-4 text-secondary">Delivery address</h6>
                        <div class="email mt-2">
                            <input type="text" form="order" name="address" class="form-control email-text"
                                                       placeholder="Delivery Address" required>
                        </div>
                        <div class="mt-3">
                            <form method="post" id="order" action="${abs_path}/controller?command=create_order_command">
                                <button class="btn btn-secondary btn-block payment-button">Proceed to payment
                                    <i class="fa fa-long-arrow-right"></i>
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript">function changeImage(element) {
    var main_prodcut_image = document.getElementById('main_product_image');
    main_prodcut_image.src = element.src;

}</script>
</body>
</html>
