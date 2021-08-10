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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/pages/busket.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/pages/cart_input_size.css" id="number" rel="stylesheet"/>

    <script type="text/javascript" src=""></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script type="text/javascript"
            src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/js/bootstrap.min.js"></script>
</head>


<body oncontextmenu="return false" class="snippet-body">
<%@include file="/include/header.jsp" %>
<div class="cart_section">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-10 offset-lg-1">
                <div class="cart_container">
                    <div class="cart_title">Your cart<small> (${items.size()} item in your
                        cart) </small>
                    </div>

                    <c:forEach items="${items}" var="item">
                        <div class="cart_items">
                            <ul class="cart_list">
                                <li class="cart_item clearfix">
                                    <div class="cart_item_image"><img src="${item.key.image[0]}" alt="" width="130"></div>
                                    <div class="cart_item_info d-flex flex-md-row flex-column justify-content-between">
                                        <div class="cart_item_name cart_info_col">
                                            <div class="cart_item_title">Name</div>
                                            <div class="cart_item_text">
                                                <div class="badge bg-secondary text-wrap" style="width: 16rem;">
                                                    ${item.key.name}
                                                </div>
                                            </div>
                                        </div>
                                        <div class="cart_item_color cart_info_col">
                                            <div class="cart_item_title">You can remove it</div>
                                            <form action="${abs_path}/controller?command=remove_from_cart_command"
                                                  method="post">
                                                <div class="cart_item_text">
                                                    <input type="hidden" name="instrumentId"
                                                           value="${item.key.instrument_id}">
                                                    <button type="submit" class="btn btn-outline-success">Remove
                                                    </button>
                                                </div>
                                            </form>
                                        </div>

                                        <div class="cart_item_quantity cart_info_col">
                                            <div class="cart_item_title">Quantity</div>
                                            <div class="cart_item_text">
                                                <form method="post" action="${abs_path}/controller?command=quantity_control_command">
                                                    <input type="hidden" name="instrumentId" value="${item.key.instrument_id}" form="order" multiple>
                                                    <input type="hidden" name="quantity" value="${item.value}" form="order" multiple>
                                                    <button type="submit" style="border: none">
                                                        <input type="hidden" name="instrumentId" value="${item.key.instrument_id}">
                                                        <input class="form-control" type="number" name="quantity" value="${item.value}" min="1" id="number1">
                                                    </button>
                                                </form>
                                            </div>
                                        </div>

                                        <div class="cart_item_price cart_info_col">
                                            <div class="cart_item_title">Price</div>
                                            <div class="cart_item_text">$${item.key.price}</div>
                                        </div>
                                        <div class="cart_item_total cart_info_col">
                                            <div class="cart_item_title">Total</div>
                                            <div class="cart_item_text">$${item.key.price*item.value}</div>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </c:forEach>

                    <div class="order_total">
                        <div class="order_total_content text-sm-right">
                            <div class="order_total_title">Order Total:</div>
                            <input type="hidden" form="order" name="total" value="${total}">
                            <div class="order_total_amount">$${total}</div>
                        </div>
                    </div>
                    <div class="cart_buttons">
                        <div class="btn-group">
                            <form method="post" action="${abs_path}/pages/common/main_page.jsp">
                                <button type="submit" class="button cart_button_clear">Continue Shopping</button>
                            </form>
                            <c:if test="${not empty items}">
                                <form method="post" id="order" action="${abs_path}/controller?command=order_processing_command">
                                    <button type="submit" class="button cart_button_checkout">To Order</button>
                                </form>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript"></script>

</body>

</html>
