<%--
  Created by IntelliJ IDEA.
  User: Pavel_Shinkarev
  Date: 09.07.2021
  Time: 22:25
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <c:set var="abs_path">${pageContext.request.contextPath}</c:set>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization"/>
    <fmt:message key="locale.lang" var="curr_lang"/>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous">
    </script>
</head>
<body>
<%@include file="../include/common_imports.jspf" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous">
</script>
<link rel="stylesheet" href="https://www.markuptag.com/bootstrap/5/css/bootstrap.min.css">

<header>
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-light bg-white fixed-top">
        <form action="${abs_path}/controller" method="post">
            <input type="hidden" name="command" value="change_locale">
            <input type="hidden" name="refererCommand" value="${refererCommand}">
            <input type="submit" class="btn btn-warning" value="${curr_lang}">
        </form>
        <div class="container-fluid">
            <button
                    class="navbar-toggler"
                    type="button"
                    data-mdb-toggle="collapse"
                    data-mdb-target="#navbarExample01"
                    aria-controls="navbarExample01"
                    aria-expanded="false"
                    aria-label="Toggle navigation">
                <i class="fas fa-bars"></i>
            </button>
            <div class="collapse navbar-collapse" id="navbarExample01">

                <div id="logout" >
                    <c:if test="${not empty sessionScope.user}">

                        <div class="collapse navbar-collapse">
                            <form>
                                <ul class="navbar-nav me-auto mb-2 mb-lg-0">

                                    <li class="nav-item active">
                                        <div class="dropdown">
                                            <a class="btn btn-secondary" href="${abs_path}/pages/common/main_page.jsp"><fmt:message key="page.header.home"/></a>
                                        </div>
                                    </li>


                                    <li class="nav-item">
                                        <div class="dropdown" >
                                            <button class="btn btn-secondary dropdown-toggle" type="button"
                                                    id="dropdownMenuButton2" data-bs-toggle="dropdown"
                                                    aria-expanded="false">
                                                <fmt:message key="page.header.user_tool"/>
                                            </button>
                                            <ul class="dropdown-menu dropdown-menu-sm-start"
                                                aria-labelledby="d">

                                                <li><a class="dropdown-item"  href="${abs_path}/pages/admin/find_user.jsp"><fmt:message key="page.header.find_user"/></a></li>
                                                <li><a class="dropdown-item" href="${abs_path}/pages/admin/add_user.jsp"><fmt:message key="page.header.add_user"/></a></li>
                                                <li>
                                                    <hr class="dropdown-divider">
                                                </li>

                                                <li><a class="dropdown-item btn-outline-secondary"
                                                       href="${abs_path}/controller?command=show_all_users"><fmt:message
                                                        key="page.admin.show_all_users"/></a>
                                                </li>
                                            </ul>
                                        </div>
                                    </li>


                                    <li class="nav-item">
                                        <div class="dropdown">
                                            <button class="btn btn-secondary dropdown-toggle" type="button"
                                                    id="dropdownMenuButton3" data-bs-toggle="dropdown"
                                                    aria-expanded="false">
                                                <fmt:message key="page.header.instrument_tool"/>
                                            </button>
                                            <ul class="dropdown-menu dropdown-menu-sm-start"
                                                aria-labelledby="d">
                                                <li><a class="dropdown-item"
                                                       href="${abs_path}/pages/admin/add_instrument.jsp"><fmt:message key="page.header.add_instrument"/></a>
                                                </li>
                                                <li>
                                                    <hr class="dropdown-divider">
                                                </li>

                                                <li><a class="dropdown-item"
                                                       href="${abs_path}/controller?command=show_type_instrument&instrumentType=GUITARS">
                                                    <fmt:message key="page.header.show_guitars"/></a></li>
                                                <li><a class="dropdown-item"
                                                       href="${abs_path}/controller?command=show_type_instrument&instrumentType=DRUMS">
                                                    <fmt:message key="page.header.show_drums"/></a></li>
                                                <li><a class="dropdown-item"
                                                       href="${abs_path}/controller?command=show_type_instrument&instrumentType=KEYBOARDS">
                                                    <fmt:message key="page.header.show_keyboards"/></a></li>
                                                <li><a class="dropdown-item"
                                                       href="${abs_path}/controller?command=show_type_instrument&instrumentType=OTHER">
                                                    <fmt:message key="page.header.show_other"/></a></li>
                                                <li>
                                                    <hr class="dropdown-divider">
                                                </li>

                                                <li><a class="dropdown-item btn-outline-secondary"
                                                       href="${abs_path}/controller?command=show_all_instruments_command"><fmt:message key="page.admin.show_all_instruments"/></a>
                                                </li>
                                            </ul>
                                        </div>
                                    </li>

                                    <li class="nav-item">
                                        <div class="dropdown">
                                            <button class="btn btn-secondary dropdown-toggle" type="button"
                                                    id="dropdownMenuButton4" data-bs-toggle="dropdown"
                                                    aria-expanded="false">
                                                <fmt:message key="page.header.order_tool"/>
                                            </button>
                                            <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                                                <li><a class="dropdown-item"
                                                       href="${abs_path}/controller?command=find_order_by_type&new_type=CREATED"><fmt:message key="page.header.created_order"/></a>
                                                </li>
                                                <li><a class="dropdown-item"
                                                       href="${abs_path}/controller?command=find_order_by_type&new_type=PROCESSING"><fmt:message key="page.header.created_processing"/></a>
                                                </li>
                                                <li><a class="dropdown-item"
                                                       href="${abs_path}/controller?command=find_order_by_type&new_type=ACCEPTED"><fmt:message key="page.header.accepted"/></a>
                                                </li>
                                                <li><a class="dropdown-item"
                                                       href="${abs_path}/controller?command=find_order_by_type&new_type=ON_DELIVERY"><fmt:message key="page.header.on_delivery"/></a>
                                                </li>
                                                <li><a class="dropdown-item"
                                                       href="${abs_path}/controller?command=find_order_by_type&new_type=DELIVERED"><fmt:message key="page.header.delivered"/></a>
                                                </li>

                                                <li>
                                                    <hr class="dropdown-divider">
                                                </li>

                                                <li><a class="dropdown-item btn-outline-secondary"
                                                       href="${abs_path}/controller?command=show_all_orders_command"><fmt:message key="page.header.show_all_orders"/></a>
                                                </li>
                                            </ul>
                                        </div>
                                    </li>


                                </ul>
                            </form>
                        </div>
                    </c:if>
                </div>
            </div>
            <form action="${abs_path}/controller" method="post">
                <input type="hidden" name="command" value="logout">
                <input type="submit" class="btn btn-warning" value=<fmt:message key="page.header.logout"/>>
            </form>
        </div>
    </nav>
    <br>
    <br>
    <br>
    <br>
</header>
</body>
</html>