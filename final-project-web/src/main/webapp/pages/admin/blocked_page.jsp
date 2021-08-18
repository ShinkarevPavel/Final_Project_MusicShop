<%--
  Created by IntelliJ IDEA.
  User: Pavel_Shinkarev
  Date: 16.08.2021
  Time: 18:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<link rel="stylesheet" href="https://www.markuptag.com/bootstrap/5/css/bootstrap.min.css">
<body style="background-image: url(https://cdn.shopify.com/s/files/1/0975/2962/products/image_67736ab5-4f2a-4ba7-b912-b0b3ae367fb9_2048x.jpg?v=1541103676);
background-repeat: no-repeat;
background-position: center center;
background-size: cover">
<%@include file="../../include/common_imports.jspf" %>


<header class="p-3 mb-3 border-bottom" style="background-color: #bcc3c9">

    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li>
                    <form action="${abs_path}/controller?command=change_locale" method="post" id="locale">
                        <input type="hidden" form="locale" name="command" value="change_locale">
                        <input type="hidden" form="locale" name="refererCommand" value="${refererCommand}">
                        <input type="submit" form="locale" class="btn btn-outline-light me-2" value="${curr_lang}">
                    </form>
                </li>
                <li>
                    <form action="${abs_path}/controller" method="post">
                        <input type="hidden" name="command" value="logout">
                        <input type="submit" class="btn btn-outline-light me-2" value=<fmt:message
                                key="page.header.logout"/>>
                    </form>
                </li>
            </ul>
        </div>
    </div>
</header>
</body>
</html>

<section class="vh-100">
    <div class="row">
        <div class="col-md-3 offset-md-4">
            <div class="login-form bg-light mt-4 p-4">
                <h4 class="b-3 center-block">${sessionScope.user.nickname} <fmt:message key="page.blocked_user"/></h4>
                <p class="mb-3 center-block"><fmt:message key="page.contact_us"/> great.guitars@gmail.com</p>

            </div>
        </div>
    </div>
</section>
</body>
</html>
