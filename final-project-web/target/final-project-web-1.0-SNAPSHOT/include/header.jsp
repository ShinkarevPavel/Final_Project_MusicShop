<%--
  Created by IntelliJ IDEA.
  User: Pavel_Shinkarev
  Date: 09.07.2021
  Time: 22:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<c:set var="abs_path">${pageContext.request.contextPath}</c:set>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization"/>
<fmt:message key="locale.lang" var="curr_lang"/>
<html>
<head>

    <link rel="stylesheet"
          href=
                  "https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/css/bootstrap.min.css"
          integrity=
                  "sha384-r4NyP46KrjDleawBgD5tp8Y7UzmLA05oM1iAEQ17CSuDqnUK2+k9luXQOfXJCJ4I"
          crossorigin="anonymous" />
    <script src=
                    "https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity=
                    "sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous">
    </script>
    <script src=
                    "https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/js/bootstrap.min.js"
            integrity=
                    "sha384-oesi62hOLfzrys4LxRF63OJCXdXDipiYWBnvTl9Y9/TRlw5xlKIEHpNyvvDShgf/"
            crossorigin="anonymous">
    </script>




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


<header class="p-3 mb-3 border-bottom" style="background-color: #bcc3c9">

    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li>
                    <form action="${abs_path}/controller?command=change_locale" method="post" id="locale">
                        <input type="hidden" form="locale" name="command" value="change_locale">
                        <input type="hidden" form="locale" name="refererCommand" value="${refererCommand}">
                        <input type="submit" form="locale"  class="btn btn-outline-light me-2" value="${curr_lang}">
                    </form>
                </li>
                <li><a href="${abs_path}/pages/common/main_page.jsp" class="nav-link px-2 link-secondary"><fmt:message key="page.header.home"/></a></li>


                <li class="nav-item dropdown">
                    <a class="nav-link dropdown" href="#" id="navbarDarkDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <fmt:message key="page.header.products"/>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="navbarDarkDropdownMenuLink">
                        <li><a class="dropdown-item" href="${abs_path}/controller?command=show_instrument_by_type_command&instrument_type=GUITARS"><fmt:message key="page.header.guitars"/></a></li>
                        <li><a class="dropdown-item" href="${abs_path}/controller?command=show_instrument_by_type_command&instrument_type=DRUMS"><fmt:message key="page.header.drums"/></a></li>
                        <li><a class="dropdown-item" href="${abs_path}/controller?command=show_instrument_by_type_command&instrument_type=KEYBOARDS"><fmt:message key="page.header.keyboards"/></a></li>
                        <li><a class="dropdown-item" href="${abs_path}/controller?command=show_instrument_by_type_command&instrument_type=OTHER"><fmt:message key="page.header.other"/></a></li>
                    </ul>
                </li>

                <li><a href="https://www.youtube.com/watch?v=_E6mIYNO3So&ab_channel=DavidGilmour" class="nav-link px-2 link-dark"><fmt:message key="page.header.promo"/></a></li>
            </ul>

            <c:if test="${not empty sessionScope.user}">
                <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-dark text-decoration-none">
                    <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap">
                        <use xlink:href="#bootstrap"></use>
                    </svg>
                </a>
                <div class="dropdown text-end">
                    <a href="#"
                       class="d-block link-dark text-decoration-none dropdown-toggle"
                       id="dropdownUser1"
                       data-bs-toggle="dropdown"
                       aria-expanded="false">
                        <img src="https://www.pngitem.com/pimgs/m/22-220721_circled-user-male-type-user-colorful-icon-png.png"
                             alt="mdo"
                             width="52"
                             height="52"
                             class="rounded-circle">
                    </a>
                    <ul class="dropdown-menu text-small" aria-labelledby="dropdownUser1" style="">
                        <ctg:is-admin>
                            <li><a class="dropdown-item" href="${abs_path}/pages/admin/admin.jsp"><fmt:message key="page.header.admin_cabinet"/></a></li>
                        </ctg:is-admin>
                        <li><a class="dropdown-item" href="${abs_path}/controller?command=check_cart_command"><fmt:message key="page.header.cart"/></a></li>
                        <li><a class="dropdown-item" href="${abs_path}/controller?command=to_cabinet_page_command"><fmt:message key="page.header.cabinet"/></a></li>
                        <li>
                            <hr class="dropdown-divider">
                        </li>
                        <li>
                            <form action="${abs_path}/controller" method="post">
                                <input type="hidden" name="command" value="logout">
                                <input type="submit" class="dropdown-item" value=<fmt:message
                                        key="page.header.logout"/>>
                            </form>
                        </li>
                    </ul>
                </div>
            </c:if>
            <c:if test="${empty sessionScope.user}">
                <div class="text-end">
                    <form action="${abs_path}/controller?command=to_login_page_command" method="post">
                        <button type="submit" class="btn btn-outline-light me-2">
                            <fmt:message key="page.login.sign_in.button"/>
                        </button>
                    </form>
                </div>
                <div class="text-xxl-end">
                    <form action="${abs_path}/controller?command=to_registration_page_command" method="post">
                        <button type="submit" class="btn btn-outline-light me-2">
                            <fmt:message key="page.login.register"/>
                        </button>
                    </form>
                </div>
            </c:if>
        </div>
    </div>
</header>
</body>
</html>