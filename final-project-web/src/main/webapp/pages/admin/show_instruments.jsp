<%--
  Created by IntelliJ IDEA.
  User: Pavel_Shinkarev
  Date: 26.07.2021
  Time: 22:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body style="background-image: url(https://wallpaperaccess.com/full/2086727.jpg);
background-repeat: no-repeat;
background-position: center center;
background-size: cover">
<%@include file="/include/admin_header.jsp" %>
<table border="4" class="table table-striped" style="background-color: #efeeee">
    <tr>
        <th><fmt:message key="page.instrument_id"/></th>
        <th><fmt:message key="page.admin_page.add_instrument.name"/></th>
        <th><fmt:message key="page.admin_page.add_instrument.brand"/></th>
        <th><fmt:message key="page.registration.status"/></th>
        <th><fmt:message key="page.registration.type"/></th>
        <th><fmt:message key="page.show_all_users.control"/></th>
    </tr>
    <strong>
        <p class="text-danger">${instrument_message}</p>
    </strong>
    <c:forEach items="${instruments}" var="instrument">
    <tr>
        <td>${instrument.instrument_id}</td>
        <td>${instrument.name}</td>
        <td>${instrument.brand}</td>
        <td>
            <div class="dropdown">
                <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenuButton"
                        data-bs-toggle="dropdown" aria-expanded="false">
                        ${instrument.instrumentStatus}
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                    <li><a class="dropdown-item"
                           href="${abs_path}/controller?command=instrument_status_control_command&instrumentId=${instrument.instrument_id}&new_status=AVAILABLE"><fmt:message key="page.admin_page.add_instrument.available"/></a>
                    </li>
                    <li><a class="dropdown-item"
                           href="${abs_path}/controller?command=instrument_status_control_command&instrumentId=${instrument.instrument_id}&new_status=NOT_AVAILABLE"><fmt:message key="page.admin_page.add_instrument.not_available"/></a></li>
                    <li><a class="dropdown-item"
                           href="${abs_path}/controller?command=instrument_status_control_command&instrumentId=${instrument.instrument_id}&new_status=ON_DEMAND"><fmt:message key="page.admin_page.add_instrument.on_demand"/></a></li>
                </ul>
            </div>
        </td>
        <td>
            <div class="dropdown">
                <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1"
                        data-bs-toggle="dropdown" aria-expanded="false">
                        ${instrument.type}
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                    <li><a class="dropdown-item"
                           href="${abs_path}/controller?command=instrument_type_control_command&instrumentId=${instrument.instrument_id}&new_type=GUITARS"><fmt:message key="page.header.guitars"/></a>
                    </li>
                    <li><a class="dropdown-item"
                           href="${abs_path}/controller?command=instrument_type_control_command&instrumentId=${instrument.instrument_id}&new_type=DRUMS"><fmt:message key="page.header.drums"/></a>
                    </li>
                    <li><a class="dropdown-item"
                           href="${abs_path}/controller?command=instrument_type_control_command&instrumentId=${instrument.instrument_id}&new_type=KEYBOARDS"><fmt:message key="page.header.keyboards"/></a>
                    </li>
                    <li><a class="dropdown-item"
                           href="${abs_path}/controller?command=instrument_type_control_command&instrumentId=${instrument.instrument_id}&new_type=OTHER"><fmt:message key="page.header.other"/></a>
                    </li>
                </ul>
            </div>
        </td>
        <td>
            <a href="${abs_path}/controller?command=update_instrument_command&instrumentId=${instrument.instrument_id}"><fmt:message key="page.admin_page.add_instrument.update"/></a>
        </td>
        <td>
            <a href="${abs_path}/controller?command=show_instrument_details_command&instrumentId=${instrument.instrument_id}"><fmt:message key="page.registration.details"/></a>
        </td>
        </c:forEach>
</table>
<c:if test="${not empty instruments}">

    <c:if test="${empty mark}">
        <nav aria-label="Page navigation area">
            <ul class="pagination justify-content-center">
                <li class="page-item ${pageable.isFirstPage() ? 'disabled': ''}">
                    <a class="page-link"
                       href="${abs_path}/controller?command=show_all_instruments_command&page=${pageable.currentPage - 1}"
                       tabindex="-1"><fmt:message key="page.previous"/></a>
                </li>
                <c:forEach var="i" begin="1" end="${pageable.pageCount()}">
                    <li class="page-item ${pageable.currentPage eq i ? 'active': ''}">
                        <a class="page-link"
                           href="${abs_path}/controller?command=show_all_instruments_command&page=${i}">${i}</a></li>
                </c:forEach>
                <li class="page-item ${pageable.isLastPage() ? 'disabled': ''}">
                    <a class="page-link"
                       href="${abs_path}/controller?command=show_all_instruments_command&page=${pageable.currentPage + 1}"><fmt:message key="page.next"/></a>
                </li>
            </ul>
        </nav>
    </c:if>
    <c:if test="${not empty mark}">
        <nav aria-label="Page navigation area">
            <ul class="pagination justify-content-center">
                <li class="page-item ${pageable.isFirstPage() ? 'disabled': ''}">
                    <a class="page-link"
                       href="${abs_path}/controller?command=show_type_instrument&instrumentType=${instruments[0].type}&page=${pageable.currentPage - 1}"
                       tabindex="-1"><fmt:message key="page.previous"/></a>
                </li>
                <c:forEach var="i" begin="1" end="${pageable.pageCount()}">
                    <li class="page-item ${pageable.currentPage eq i ? 'active': ''}">
                        <a class="page-link"
                           href="${abs_path}/controller?command=show_type_instrument&instrumentType=${instruments[0].type}&page=${i}">${i}</a>
                    </li>
                </c:forEach>
                <li class="page-item ${pageable.isLastPage() ? 'disabled': ''}">
                    <a class="page-link"
                       href="${abs_path}/controller?command=show_type_instrument&instrumentType=${instruments[0].type}&page=${pageable.currentPage + 1}"><fmt:message key="page.next"/></a>
                </li>
            </ul>
        </nav>
    </c:if>

</c:if>
</body>
</html>
