<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Paperback Paradise : Register</title>
    <%@ include file="all_component/allCss.jsp"%>
</head>
<body>
    <%@ include file="all_component/navbar.jsp"%>

    <div class="container pd-4">
        <div class="row">
            <div class="col-md-4 offset-md-4">
                <div class="card">
                    <div class="card-body">
                        <h4 class="text-center">Registration Page</h4>
                        <c:if test="${not empty successMsg }">
                            <p class="text-center text-success">${successMsg}</p>
                            <c:remove var="successMsg" scope="session" />
                        </c:if>
                        <c:if test="${not empty failMsg }">
                            <p class="text-center text-danger">${failMsg}</p>
                            <c:remove var="failMsg" scope="session" />
                        </c:if>
                        <form method="post" action="register">
                            <div class="form-group">
                                <label for="inputName">Name</label>
                                <input type="text" class="form-control" id="inputName"
                                       placeholder="Name" required="required" name="fname" minlength="2" maxlength="20">
                            </div>
                            <div class="form-group">
                                <label for="inputEmail">Email address</label>
                                <input type="email" class="form-control" id="inputEmail"
                                       placeholder="Enter email" required="required" name="email" maxlength="50">
                            </div>
                            <div class="form-group">
                                <label for="inputMobile">Mobile No.</label>
                                <input type="tel" class="form-control" id="inputMobile"
                                       placeholder="Enter mobile no" required="required" name="phno" minlength="10" maxlength="15">
                            </div>
                            <div class="form-group">
                                <label for="inputPassword">Password</label>
                                <input type="password" class="form-control" id="inputPassword"
                                       placeholder="Password" required="required" name="password" minlength="6" maxlength="20">
                            </div>
                            <div class="form-check">
                                <input type="checkbox" class="form-check-input" id="checkTerms" name="check" required="required">
                                <label class="form-check-label" for="checkTerms">I agree to the terms and conditions</label>
                            </div>
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%@ include file="all_component/footer.jsp"%>
</body>
</html>


 
 


<%-- <!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Paperback Paradise : Register</title>
    <%@ include file="all_component/allCss.jsp" %>
    <!-- Include jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <style>
        .hidden {
            display: none;
        }
    </style>
</head>
<body>
    <%@ include file="all_component/navbar.jsp" %>

    <div class="container pd-4">
        <div class="row">
            <div class="col-md-6 offset-md-3">
                <div class="card">
                    <div class="card-body">
                        <h4 class="text-center">Registration Page</h4>
                        <div id="errorMsg" class="text-center text-danger"></div>
                        <div id="successMsg" class="text-center text-success"></div>
                        <form id="registrationForm" method="post">
                            <div class="form-group">
                                <label for="inputName">Name</label>
                                <input type="text" class="form-control" id="inputName"
                                       placeholder="Name" required="required" name="fname" minlength="2" maxlength="20">
                            </div>
                            <div class="form-group">
                                <label for="inputEmail">Email address</label>
                                <input type="email" class="form-control" id="inputEmail"
                                       placeholder="Enter email" required="required" name="verifyEmail" maxlength="50">
                            </div>
                            <div class="form-group">
                                <label for="inputMobile">Mobile No.</label>
                                <input type="tel" class="form-control" id="inputMobile"
                                       placeholder="Enter mobile no" required="required" name="phno" minlength="10" maxlength="15">
                            </div>
                            <div class="form-group">
                                <label for="inputPassword">Password</label>
                                <input type="password" class="form-control" id="inputPassword"
                                       placeholder="Password" required="required" name="password" minlength="6" maxlength="20">
                            </div>
                            <div class="form-check">
                                <input type="checkbox" class="form-check-input" id="checkTerms" name="check" required="required">
                                <label class="form-check-label" for="checkTerms">I agree to the terms and conditions</label>
                            </div>
                            <button type="submit" class="btn btn-primary mt-3">Submit</button>
                        </form>

                        <!-- OTP Verification Section -->
                        <div id="otpSection" class="hidden">
                            <form id="otpForm" method="post">
                                <div class="form-group mt-3">
                                    <label for="inputOTP">Enter OTP</label>
                                    <input type="text" class="form-control" id="inputOTP"
                                           placeholder="Enter OTP received on your email" required="required" name="otp">
                                </div>
                                <button type="submit" class="btn btn-secondary">Verify OTP</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%@ include file="all_component/footer.jsp" %>

    <script>
        $(document).ready(function() {
            $('#registrationForm').submit(function(event) {
                event.preventDefault(); // Prevent default form submission

                var formData = $(this).serialize(); // Serialize form data

                $.ajax({
                    type: 'POST',
                    url: 'register',
                    data: formData,
                    dataType: 'json',
                    success: function(response) {
                        if (response.success) {
                            $('#otpSection').removeClass('hidden'); // Show OTP section
                            $('#registrationForm').hide(); // Hide registration form after successful registration
                            $('#successMsg').text('OTP sent to your email. Please verify.'); // Display success message
                        } else {
                            $('#errorMsg').text(response.message); // Display error message
                        }
                    },
                    error: function(xhr, status, error) {
                        console.error('Error:', error);
                        $('#errorMsg').text('An error occurred. Please try again.'); // Display error message
                    }
                });
            });

            $('#otpForm').submit(function(event) {
                event.preventDefault(); // Prevent default form submission for OTP form

                var formData = $(this).serialize(); // Serialize OTP form data

                $.ajax({
                    type: 'POST',
                    url: 'verifyOTP',
                    data: formData,
                    dataType: 'json',
                    success: function(response) {
                        if (response.success) {
                            alert('OTP verification successful!'); // Handle success
                            window.location.href = 'register.jsp'; // Redirect to registration page
                        } else {
                            alert('OTP verification failed! Please try again.'); // Handle failure
                        }
                    },
                    error: function(xhr, status, error) {
                        console.error('Error:', error);
                        alert('An error occurred. Please try again.');
                    }
                });
            });
        });
    </script>
</body>
</html>

 --%>