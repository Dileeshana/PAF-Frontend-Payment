 $(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	 {
	 	$("#alertSuccess").hide();
	 }
	 $("#alertError").hide(); 

}); 


$(document).on("click", "#btnSave", function(event)
{
	//Clear status msges---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	//Form validation-------------------
	var status = validateItemForm();
	
	//If not valid
	if (status != true)
	{
		 $("#alertError").text(status);
		 $("#alertError").show();
		return;
	}
	
	//If valid
	$("#formPayments").submit();
	
	var type = ($("#hidpaymentIDSave").val() == "") ? "POST" : "PUT";

	$.ajax(
 	{
 		url : "PaymentAPI",
 		type : type,
 		data : $("#formPayments").serialize(),
 		dataType : "text",
 		complete : function(response, status)
 		{
 			onPaymentSaveComplete(response.responseText, status);
 		}
 	}); 
});



function onPaymentSaveComplete(response, status)
	{
		if (status == "success")
		{
			 var resultSet = JSON.parse(response);
 			 if (resultSet.status.trim() == "success")
			 {
 				$("#alertSuccess").text("Successfully saved.");
 				$("#alertSuccess").show();
 				$("#divMeterGrid").html(resultSet.data);
 			 } 
 			 else if (resultSet.status.trim() == "error")
			 {
 				$("#alertError").text(resultSet.data);
 				$("#alertError").show();
 			 }
 		} 
 		else if (status == "error")
 		{
 			$("#alertError").text("Error while saving.");
 			$("#alertError").show();
 		} 
 		else
 		{
 			$("#alertError").text("Unknown error while saving..");
 			$("#alertError").show();
 		}
		$("#hidMeterIDSave").val("");
 		$("#formMeter")[0].reset();
}


// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
	$("#hidpaymentIDSave").val($(this).data("paymentID"));
	$("#txtAccountID").val($(this).closest("tr").find('td:eq(0)').text());
	$("#txtAmount").val($(this).closest("tr").find('td:eq(1)').text());
	$("#txtPayMethod").val($(this).closest("tr").find('td:eq(2)').text());
	$("#txtPayDate").val($(this).closest("tr").find('td:eq(3)').text());
}); 


// REMOVE==========================================
$(document).on("click", ".btnRemove", function(event)
{
	 $.ajax(
 		{
 			url : "PaymentAPI",
 			type : "DELETE",
 			data : "paymentID=" + $(this).data("paymentID"),
 			dataType : "text",
 			complete : function(response, status)
 			{
 				onMeterDeleteComplete(response.responseText, status);
 			}
 		});
}); 

function onPaymentDeleteComplete(response, status)
{
		if (status == "success")
 		{
 			var resultSet = JSON.parse(response);
 			if (resultSet.status.trim() == "success")
 			{
 				$("#alertSuccess").text("Successfully deleted.");
 				$("#alertSuccess").show();
 				$("#divMeterGrid").html(resultSet.data);
 			} 
 			else if (resultSet.status.trim() == "error")
 			{
 				$("#alertError").text(resultSet.data);
 				$("#alertError").show();
 			}
 		} 
 		else if (status == "error")
 		{
 				$("#alertError").text("Error while deleting.");
 				$("#alertError").show();
 		} 
 		else
 		{
 				$("#alertError").text("Unknown error while deleting..");
 				$("#alertError").show();
 		}
}


function validateItemForm()
{
	//accountID
	if ($("#txtAccountID").val().trim() == "")
	{
		return "Insert Account ID";
	}
	
	//amount
	if ($("#txtAmount").val().trim() == "")
	{
		return "Insert Amount";
	}
	
	//PayMethod
	if ($("#txtPayMethod").val() == "")
	{
		return "Insert Pay Method";
	}
	
	//PayDate
	if ($("#txtPayDate").val() == "")
	{
		return "Insert Date";
	}
	
	return true;
}


function getPaymentCard(accountID, amount, payMethod, payDate)
{
	
	var payment = "";
	payment += "<div class=\"student card bg-light m-2\" style=\"max-width: 10rem; float: left;\">";
	payment += "<div class=\"card-body\">";
	payment += "accountID: " + accountID + ",";
	payment += "<br>";
	payment += "Amount: " + amount + ",";
	payment += "<br>";
	payment += "Pay Method: " + payMethod + ",";
	payment += "<br>";
	payment += "Date: " + payDate;
	payment += "</div>";
	payment += "<input type=\"button\" value=\"Remove\" class=\"btn btn-danger remove\">";
	payment += "</div>";
	
	return payment;
}

