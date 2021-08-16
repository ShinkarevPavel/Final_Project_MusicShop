<%--
  Created by IntelliJ IDEA.
  User: Pavel_Shinkarev
  Date: 25.07.2021
  Time: 22:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>


<body style="background-image: url(https://images.pexels.com/photos/1082615/pexels-photo-1082615.jpeg?cs=srgb&dl=pexels-alaa-elawaad-1082615.jpg&fm=jpg);
background-repeat: no-repeat;
background-position: center center;
background-size: cover">
<%@include file="/include/admin_header.jsp" %>
<section class="vh-100" >
  <form method="post" id="fuck" name="" action="${abs_path}/controller?command=save_updated_instrument_command" enctype="multipart/form-data">
    <div class="row">
      <div class="col-md-3 offset-md-8">
        <div class="login-form bg-light mt-4 p-4">
          <!-- Name input -->
          <div class="form-outline mb-4">
            <label class="form-label" for="form1Example1"><fmt:message key="page.admin_page.add_instrument.name"/></label>
            <input type="text"
                   name="instrument_name"
                   value="${updateValues.instrument_name}"
                   pattern="^[\w\s,.А-Яа-я():;!?@#$%^&\-+=]{2,45}$"
                   id="form1Example1"
                   class="form-control"/>
            <strong>
              <p class="text-danger">${errors.instrumentNameError}</p>
            </strong>
          </div>
          <!-- Brand input -->
          <div class="form-outline mb-4">
            <label class="form-label" for="form1Example2"><fmt:message key="page.admin_page.add_instrument.brand"/></label>
            <input type="text"
                   name="instrument_brand"
                   value="${updateValues.instrument_brand}"
                   id="form1Example2"
                   pattern="^[\w\s,.А-Яа-я():;!?@#$%^&\-+=]{2,45}$"
                   class="form-control"/>
            <strong>
              <p class="text-danger">${errors.instrumentBrandError}</p>
            </strong>
          </div>

          <!-- Country input -->
          <div class="form-outline mb-4">
            <label class="form-label" for="form1Example2"><fmt:message key="page.admin_page.add_instrument.country"/></label>
            <input type="text"
                   name="instrument_country"
                   value="${updateValues.instrument_country}"
                   id="form1Example3"
                   pattern="^[\w\s,.А-Яа-я():;!?@#$%^&\-+=]{2,45}$"
                   class="form-control"/>
            <strong>
              <p class="text-danger">${errors.instrumentCountryError}</p>
            </strong>
          </div>

          <!-- Price input -->
          <div class="form-outline mb-4">
            <label class="form-label" for="form1Example2"><fmt:message key="page.admin_page.add_instrument.price"/></label>
            <input type="text"
                   name="instrument_price"
                   value="${updateValues.instrument_price}"
                   id="form1Example4"
                   pattern="^(\d+\.\d+)$"
                   class="form-control"/>
            <strong>
              <p class="text-danger">${errors.instrumentPriceError}</p>
            </strong>
          </div>

          <!-- Description input -->
          <div class="form-outline mb-4">
            <label class="form-label" for="form1Example2"><fmt:message key="page.admin_page.add_instrument.description"/></label>
            <input type="text"
                   name="instrument_description"
                   value="${updateValues.instrument_description}"
                   id="form1Example5"
                   pattern="^[\w\s,\\.А-Яа-я():;'"*!?@#$%^&\-+=]{2,800}$"
                   class="form-control"/>
            <strong>
              <p class="text-danger">${errors.instrumentDescriptionError}</p>
            </strong>
          </div>
          <br>
          <br>
          <input type="hidden" name="instrumentId" value="${updateValues.instrumentId}">
          <!-- Submit button -->
          <button type="submit" class="btn btn-warning"><fmt:message key="page.admin_page.add_instrument.update"/></button>
        </div>
      </div>
    </div>
  </form>
</section>
</body>
</html>