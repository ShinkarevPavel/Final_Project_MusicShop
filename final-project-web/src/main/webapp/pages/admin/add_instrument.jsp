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
        <form method="post" id="fuck" name="" action="${abs_path}/controller?command=add_instrument_command" enctype="multipart/form-data">
        <div class="row">
            <div class="col-md-3 offset-md-8">
                <div class="login-form bg-light mt-4 p-4">
                    <!-- Name input -->
                    <div class="form-outline mb-4">
                        <label class="form-label" for="form1Example1">Name</label>
                        <input type="text"
                               name="instrument_name"
                               value="${registrationValues.instrument_name}"
                               pattern="^[\w\s,.А-Яа-я():;!?@#$%^&\-+=]{2,45}$"
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
                               value="${registrationValues.instrument_brand}"
                               id="form1Example2"
                               pattern="^[\w\s,.А-Яа-я():;!?@#$%^&\-+=]{2,45}$"
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
                               value="${registrationValues.instrument_country}"
                               id="form1Example3"
                               pattern="^[\w\s,.А-Яа-я():;!?@#$%^&\-+=]{2,45}$"
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
                               value="${registrationValues.instrument_price}"
                               id="form1Example4"
                               pattern="^(\d+\.\d+)$"
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
                               value="${registrationValues.instrument_rating}"
                               id="form1Example8"
                               pattern="^(\d+\.\d+)$"
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
                               value="${registrationValues.instrument_description}"
                               id="form1Example5"
                               pattern="^[\w\s,\\.А-Яа-я():;'"*!?@#$%^&\-+=]{2,800}$"
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

                    <div class="row">
                        <div class="col-md-9 pe-5">
                            <input class="form-control form-control-sm" id="imageId" name="image" type="file" multiple/>
                            <div class="small text-muted mt-2">Upload your photo. Max file size 1 MB</div>
                        </div>
                    </div>
                    <!-- Submit button -->
                    <button type="submit" class="btn btn-warning">Add</button>
                </div>
            </div>
        </div>
        </form>
    </section>
</body>
</html>