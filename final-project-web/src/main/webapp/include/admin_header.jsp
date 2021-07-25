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
                                            <a class="btn btn-secondary" href="${abs_path}/pages/login.jsp">Home</a>
                                        </div>
                                    </li>


                                    <li class="nav-item">
                                        <div class="dropdown" >
                                            <button class="btn btn-secondary dropdown-toggle" type="button"
                                                    id="dropdownMenuButton2" data-bs-toggle="dropdown"
                                                    aria-expanded="false">
                                                Users Tool
                                            </button>
                                            <ul class="dropdown-menu dropdown-menu-sm-start"
                                                aria-labelledby="d">

                                                <li><a class="dropdown-item" href="${abs_path}/pages/find_user.jsp">Find user</a></li>
                                                <li><a class="dropdown-item" href="#">Add user</a></li>
                                                <li><a class="dropdown-item" href="#">Something else here</a></li>

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
                                                Instruments Tool
                                            </button>
                                            <ul class="dropdown-menu dropdown-menu-sm-start"
                                                aria-labelledby="d">

                                                <li><a class="dropdown-item" href="#">Add instrument</a></li>
                                                <li><a class="dropdown-item" href="#">Remove instrumet</a></li>
                                                <li><a class="dropdown-item" href="#">Something else here</a></li>

                                                <li>
                                                    <hr class="dropdown-divider">
                                                </li>

                                                <li><a class="dropdown-item btn-outline-secondary"
                                                       href="#">Show all instrument</a>
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