<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/main.js"></script>

<title>Payments</title>
</head>
<body>


	<div class="container">
		 <div class="row">
			 <div class="col-8">
			 
				 <h1 class="m-3">Payments details</h1>
				 <form id="formPayments">
				 
				 	<!-- AccountID -->
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="lblAccountID">Account ID: </span>
						</div>
						
						<input type="text" id="txtAccountID" name="txtAccountID">
					</div>
					
					<!-- Amount -->
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="lblAmount">Amount: </span>
						</div>
						
						<input type="text" id="txtAmount" name="txtAmount">
					</div>
					
					<!-- payMethod -->
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="lblPayMethod">Pay Method: </span>
						</div>
						
						<input type="text" id="txtPayMethod" name="txtPayMethod" placeholder="cash or credit">
					</div>
					
					<!-- payDate -->
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="lblPayDate">Pay Date: </span>
						</div>
						
						<input type="text" id="txtPayDate" name="txtPayDate" placeholder="yyyy-mm-dd">
					</div>
					
					
					<div id="alertSuccess" class="alert alert-success"></div>
 					<div id="alertError" class="alert alert-danger"></div>
					
					
					<input type="button" id="btnSave" value="Save" class="btn btn-primary">
					<input type="hidden" id="hidpaymentIDSave" name="hidpaymentIDSave" value="">
				 
				 </form>
			 </div>
		 </div>
		
		 <br>
		 <div class="row">
			 <div class="col-12" id="colPayments">
			
			 </div>
		 </div>
	</div>

</body>
</html>
