<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Music Shop</title>
</head>
<body>
<c:if test="${not empty sessionScope.user}">
    <c:redirect url="/controller?command=login"/>
</c:if>

<jsp:forward page="/pages/newlogin.jsp"/>
    </body>
    </html>

