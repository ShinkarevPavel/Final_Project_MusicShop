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
        <span>
            <fmt:message key="page.registration.login"/>
            <input type="text" name="login" pattern="^[\w@#$%^&+=]{7,25}$" value="${registrationValues.login}" required>
        </span>
            <strong>
            <p class="text-danger">${errors.loginError}</p>
            </strong>
        <h6 style="color: forestgreen"><fmt:message key="page.registration.login.requirements"/>
    </div>

    <div>
        <span>
            <fmt:message key="page.registration.password"/>
            <input type="password" name="password" pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{12,45}$" required>
        </span>
        <span>
            <strong>
            <p class="text-danger">${errors.passwordError}</p>
        </strong>
        </span>
        <h6 style="color: forestgreen"><fmt:message key="page.registration.password.requirements"/>
    </div>


    <div>
        <span>
            <fmt:message key="page.registration.confirm_password"/>
            <input type="password" name="checkPassword"  required>
        </span>
    </div>


    <div>
        <span>
            <fmt:message key="page.registration.email"/>
            <input type="email" name="email" pattern="^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$"
                   value="${registrationValues.email}" required>
        </span>
        <span>
            <strong>
            <p class="text-danger">${errors.emailError}</p>
        </strong>
        </span>
    </div>
    <div>

        <span>
            <fmt:message key="page.registration.nickname"/>
            <input type="text" name="nickname" pattern="^[\w@#$%^&+=]{2,30}$" value="${registrationValues.nickname}" required>
        </span>
        <span>
            <strong>
            <p class="text-danger">${errors.nicknameError}</p>
        </strong>
        </span>
        <h6 style="color: forestgreen"><fmt:message key="page.registration.nickname.requirements"/>
    </div>


    <div>
        <span>
            <fmt:message key="page.registration.name"/>
            <input type="text" name="name" value="${registrationValues.name}">
        </span>
        <h6 style="color: forestgreen"><fmt:message key="page.registration.name.requirements"/>
    </div>


    <div>
        <span>
            <fmt:message key="page.registration.surename"/>
            <input type="text" name="surename" value="${registrationValues.surename}">
        </span>
        <h6 style="color: forestgreen"><fmt:message key="page.registration.surename.requirements"/>
    </div>
        <button type="submit"><fmt:message key="page.registration.sign_up"/></button>
</form>
</body>
</html>
