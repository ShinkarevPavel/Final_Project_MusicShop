<%--
  Created by IntelliJ IDEA.
  User: Pavel_Shinkarev
  Date: 01.08.2021
  Time: 21:02
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
    <link rel="stylesheet" href="https://www.markuptag.com/bootstrap/5/css/bootstrap.min.css">
</head>
<body style="background-image: url(https://cdn.hipwallpaper.com/i/95/86/BSezOZ.jpg);
background-repeat: no-repeat;
background-position: center center;
background-size: cover">

<%@include file="/include/header.jsp" %>


<div class="container">
    <div class="row">

        <div class="col">
            <div class="bg-image hover-overlay ripple" data-mdb-ripple-color="light">
                <img
                        src="https://www.rollingstone.com/wp-content/uploads/2021/01/alexi-laiho-obit.jpg?resize=1800,1200&w=1200"
                        class="img-fluid"
                />
                <a href="#!">
                    <div class="mask" style="background-color: rgba(243,237,237,0.15);"></div>
                </a>
            </div>
            <div class="card-body">
                <h5 class="card-title" style="color: white">RollingStone News</h5>

                <a href="https://www.rollingstone.com/music/music-news/alexi-laiho-children-of-bodom-dead-obituary-1109338/"
                   class="link-secondary">
                    <p class="card-text" style="color: white">
                        Alexi Laiho, Frontman for Finnish Metal Giants Children of Bodom, Dead at 41
                    </p>
                </a>
            </div>
        </div>


        <div class="col">
            <div class="bg-image hover-overlay ripple" data-mdb-ripple-color="light">
                <img
                        src="https://www.rollingstone.com/wp-content/uploads/2021/07/GettyImages-805158886c.jpg?resize=1800,1200&w=1200"
                        class="img-fluid"
                />
                <a href="#!">
                    <div class="mask" style="background-color: rgba(243,237,237,0.15);"></div>
                </a>
            </div>
            <div class="card-body">
                <h5 class="card-title" style="color: white">RollingStone News</h5>

                <a href="https://www.rollingstone.com/music/music-news/joey-jordison-slipknot-dead-1203167/"
                   class="link-secondary">
                    <p class="card-text" style="color: white">
                        Joey Jordison, Founding Slipknot Drummer, Dead at 46
                    </p>
                </a>
            </div>
        </div>
        <div class="col">
            <div class="bg-image hover-overlay ripple" data-mdb-ripple-color="light">
                <img
                        src="https://www.rollingstone.com/wp-content/uploads/2019/05/Rammstein_JensKoch_3105_hires.jpg?resize=1800,1200&w=1200"
                        class="img-fluid"
                />
                <a href="#!">
                    <div class="mask" style="background-color: rgba(243,237,237,0.15);"></div>
                </a>
            </div>
            <div class="card-body">
                <h5 class="card-title" style="color: white">RollingStone News</h5>

                <a href="https://www.rollingstone.com/music/music-album-reviews/rammstein-tussle-with-germanys-past-embrace-their-own-darkness-on-self-titled-album-835717/"
                   class="link-secondary">
                    <p class="card-text" style="color: white">
                        Rammstein Tussle With Germany’s Past, Embrace Their Own Darkness on Self-Titled Album
                    </p>
                </a>
            </div>
        </div>

        <div class="row">
            <div class="col-4">
                <div class="bg-image hover-overlay ripple" data-mdb-ripple-color="light">
                    <img
                            src="https://www.rollingstone.com/wp-content/uploads/2021/08/Limp-Bizkit_-SL_-DSC_5146.jpg?resize=1800,1200&w=1200"
                            class="img-fluid"
                    />
                    <a href="#!">
                        <div class="mask" style="background-color: rgba(243,237,237,0.15);"></div>
                    </a>
                </div>
                <div class="card-body">
                    <h5 class="card-title" style="color: white">RollingStone News</h5>

                    <a href="https://www.rollingstone.com/music/music-news/fred-durst-new-look-limp-bizkit-lollapalooza-2021-set-1205652/"
                       class="link-secondary">
                        <p class="card-text" style="color: white">
                            Fred Durst Shows Off Surprising New Look at Limp Bizkit’s Lollapalooza 2021 Set
                        </p>
                    </a>
                </div>
            </div>


        </div>


    </div>
</div>
</body>
</html>
