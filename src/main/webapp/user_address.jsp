<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>User Address</title>
    <%@ include file="all_component/allCss.jsp"%>
</head>
<body>
    <%@ include file="all_component/navbar.jsp"%>
    <div class="container">
        <div class="row">
            <div class="col-md-6 offset-md-3">
                <div class="card">
                    <div class="card-body">
                        <h5 class="text-center text-primary p-1">Add Address</h5>

                        <form method="post" action="addAddress">

                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label for="inputAddress">Address</label>
                                    <input type="text" class="form-control" id="inputAddress"
                                           placeholder="Enter address" required="required" name="address"
                                           maxlength="50">
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="inputLandmark">Landmark</label>
                                    <input type="text" class="form-control" id="inputLandmark"
                                           placeholder="Enter landmark" required="required" name="landmark"
                                           maxlength="20">
                                </div>
                            </div>

                            <div class="form-row">
                                <div class="form-group col-md-4">
                                    <label for="inputCity">City</label>
                                    <input type="text" class="form-control" id="inputCity"
                                           placeholder="Enter city" required="required" name="city"
                                           maxlength="20">
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="inputState">State</label>
                                    <input type="text" class="form-control" id="inputState"
                                           placeholder="Enter state" required="required" name="state"
                                           maxlength="15">
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="inputZip">Zip</label>
                                    <input type="text" class="form-control" id="inputZip"
                                           placeholder="Enter zip" required="required" name="zip"
                                           maxlength="10" pattern="[0-9]{5}">
                                    <small class="form-text text-muted">Enter a valid 5-digit ZIP code.</small>
                                </div>
                            </div>

                            <div class="text-center">
                                <button type="submit" class="btn btn-warning">Add Address</button>
                            </div>

                        </form>

                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
