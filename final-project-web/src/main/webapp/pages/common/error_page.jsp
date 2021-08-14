
<%--
  Created by IntelliJ IDEA.
  User: Pavel_Shinkarev
  Date: 15.07.2021
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://fonts.googleapis.com/css?family=Raleway:400,400i,500,600,700,800&display=swap" rel="stylesheet">
    <link href=" ${pageContext.request.contextPath}/pages/style.css" rel="stylesheet"/>
</head>
<body>

<div class="not-found">
    <h2 ><span>${pageContext.errorData.statusCode}</span><br>${pageContext.exception.message}<br>${error}</h2>
    <p>Unfortunately the action you performed led to getting to this page<br>Please correct your request or ...</p>
    <div class="go-on">
        <a href="${pageContext.request.contextPath}/pages/common/main_page.jsp">Visit Homepage</a>
    </div>
</div>
</body>
</html>
</html>
