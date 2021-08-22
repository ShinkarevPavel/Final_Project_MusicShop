<%--
  Created by IntelliJ IDEA.
  User: Pavel_Shinkarev
  Date: 15.07.2021
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
</head>
<body style="background-image: url(https://cdn.mos.cms.futurecdn.net/XHkXFNDFEruxCuAX2ZGRcT.jpg);
background-repeat: no-repeat;
background-position: center center;
background-size: cover">
<%@include file="/include/admin_header.jsp" %>



    <div class="container">
        <div class="row">
            <div class="col-md-4 offset-md-5">
                <c:choose>
                    <c:when test="${param.data=='update'}">
                        <h5><span style="color: white"><fmt:message key="page.errors.on_admin_update"/></span></h5>
                    </c:when>
                    <c:when test="${param.data=='add'}">
                        <h5><span style="color: white"><fmt:message key="page.errors.on_admin_add"/></span></h5>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </div>

</body>
</html>
