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
<c:set var="abs_path">${pageContext.request.contextPath}</c:set>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization"/>
<fmt:message key="locale.lang" var="curr_lang"/>
<html>
<head>

</head>
<body>
<%@include file="../include/common_imports.jspf" %>
<div>
    <c:if test="${not empty sessionScope.user}">
        <form action="${abs_path}/controller" method="post">
            <input type="hidden" name="command" value="logout">
            <input type="submit" class="colorText" value=<fmt:message key="page.header.logout"/>>
        </form>
    </c:if>
</div>
<form action="${abs_path}/controller" method="post">
    <input type="hidden" name="command" value="change_locale">
    <input type="hidden" name="refererCommand" value="${refererCommand}">
    <input type="submit" class="btn btn-danger" value="${curr_lang}">
</form>
</body>
</html>