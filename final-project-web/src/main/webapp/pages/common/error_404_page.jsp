<%--
  Created by IntelliJ IDEA.
  User: Pavel_Shinkarev
  Date: 15.07.2021
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="abs_path">${pageContext.request.contextPath}</c:set>
<fmt:setLocale value="${curr_lang}" scope="request"/>
<fmt:setBundle basename="localization"/>
<html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://fonts.googleapis.com/css?family=Raleway:400,400i,500,600,700,800&display=swap" rel="stylesheet">
    <link href=" ${pageContext.request.contextPath}/pages/styles.css" rel="stylesheet"/>
</head>
<body>

<div class="not-found">
    <h2 ><span>404</span><br>Page not found</h2>
    <h3>${error}</h3>
    <p>Unfortunately the action you performed led to getting to this page<br>Please correct your request or ...</p>
    <div class="go-on">
        <a href="#">Visit Homepage</a>
        <a href="#">Contact Us</a>
    </div>
</div>
</body>
</html>
</html>
