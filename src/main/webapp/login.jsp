<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Paperback-Paradise : login</title>
    <%@ include file="all_component/allCss.jsp"%>
</head>
<body>
    <%@ include file="all_component/navbar.jsp"%>

    <div class="container pd-4">
        <div class="row">
            <div class="col-md-4 offset-md-4">
                <div class="card">
                    <div class="card-body">
                        <h3 class="text-center">Login</h3>
                        <c:if test="${not empty failMsg }">
                            <h5 class="text-center text-danger">${failMsg }</h5>
                            <c:remove var="failMsg" scope="session" />
                        </c:if>
                        <c:if test="${not empty succMsg }">
                            <h5 class="text-center text-success">${succMsg }</h5>
                            <c:remove var="succMsg" scope="session" />
                        </c:if>
                        <form action="login" method="post" onsubmit="return validateForm()">
                            <div class="form-group">
                                <label for="exampleInputEmail1">User Email-Id:</label>
                                <input type="email" class="form-control" id="exampleInputEmail1"
                                       aria-describedby="emailHelp" placeholder="User Email-Id"
                                       required="required" name="email" maxlength="50">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputPassword1">Password</label>
                                <input type="password" class="form-control" id="exampleInputPassword1"
                                       placeholder="Password" required="required" name="password" minlength="6" maxlength="20">
                            </div>
                            <div class="text-center">
                                <button type="submit" class="btn btn-primary">Login</button>
                                <br>
                                <a href="register.jsp">Create account</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script>
        function validateForm() {
            var email = document.getElementById('exampleInputEmail1').value;
            var password = document.getElementById('exampleInputPassword1').value;

            // Basic validation to check if fields are empty
            if (email.trim() === '' || password.trim() === '') {
                alert('Email and Password are required');
                return false;
            }

            // You can add more sophisticated validation if needed

            return true; // Form will submit if all validations pass
        }
    </script>
</body>
</html>
