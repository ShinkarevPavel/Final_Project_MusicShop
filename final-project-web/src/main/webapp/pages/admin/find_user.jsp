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
<body>
<%@include file="/include/admin_header.jsp"%>


<section class="vh-100" style="background-color: #e4e7e8">
    <div class="row">
        <div class="col-md-4 offset-md-1">
            <div class="login-form bg-light mt-4 p-4">
                <form method="post" action="${abs_path}/controller?command=find_user_command" >
                    <div class="input-group">
                        <input type="search" class="form-control rounded" placeholder="Search" aria-label="Search"
                               aria-describedby="search-addon" name="admin_search_by_id" required/>
                        <button type="submit" class="btn btn-outline-warning">search</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>
</body>
</html>
