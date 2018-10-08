<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Vending Machine</title>
        <!-- Bootstrap core CSS -->
        <!--<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">-->  

        <!--CDN for Bootstrap 4-->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

        <!--google fonts api-->
        <link href="https://fonts.googleapis.com/css?family=Rajdhani:600|Righteous|Armata|Orbitron:400" rel="stylesheet">

        <!--font awesome-->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">

        <link rel="stylesheet" href="./css/customStyle.css">
        
    </head>
    <body>

        <div class="container">

            <div class="text-center mt-2">
                <h1>Vending Machine</h1>
            </div>

            <hr />

            <div class="row">

                <!-- Left div to hold all the snack buttons -->
                <div class="col-sm-8">

                    <!-- #snacksDiv will hold all the snack buttons -->
                    <div class="row justify-content-center" id="snacksDiv">

                        <c:forEach var="currentSnack" items="${snackList}">

                            <button type="button" class="btn btn-sm btn-light col-md-3 m-3 selection-button">
                                <a href="selectSnack?snackId=${currentSnack.snackId}">
                                    <p class="text-left selection-id"><c:out value="${currentSnack.snackId}"/></p>
                                    <div class="text-center">
                                        <p class="snack-name"><c:out value="${currentSnack.name}"/></p>
                                        <p class="snack-price">$<c:out value="${currentSnack.price}"/></p>
                                        <p class="snack-quantity">Quantity left: <c:out value="${currentSnack.quantity}"/></p>
                                    </div>
                                </a>
                            </button>

                        </c:forEach>

                    </div>

                </div>

                <!-- Right div to hold the three forms -->
                <div class="col-sm-4">

                    <!-- Form 1: displays money input and buttons for adding money -->
                    <form>
                        <div class="form-group text-center">
                            <label >Total <i class="fas fa-dollar-sign"></i> In</label>
                            <input type="text" class="form-control bg-dark text-success" name="money-in" id="money-in"
                                   placeholder="$0.00" value="$ ${moneyIn}" readonly>
                        </div>

                        <div class="form-group">
                            <div class="row text-center">
                                <div class="col-sm-6">
                                    
                                    <!--routing to the /addMoney endpoint with parameter of money=1.00-->
                                    <a href="addMoney?money=1.00">
                                        <button type="button" id="add-dollar" class="btn btn-light m-1">Add Dollar</button>
                                    </a>
                                </div>
                                <div class="col-sm-6">
                                    <a href="addMoney?money=.25">
                                        <button type="button" id="add-quarter" class="btn btn-light m-1">Add Quarter</button>
                                    </a>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="row text-center">
                                <div class="col-sm-6">
                                    <a href="addMoney?money=.1">
                                        <button type="button" id="add-dime" class="btn btn-light m-1">Add Dime</button>
                                    </a>
                                </div>
                                <div class="col-sm-6">
                                    <a href="addMoney?money=.05">
                                        <button type="button" id="add-nickel" class="btn btn-light m-1">Add Nickel</button>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </form>

                    <hr>

                    <!-- Form 2: displays messages, item number selected and make-purchase-button -->
                    <form id="messages">
                        <div class="form-group text-center">
                            <label class="m-2" for="messages">Messages <i class="fas fa-comment"></i></label>
                            <input type="text" class="form-control text-center bg-dark text-success" id="messages" value="${message}" readonly>

                            <label class="m-2" for="item">Item <i class="fas fa-sort-numeric-down"></i></label>
                            <input type="text" class="form-control bg-dark text-success" name="item" id="item" value="${selectedSnack.snackId}" readonly>

                            <!--clicking the make purchase button routes to the /makePurchase endpoint-->
                            <a href="makePurchase">
                                <button type="button" name="make-purchase-button" id="make-purchase-button" class="btn btn-light m-2">Make
                                    Purchase</button>
                            </a>
                        </div>
                    </form>

                    <hr>

                    <!-- Form 3: displays change and change return button -->
                    <form id="change">
                        <div class="text-center">
                            <label for="change">Change <i class="fas fa-hand-holding-usd"></i></label>
                            <input type="text" class="form-control text-center bg-dark text-success" name="change" id="change-display" value="${changeReturned}"
                                   readonly>
                            
                            <!--clicking the "Change Return" button routes to /getChange endpoint--> 
                            <a href="getChange">
                                <button type="button" id="change-button" class="btn btn-light m-2">Change Return</button>
                            </a>
                        </div>
                    </form>

                </div>

            </div>
        </div>

        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>