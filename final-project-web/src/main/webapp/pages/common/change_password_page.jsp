<%--
  Created by IntelliJ IDEA.
  User: Pavel_Shinkarev
  Date: 11.08.2021
  Time: 00:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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
                    <h4 class="text-right">Profile Settings</h4>
                </div>

                <div class="col-md-12"><label class="labels">Enter password:</label>
                    <input type="password"
                           form="change"
                           class="form-control"
                           name="password"
                           pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{12,45}$"
                           required>
                </div>
                <br>
                <div class="col-md-12"><label class="labels">Confirm password:</label>
                    <input type="password"
                           form="change"
                           name="checkPassword"
                           class="form-control"
                           required>
                </div>
                <strong>
                    <p class="text-danger">${errors.passwordError}</p>
                </strong>
                <form method="post" id="change" action="${abs_path}/controller?command=change_password_command">
                    <div class="mt-5 text-center">
                        <button class="btn btn-success profile-button" type="submit">Save</button>
                    </div>
                </form>
            </div>
        </div>


        <%--Buttons--%>
        <div class="col-md-4">
            <div class="p-3 py-5">
                <div class="d-flex justify-content-between align-items-center experience">
                    <img class="rounded-circle mt-5" width="150px"
                         src="https://cdn4.iconfinder.com/data/icons/essential-6/512/change_password-512.png">
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
