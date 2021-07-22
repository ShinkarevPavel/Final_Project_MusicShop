<%--
  Created by IntelliJ IDEA.
  User: Pavel_Shinkarev
  Date: 15.07.2021
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="abs_path">${pageContext.request.contextPath}</c:set>
<html>
<head>


</head>
<body> <%@include file="/include/header.jsp"%>
${user.nickname}

<a href="${abs_path}/controller?command=show_all_users">
    <button type="button"><fmt:message key="page.admin.show_all_users"/></button>
</a>
</body>
</html>
