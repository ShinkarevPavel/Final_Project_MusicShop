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
<%--    <style>--%>
<%--        .rating {--%>
<%--            float:left;--%>
<%--            border:none;--%>
<%--        }--%>
<%--        .rating:not(:checked) > input {--%>
<%--            position:absolute;--%>
<%--            top:-9999px;--%>
<%--            clip:rect(0, 0, 0, 0);--%>
<%--        }--%>
<%--        .rating:not(:checked) > label {--%>
<%--            float:right;--%>
<%--            width:1em;--%>
<%--            padding:0 .1em;--%>
<%--            overflow:hidden;--%>
<%--            white-space:nowrap;--%>
<%--            cursor:pointer;--%>
<%--            font-size:200%;--%>
<%--            line-height:1.2;--%>
<%--            color:#ddd;--%>
<%--        }--%>
<%--        .rating:not(:checked) > label:before {--%>
<%--            content:'★ ';--%>
<%--        }--%>
<%--        .rating > input:checked ~ label {--%>
<%--            color: #f70;--%>
<%--        }--%>
<%--        .rating:not(:checked) > label:hover, .rating:not(:checked) > label:hover ~ label {--%>
<%--            color: gold;--%>
<%--        }--%>
<%--        .rating > input:checked + label:hover, .rating > input:checked + label:hover ~ label, .rating > input:checked ~ label:hover, .rating > input:checked ~ label:hover ~ label, .rating > label:hover ~ input:checked ~ label {--%>
<%--            color: #ea0;--%>
<%--        }--%>
<%--        .rating > label:active {--%>
<%--            position: relative;--%>
<%--        }--%>
<%--    </style>--%>
<%--    <style>--%>
<%--        body {--%>
<%--            background-color: #ecedee--%>
<%--        }--%>

<%--        .card {--%>
<%--            border: none;--%>
<%--            overflow: hidden--%>
<%--        }--%>

<%--        .thumbnail_images ul {--%>
<%--            list-style: none;--%>
<%--            justify-content: center;--%>
<%--            display: flex;--%>
<%--            align-items: center;--%>
<%--            margin-top: 10px--%>
<%--        }--%>

<%--        .thumbnail_images ul li {--%>
<%--            margin: 5px;--%>
<%--            padding: 10px;--%>
<%--            border: 2px solid #eee;--%>
<%--            cursor: pointer;--%>
<%--            transition: all 0.5s--%>
<%--        }--%>

<%--        .thumbnail_images ul li:hover {--%>
<%--            border: 2px solid #000--%>
<%--        }--%>

<%--        .main_image {--%>
<%--            display: flex;--%>
<%--            justify-content: center;--%>
<%--            align-items: center;--%>
<%--            border-bottom: 1px solid #eee;--%>
<%--            height: 400px;--%>
<%--            width: 100%;--%>
<%--            overflow: hidden--%>
<%--        }--%>

<%--        .heart {--%>
<%--            height: 29px;--%>
<%--            width: 29px;--%>
<%--            background-color: #eaeaea;--%>
<%--            border-radius: 50%;--%>
<%--            display: flex;--%>
<%--            justify-content: center;--%>
<%--            align-items: center--%>
<%--        }--%>

<%--        .content p {--%>
<%--            font-size: 12px--%>
<%--        }--%>

<%--        .ratings span {--%>
<%--            font-size: 14px;--%>
<%--            margin-left: 12px--%>
<%--        }--%>

<%--        .colors {--%>
<%--            margin-top: 5px--%>
<%--        }--%>

<%--        .colors ul {--%>
<%--            list-style: none;--%>
<%--            display: flex;--%>
<%--            padding-left: 0px--%>
<%--        }--%>

<%--        .colors ul li {--%>
<%--            height: 20px;--%>
<%--            width: 20px;--%>
<%--            display: flex;--%>
<%--            border-radius: 50%;--%>
<%--            margin-right: 10px;--%>
<%--            cursor: pointer--%>
<%--        }--%>

<%--        .colors ul li:nth-child(1) {--%>
<%--            background-color: #6c704d--%>
<%--        }--%>

<%--        .colors ul li:nth-child(2) {--%>
<%--            background-color: #96918b--%>
<%--        }--%>

<%--        .colors ul li:nth-child(3) {--%>
<%--            background-color: #68778e--%>
<%--        }--%>

<%--        .colors ul li:nth-child(4) {--%>
<%--            background-color: #263f55--%>
<%--        }--%>

<%--        .colors ul li:nth-child(5) {--%>
<%--            background-color: black--%>
<%--        }--%>

<%--        .right-side {--%>
<%--            position: relative--%>
<%--        }--%>

<%--        .search-option {--%>
<%--            position: absolute;--%>
<%--            background-color: #000;--%>
<%--            overflow: hidden;--%>
<%--            align-items: center;--%>
<%--            color: #fff;--%>
<%--            width: 200px;--%>
<%--            height: 200px;--%>
<%--            border-radius: 49% 51% 50% 50% / 68% 69% 31% 32%;--%>
<%--            left: 30%;--%>
<%--            bottom: -250px;--%>
<%--            transition: all 0.5s;--%>
<%--            cursor: pointer--%>
<%--        }--%>

<%--        .search-option .first-search {--%>
<%--            position: absolute;--%>
<%--            top: 20px;--%>
<%--            left: 90px;--%>
<%--            font-size: 20px;--%>
<%--            opacity: 1000--%>
<%--        }--%>

