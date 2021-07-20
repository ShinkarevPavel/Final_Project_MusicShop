<%--
  Created by IntelliJ IDEA.
  User: pavel
  Date: 30.06.2021
  Time: 23:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="abs_path">${pageContext.request.contextPath}</c:set>
<fmt:setLocale value="${curr_lang}" scope="request"/>
<fmt:setBundle basename="localization"/>
<html>
<head>
    <%@include file="/include/header.jsp" %>
</head>
<body>
<label><fmt:message key="page.registration.is_done"/></label>
<form method="post" action="${abs_path}/pages/login.jsp">
    <button type="submit"><fmt:message key="page.login.login"/></button>
</form>
</body>
</html>
