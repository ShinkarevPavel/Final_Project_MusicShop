
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
<fmt:setLocale value="${curr_lang}" scope="request"/>
<fmt:setBundle basename="localization"/>

<html>
<html lang="en">
<head>
    <style>
        .not-found {
            text-align: center;
            padding: 100px 0px 50px;
            font-family: 'Raleway', sans-serif;
            color: #333;
        }
        .not-found h2 {
            font-size: 36px;
        }
        .not-found h3 {
            font-size: 22px;
            font-weight: normal;
        }
        .not-found h2 span {
            background-color: #eee;
            color: #03A9F4;
            width: 200px;
            height: 170px;
            display: inline-grid;
            align-items: center;
            font-size: 100px;
            border-radius: 20px;
            font-style: italic;
            margin-bottom: 15px;
        }
        .not-found p {
            font-size: 17px;
            line-height: 32px;
            color: #9E9E9E;
        }
        .not-found .go-on {
            padding: 20px 0px 20px;
        }
        .not-found .go-on a {
            padding: 7px 15px;
            background-color: #2196F3;
            border-radius: 4px;
            font-size: 20px;
            margin: 10px 10px;
            display: inline-block;
            font-weight: 500;
            color: #fff;
            text-decoration: none;
        }
        .not-found .go-on a:hover{
            opacity: 0.8;
        }
        .not-found form input {
            height: 40px;
            border: 1px solid #ddd;
            border-radius: 4px;
            padding: 4px 10px;
            box-sizing: border-box;
        }
        .not-found form .submit-btn {
            height: 40px;
            border: none;
            margin-left: 10px;
            color: #fff;
            background-color: #03a9f4;
            border-radius: 4px;
            cursor: pointer;
            padding: 0px 15px;
        }
        .not-found form .submit-btn:hover{
            opacity: 0.8;
        }
        .not-found hr {
            border: 1px solid #eeeeee;
            margin: 30px 0px;
        }
    </style>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous">
    </script>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://fonts.googleapis.com/css?family=Raleway:400,400i,500,600,700,800&display=swap" rel="stylesheet">
<%--    <link href=" ${pageContext.request.contextPath}/pages/style.css" rel="stylesheet"/>--%>
</head>
<body>

<div class="not-found">
    <h2 ><span>${pageContext.errorData.statusCode}</span><br>${pageContext.exception.message}<br>${error}</h2>
    <p><fmt:message key="page.error_page.message"/></p>
    <div class="go-on">
        <a href="${pageContext.request.contextPath}/controller?command=to_main_page"><fmt:message key="page.error_page.visit_homepage"/></a>
    </div>
</div>
</body>
</html>
</html>