<%--        .search-option .inputs {--%>
<%--            opacity: 0;--%>
<%--            transition: all 0.5s ease;--%>
<%--            transition-delay: 0.5s;--%>
<%--            position: relative--%>
<%--        }--%>

<%--        .search-option .inputs input {--%>
<%--            position: absolute;--%>
<%--            top: 200px;--%>
<%--            left: 30px;--%>
<%--            padding-left: 20px;--%>
<%--            background-color: transparent;--%>
<%--            width: 300px;--%>
<%--            border: none;--%>
<%--            color: #fff;--%>
<%--            border-bottom: 1px solid #eee;--%>
<%--            transition: all 0.5s;--%>
<%--            z-index: 10--%>
<%--        }--%>

<%--        .search-option .inputs input:focus {--%>
<%--            box-shadow: none;--%>
<%--            outline: none;--%>
<%--            z-index: 10--%>
<%--        }--%>

<%--        .search-option:hover {--%>
<%--            border-radius: 0px;--%>
<%--            width: 100%;--%>
<%--            left: 0px--%>
<%--        }--%>

<%--        .search-option:hover .inputs {--%>
<%--            opacity: 1--%>
<%--        }--%>

<%--        .search-option:hover .first-search {--%>
<%--            left: 27px;--%>
<%--            top: 25px;--%>
<%--            font-size: 15px--%>
<%--        }--%>

<%--        .search-option:hover .inputs input {--%>
<%--            top: 20px--%>
<%--        }--%>

<%--        .search-option .share {--%>
<%--            position: absolute;--%>
<%--            right: 20px;--%>
<%--            top: 22px--%>
<%--        }--%>

<%--        .buttons .btn {--%>
<%--            height: 50px;--%>
<%--            width: 150px;--%>
<%--            border-radius: 0px !important--%>
<%--        }--%>
<%--    </style>--%>
    <link type="text/css" rel="stylesheet" href="${abs_path}/music_css/instrumentsStyle.css"/>
    <link href="${abs_path}/music_css/rating.css" rel="stylesheet"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css" rel="stylesheet">

    <script type="text/javascript" src=""></script>
    <script type="text/javascript"
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
</head>

<%@include file="/include/header.jsp" %>

<body oncontextmenu="return false" class="snippet-body">
<div class="container mt-5 mb-5">
    <div class="card">
        <div class="row g-0">
            <div class="col-md-6 border-end">
                <div class="d-flex flex-column justify-content-center">
                    <div class="main_image">
                        <img src="${instrument.image[0]}" id="main_product_image" width="350">
                    </div>
                    <div class="thumbnail_images">
                        <ul id="thumbnail">
                            <c:forEach items="${instrument.image}" var="image">
                                <li><img onclick="changeImage(this)" src="${image}" width="70">
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>




            <div class="col-md-6">
                <div class="p-3 right-side">
                    <div class="d-flex justify-content-between align-items-center">
                        <h3>${instrument.name}</h3>
                        <span class="heart">${instrument.instrument_id}</span>
                    </div>
                    <div class="mt-2 pr-3 content">
                        <p>${instrument.description}</p>
                    </div>
                    <h3>$${instrument.price}</h3>




                    <div class="ratings d-flex flex-row align-items-center">
                        <c:if test="${empty message}">
                            <c:if test="${not empty instrument}">
                                <form action="${abs_path}/controller?command=set_instrument_rating_command"
                                      class="rating"
                                      method="post">
                                    <input type="submit" id="star5" name="rating" value="5"/>
                                    <label for="star5">5 stars</label>
                                    <input type="submit" id="star4" name="rating" value="4"/>
                                    <label for="star4">4 stars</label>
                                    <input type="submit" id="star3" name="rating" value="3"/>
                                    <label for="star3">3 stars</label>
                                    <input type="submit" id="star2" name="rating" value="2"/>
                                    <label for="star2">2 stars</label>
                                    <input type="submit" id="star1" name="rating" value="1"/>
                                    <label for="star1">1 star</label>
                                </form>
                            </c:if>
                        </c:if>
                        <span>${message}</span>
                    </div>

                    <div class="mt-5">
                        <span class="fw-bold">Country: ${instrument.country}</span>
                    </div>





                    <div class="buttons d-flex flex-row mt-5 gap-3">
                        <c:if test="${not empty user}">
                            <form method="post" action="${abs_path}/controller?command=by_now_command&instrumentId=${instrument.instrument_id}">
                                <button class="btn btn-outline-dark"><fmt:message key="page.item.details.by_now"/></button>
                            </form>
                            <c:if test="${not requestScope.containsValue(instrument.instrument_id)}">
                                <form method="post" action="${abs_path}/controller?command=add_to_bucket_command">
                                    <button class="btn btn-dark"><fmt:message key="page.item.details.add_to_cart"/></button>
                                </form>
                            </c:if>
                            <c:if test="${requestScope.containsValue(instrument.instrument_id)}">
                                <form method="post" action="${abs_path}/controller?command=add_to_bucket_command">
                                    <button class="btn btn-success"><fmt:message key="page.item.details.add_to_cart"/></button>
                                </form>
                            </c:if>
                        </c:if>
                        <c:if test="${empty sessionScope.user}">
                            <form method="post" action="${abs_path}/controller?command=to_login_page_command">
                                <button class="btn btn-outline-dark"><fmt:message key="page.item.details.by_now"/></button>
                            </form>
                            <form method="post" action="${abs_path}/controller?command=to_login_page_command">
                                <button class="btn btn-dark"><fmt:message key="page.item.details.add_to_cart"/></button>
                            </form>
                        </c:if>
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