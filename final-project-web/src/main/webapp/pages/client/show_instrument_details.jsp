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
                            <form method="post" action="${abs_path}/controller?command=by_now_command">
                                <input type="hidden" name="instrumentId" value="${instrument.instrument_id}">
                                <button class="btn btn-outline-dark">Buy Now</button>
                            </form>
                            <c:if test="${not requestScope.containsValue(instrument.instrument_id)}">
                                <form method="post" action="${abs_path}/controller?command=add_to_bucket_command">
                                    <button class="btn btn-dark">Add to Bucket</button>
                                </form>
                            </c:if>
                            <c:if test="${requestScope.containsValue(instrument.instrument_id)}">
                                <form method="post" action="${abs_path}/controller?command=add_to_bucket_command">
                                    <button class="btn btn-success">Added to bucket</button>
                                </form>
                            </c:if>
                        </c:if>
                        <c:if test="${empty sessionScope.user}">
                            <form method="post" action="${abs_path}/controller?command=to_login_page_command">
                                <button class="btn btn-outline-dark">Buy Now</button>
                            </form>
                            <form method="post" action="${abs_path}/controller?command=to_login_page_command">
                                <button class="btn btn-dark">Add to Basket</button>
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