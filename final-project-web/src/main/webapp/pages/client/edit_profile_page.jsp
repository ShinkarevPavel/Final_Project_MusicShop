<%--
  Created by IntelliJ IDEA.
  User: Pavel_Shinkarev
  Date: 10.08.2021
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

</head>
<%@include file="/include/header.jsp" %>
<body>

<div class="container rounded bg-white mt-5 mb-5">
    <div class="row">
        <div class="col-md-3 border-right">
            <div class="d-flex flex-column align-items-center text-center p-3 py-5">
                <img class="rounded-circle mt-5" width="150px"
                     src="https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg">
                <span class="font-weight-bold">
                    ${sessionScope.user.nickname}
                </span>
                <span class="text-black-50">${sessionScope.user.role}</span>
                <span>
                </span>
            </div>
        </div>
        <div class="col-md-5 border-right">
            <div class="p-3 py-5">
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h4 class="text-right"><fmt:message key="page.user_info.profile_settings"/></h4>
                </div>
                <form action="${abs_path}/controller?command=edit_profile_command" method="post">
                <div class="row mt-2">
                    <div class="col-md-6">
                        <label class="labels"><fmt:message key="page.registration.nickname"/></label>
                        <label class="form-control">
                        <input type="text" pattern="^[\w@#$%^&+=]{2,30}$" name="nickname" value="${sessionScope.user.nickname}" required></label>
                        <strong>
                            <p class="text-danger">${errors.nicknameError}</p>
                        </strong>
                    </div>
                    <div class="col-md-6">
                        <label class="labels"><fmt:message key="page.registration.email"/></label>
                        <label class="form-control">${sessionScope.user.email}</label>
                    </div>
                </div>
                <div class="row mt-3">
                    <div class="col-md-6">
                        <label class="labels"><fmt:message key="page.registration.user_id"/></label>
                        <label class="form-control">${sessionScope.user.id}</label>
                    </div>
                    <div class="col-md-6">
                        <label class="labels"><fmt:message key="page.registration.status"/></label>
                        <label class="form-control">${sessionScope.user.status}</label>
                    </div>
                </div>
                <div class="row mt-3">
                    <div class="col-md-6">
                        <label class="labels"><fmt:message key="page.registration.name"/></label>
                        <label class="form-control">
                            <input type="text" pattern="[a-zA-Zа-яА-ЯёЁ]{3,35}" name="name" value="${sessionScope.user.name}"></label>
                        <strong>
                            <p class="text-danger">${errors.nameError}</p>
                        </strong>
                    </div>
                    <div class="col-md-6">
                        <label class="labels"><fmt:message key="page.registration.surename"/></label>
                        <label class="form-control">
                            <input type="text" pattern="[a-zA-Zа-яА-ЯЁё]{3,35}" name="surename" value="${sessionScope.user.surename}"></label>
                        <strong>
                            <p class="text-danger">${errors.surenameError}</p>
                        </strong>
                    </div>
                </div>

                <div class="mt-5 text-center">
                        <button class="btn btn-success profile-button" type="submit"><fmt:message key="page.admin.update"/></button>
                </div>
                </form>
            </div>
        </div>

        <%--Buttons--%>
        <div class="col-md-4">


            </div>
        </div>
    </div>
</body>
</html>
