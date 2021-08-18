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
    <style>
        #number1 {
        width: 5em;
        height: 30px;
    }</style>

    <style>
        * {
            margin: 0;
            padding: 0;
            -webkit-font-smoothing: antialiased;
            -webkit-text-shadow: rgba(0, 0, 0, .01) 0 0 1px;
            text-shadow: rgba(0, 0, 0, .01) 0 0 1px
        }

        body {
            font-family: 'Rubik', sans-serif;
            font-size: 14px;
            font-weight: 400;
            background: #E0E0E0;
            color: #000000
        }

        ul {
            list-style: none;
            margin-bottom: 0px
        }

        .button {
            display: inline-block;
            background: #0e8ce4;
            border-radius: 5px;
            height: 48px;
            -webkit-transition: all 200ms ease;
            -moz-transition: all 200ms ease;
            -ms-transition: all 200ms ease;
            -o-transition: all 200ms ease;
            transition: all 200ms ease
        }

        .button a {
            display: block;
            font-size: 18px;
            font-weight: 400;
            line-height: 48px;
            color: #FFFFFF;
            padding-left: 35px;
            padding-right: 35px
        }

        .button:hover {
            opacity: 0.8
        }

        .cart_section {
            width: 100%;
            padding-top: 93px;
            padding-bottom: 111px
        }

        .cart_title {
            font-size: 30px;
            font-weight: 500
        }

        .cart_items {
            margin-top: 8px
        }

        .cart_list {
            border: solid 1px #e8e8e8;
            box-shadow: 0px 1px 5px rgba(0, 0, 0, 0.1);
            background-color: #fff
        }

        .cart_item {
            width: 100%;
            padding: 15px;
            padding-right: 46px
        }

        .cart_item_image {
            width: 133px;
            height: 133px;
            float: left
        }

        .cart_item_image img {
            max-width: 100%
        }

        .cart_item_info {
            width: calc(100% - 133px);
            float: left;
            padding-top: 18px
        }

        .cart_item_name {
            margin-left: 7.53%
        }

        .cart_item_title {
            font-size: 14px;
            font-weight: 400;
            color: rgba(0, 0, 0, 0.5)
        }

        .cart_item_text {
            font-size: 18px;
            margin-top: 35px
        }

        .cart_item_text span {
            display: inline-block;
            width: 20px;
            height: 20px;
            border-radius: 50%;
            margin-right: 11px;
            -webkit-transform: translateY(4px);
            -moz-transform: translateY(4px);
            -ms-transform: translateY(4px);
            -o-transform: translateY(4px);
            transform: translateY(4px)
        }

        .cart_item_price {
            text-align: right
        }

        .cart_item_total {
            text-align: right
        }

        .order_total {
            width: 100%;
            height: 60px;
            margin-top: 30px;
            border: solid 1px #e8e8e8;
            box-shadow: 0px 1px 5px rgba(0, 0, 0, 0.1);
            padding-right: 46px;
            padding-left: 15px;
            background-color: #fff
        }

        .order_total_title {
            display: inline-block;
            font-size: 14px;
            color: rgba(0, 0, 0, 0.5);
            line-height: 60px
        }

        .order_total_amount {
            display: inline-block;
            font-size: 18px;
            font-weight: 500;
            margin-left: 26px;
            line-height: 60px
        }

        .cart_buttons {
            margin-top: 60px;
            text-align: right
        }

        .cart_button_clear {
            display: inline-block;
            border: none;
            font-size: 18px;
            font-weight: 400;
            line-height: 48px;
            color: rgba(0, 0, 0, 0.5);
            background: #FFFFFF;
            border: solid 1px #b2b2b2;
            padding-left: 35px;
            padding-right: 35px;
            outline: none;
            cursor: pointer;
            margin-right: 26px
        }

        .cart_button_clear:hover {
            border-color: #0e8ce4;
            color: #0e8ce4
        }

        .cart_button_checkout {
            display: inline-block;
            border: none;
            font-size: 18px;
            font-weight: 400;
            line-height: 48px;
            color: #FFFFFF;
            padding-left: 35px;
            padding-right: 35px;
            outline: none;
            cursor: pointer;
            vertical-align: top
        }
    </style>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/css/bootstrap.min.css" rel="stylesheet">
<%--    <link href="${abs_path}/pages/music_css/busket.css" rel="stylesheet"/>--%>
<%--    <link href="${abs_path}/pages/music_css/cart_input_size.css" id="number1" rel="stylesheet"/>--%>

    <script type="text/javascript" src=""></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script type="text/javascript"
            src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/js/bootstrap.min.js"></script>
</head>

<%@include file="/include/header.jsp" %>

<body oncontextmenu="return false" class="snippet-body">
<div class="cart_section">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-10 offset-lg-1">
                <div class="cart_container">
                    <div class="cart_title"><fmt:message key="page.cart.your_cart"/><small> (${items.size()} <fmt:message key="page.cart.text"/>) </small>
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
                                            <div class="cart_item_title"><fmt:message key="page.cart.remmove_message"/></div>
                                            <form action="${abs_path}/controller?command=remove_from_cart_command"
                                                  method="post">
                                                <div class="cart_item_text">
                                                    <input type="hidden" name="instrumentId"
                                                           value="${item.key.instrument_id}">
                                                    <button type="submit" class="btn btn-outline-success"><fmt:message key="page.cart.remmove"/>
                                                    </button>
                                                </div>
                                            </form>
                                        </div>

                                        <div class="cart_item_quantity cart_info_col">
                                            <div class="cart_item_title"><fmt:message key="page.registration.quantity"/></div>
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
                                            <div class="cart_item_title"><fmt:message key="page.admin_page.add_instrument.price"/></div>
                                            <div class="cart_item_text">$${item.key.price}</div>
                                        </div>
                                        <div class="cart_item_total cart_info_col">
                                            <div class="cart_item_title"><fmt:message key="page.cart.total"/></div>
                                            <div class="cart_item_text">$${item.key.price*item.value}</div>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </c:forEach>

                    <div class="order_total">
                        <div class="order_total_content text-sm-right">
                            <div class="order_total_title"><fmt:message key="page.cart.order_total"/></div>
                            <input type="hidden" form="order" name="total" value="${total}">
                            <div class="order_total_amount">$${total}</div>
                        </div>
                    </div>
                    <div class="cart_buttons">
                        <div class="btn-group">
                            <form method="post" action="${abs_path}/controller?command=to_main_page">
                                <button type="submit" class="button cart_button_clear"><fmt:message key="page.cart.continiue"/></button>
                            </form>
                            <c:if test="${not empty items}">
                                <form method="post" id="order" action="${abs_path}/controller?command=order_processing_command">
                                    <button type="submit" class="button cart_button_checkout"><fmt:message key="page.cart.to_order"/></button>
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
