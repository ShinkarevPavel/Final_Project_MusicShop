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



<%@include file="/include/admin_header.jsp" %>
<form method="post" id="fuck" action="${abs_path}/controller?command=add_instrument_command">
<section class="vh-100" >
    <div class="row">
        <div class="col-md-3 offset-md-8">
            <div class="login-form bg-light mt-4 p-4">
                <!-- Name input -->
                <div class="form-outline mb-4">
                    <label class="form-label" for="form1Example1">Name</label>
                    <input type="text"
                           name="instrument_name"
                           pattern="^[\\w\\s@#$%^&+=]{2,45}$"
                           value="${registrationValues.instrument_name}"
                           id="form1Example1"
                           class="form-control"/>
                    <strong>
                        <p class="text-danger">${errors.instrumentNameError}</p>
                    </strong>
                </div>
                <!-- Brand input -->
                <div class="form-outline mb-4">
                    <label class="form-label" for="form1Example2">Brand</label>
                    <input type="text"
                           name="instrument_brand"
                           pattern="^[\\w\\s@#$%^&+=]{2,25}$"
                           value="${registrationValues.instrument_brand}"
                           id="form1Example2"
                           class="form-control"/>
                    <strong>
                        <p class="text-danger">${errors.instrumentBrandError}</p>
                    </strong>
                </div>

                <!-- Country input -->
                <div class="form-outline mb-4">
                    <label class="form-label" for="form1Example2">Country</label>
                    <input type="text"
                           name="instrument_country"
                           pattern="^[\\w\\s@#$%^&+=]{2,45}$"
                           value="${registrationValues.instrument_country}"
                           id="form1Example3"
                           class="form-control"/>
                    <strong>
                        <p class="text-danger">${errors.instrumentCountryError}</p>
                    </strong>
                </div>

                <!-- Price input -->
                <div class="form-outline mb-4">
                    <label class="form-label" for="form1Example2">Price</label>
                    <input type="text"
                           name="instrument_price"
                           pattern="^(\\d+\\.\\d+)$"
                           value="${registrationValues.instrument_price}"
                           id="form1Example4"
                           class="form-control"/>
                    <strong>
                        <p class="text-danger">${errors.instrumentPriceError}</p>
                    </strong>
                </div>

                <!-- Rating input -->
                <div class="form-outline mb-4">
                    <label class="form-label" for="form1Example2">Rating</label>
                    <input type="text"
                           name="instrument_rating"
                           pattern="[0-5]"
                           value="${registrationValues.instrument_rating}"
                           id="form1Example8"
                           class="form-control"/>
                    <strong>
                        <p class="text-danger">${errors.instrumentRatingError}</p>
                    </strong>
                </div>

                <!-- Description input -->
                <div class="form-outline mb-4">
                    <label class="form-label" for="form1Example2">Description</label>
                    <input type="text"
                           name="instrument_description"
                           pattern="^[\\w\\s@#$%^&+=]{2,800}$"
                           value="${registrationValues.instrument_description}"
                           id="form1Example5"
                           class="form-control"/>
                    <strong>
                        <p class="text-danger">${errors.instrumentDescriptionError}</p>
                    </strong>
                </div>
                <br>
                <!-- Status input -->
                <select class="form-select" name="instrumentStatus" id="fuck1" aria-label="Default select example">
                    <option selected>Chose Status</option>
                    <option value="AVAILABLE">AVAILABLE</option>
                    <option value="NOT_AVAILABLE">NOT_AVAILABLE</option>
                    <option value="ON_DEMAND">ON_DEMAND</option>
                </select>
                <strong>
                    <p class="text-danger">${errors.instrumentStatusError}</p>
                </strong>
                <br>
                <br>

                <!-- Type input -->
                <select class="form-select" name="instrumentType" id="fuck2"aria-label="Default select example">
                    <option selected>Chose type</option>
                    <option value="GUITARS">GUITARS</option>
                    <option value="DRUMS">DRUMS</option>
                    <option value="KEYBOARDS">KEYBOARDS</option>
                    <option value="OTHER">OTHER</option>
                </select>
                <strong>
                    <p class="text-danger">${errors.instrumentTypeError}</p>
                </strong>
                <!-- Submit button -->
                <button type="submit" class="btn btn-warning">Add</button>

            </div>
        </div>
    </div>
</section>
</form>
</body>
</html>
