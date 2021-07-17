<%--
  Created by IntelliJ IDEA.
  User: pavel
  Date: 30.06.2021
  Time: 00:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="abs_path">${pageContext.request.contextPath}</c:set>
<html>
<head>
    <%@include file="/include/header.jsp" %>

<body>
<h3 style="color: #04414d"><fmt:message key="page.registration.head"/>
</h3>
<br/>
<br/>
<form method="post" action="${abs_path}/controller?command=registration">
    <div>
        <span><fmt:message key="page.registration.login"/><input type="text" name="login" pattern="^[\w@#$%^&+=]{7,25}$"
                                                                 value="${login}"></span>
        <span style="color: red">${loginError}</span>
        <h6 style="color: forestgreen"><fmt:message key="page.registration.login.requirements"/>
    </div>
    <div>
        <span><fmt:message key="page.registration.password"/><input type="password" name="password"
                                                                    pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{12,45}$"
                                                                    value="${password}"></span>
        <span style="color: red">${passwordError}</span>
        <h6 style="color: forestgreen"><fmt:message key="page.registration.password.requirements"/>
    </div>
    <div>
        <span><fmt:message key="page.registration.confirm_password"/>
            <input type="password" name="checkPassword" value="${secondPassword}"></span>
    </div>
    <div>
        <span><fmt:message key="page.registration.email"/>
            <input type="email" name="email" pattern="^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$"
                   value="${email}"></span>
        <span style="color: red">${emailError}</span>
    </div>
    <div>
        <span><fmt:message key="page.registration.nickname"/>
            <input type="text" name="nickname" pattern="^[\w@#$%^&+=]{2,30}$" value="${nickname}"></span>
        <span style="color: red">${nicknameError}</span>
        <h6 style="color: forestgreen"><fmt:message key="page.registration.nickname.requirements"/>
    </div>
    <div>
        <span><fmt:message key="page.registration.name"/><input type="text" name="name" value="${name}"></span>
        <h6 style="color: forestgreen"><fmt:message key="page.registration.name.requirements"/>
    </div>
    <div>
        <span><fmt:message key="page.registration.surename"/><input type="text" name="surename"
                                                                    value="${surename}"></span>
        <h6 style="color: forestgreen"><fmt:message key="page.registration.surename.requirements"/>
    </div>
    <a href="${abs_path}/pages/registration.jsp">
        <button type="submit"><fmt:message key="page.registration.sign_up"/></button>
    </a>

</form>
</body>
</html>
