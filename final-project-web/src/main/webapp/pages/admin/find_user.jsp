<%--
  Created by IntelliJ IDEA.
  User: Pavel_Shinkarev
  Date: 23.07.2021
  Time: 16:35
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
            <div class="login-form bg-light mt-4 p-4">
                <form method="post" action="${abs_path}/controller?command=find_user_command">
                    <div class="input-group">
                        <input type="number" min="1" class="form-control rounded"
                               placeholder="<fmt:message key="page.admin_page.search_by_id"/>" aria-label="Search"
                               aria-describedby="search-addon" name="admin_search_by_id" required/>
                        <button type="submit" class="btn btn-outline-secondary"><fmt:message
                                key="page.admin_page.search"/></button>
                    </div>
                    <h6 class="offset-xxl-3"><span style="color: red">${message}</span></h6>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
