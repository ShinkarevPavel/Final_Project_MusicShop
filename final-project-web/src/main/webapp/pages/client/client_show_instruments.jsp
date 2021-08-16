<%--
  Created by IntelliJ IDEA.
  User: Pavel_Shinkarev
  Date: 01.08.2021
  Time: 23:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="abs_path">${pageContext.request.contextPath}</c:set>
<fmt:setLocale value="${curr_lang}" scope="request"/>
<fmt:setBundle basename="localization"/>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://www.markuptag.com/bootstrap/5/css/bootstrap.min.css">

</head>
<body style="background-image: url(https://proguitarworld.ru/wp-content/uploads/2017/09/guitar-shop.jpg);
background-repeat: no-repeat;
background-position: center center;
background-size: cover">
<%@include file="/include/header.jsp" %>

<div class="container">

    <c:if test="${empty instruments}">
        <section class="vh-100">
            <div class="row">
                <div class="col-md-3 offset-md-4">
                    <div class="login-form bg-light mt-4 p-4">
                        <h4 class="offset-xxl-2"><fmt:message key="page.instruments.message"/></h4>
                        <h4 class="offset-xxl-2">${instrument_message}</h4>
                    </div>
                </div>
            </div>
        </section>
    </c:if>
    <div class="row row-cols-2 row-cols-lg-3">
        <c:forEach items="${instruments}" var="instrument">
            <div class="col">
                <div class="card mt-3" style="background: #e2e3e5">
                    <div class="product-1 align-items-center p-2 text-center">
                        <img src="${instrument.image[0]}"
                             class="rounded"
                             width="260">

                        <div class="product-1 align-items-center p-2 text-center">
                        <span style="color: black">
                            <h5>
                                <i class="bx bxs-star" style="color: orange"></i>
                                    ${instrument.rating}
                                    <span style="font-family: Cambria">${instrument.name}<span/>
                            </h5>
                        </span>
                        </div>

                        <div class="mt-3 info">
                            <h5 class="text1 d-block" style="color: red">
                                    ${instrument.instrumentStatus}
                            </h5>
                            <span class="text1">
                                    ${instrument.country}
                            </span>
                        </div>
                        <div class=" cost mt-3 text-dark">
                            <h4>
                                $${instrument.price}
                            </h4>
                        </div>
                    </div>
                    <form method="post" action="${abs_path}/controller?command=show_instrument_details_command&instrumentId=${instrument.instrument_id}">
                        <div class="product-1 align-items-center p-2 text-center">
                            <button type="submit" class="btn btn-outline-secondary"><fmt:message key="page.item_details.more_details"/></button>
                        </div>
                    </form>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<c:if test="${not empty instruments}">
    <nav aria-label="Page navigation area">
        <ul class="pagination justify-content-center">
            <li class="page-item ${pageable.isFirstPage() ? 'disabled': ''}">
                <a class="page-link"
                   href="${abs_path}/controller?command=show_instrument_by_type_command&instrument_type=${instruments[0].type}&page=${pageable.currentPage - 1}" tabindex="-1"><fmt:message key="page.previous"/></a>
            </li>
            <c:forEach var="i" begin="1" end="${pageable.pageCount()}">
                <li class="page-item ${pageable.currentPage eq i ? 'active': ''}">
                    <a class="page-link" href="${abs_path}/controller?command=show_instrument_by_type_command&instrument_type=${instruments[0].type}&page=${i}">${i}</a></li>
            </c:forEach>
            <li class="page-item ${pageable.isLastPage() ? 'disabled': ''}">
                <a class="page-link"
                   href="${abs_path}/controller?command=show_instrument_by_type_command&instrument_type=${instruments[0].type}&page=${pageable.currentPage + 1}"><fmt:message key="page.next"/></a>
            </li>
        </ul>
    </nav>
</c:if>

</body>
</html>
