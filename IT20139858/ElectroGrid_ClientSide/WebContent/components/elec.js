$(document).ready(function() 
{  
	if ($("#alertSuccess").text().trim() == "")  
	{   
		$("#alertSuccess").hide();  
	} 
	$("#alertError").hide(); 
}); 

//SAVE ============================================ 
$(document).on("click", "#btnSave", function(event) 
{  
	// Clear alerts---------------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 

	// Form validation-------------------  
	var status = validateElectricityboardForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 

	// If valid------------------------  
	var t = ($("#hidelectricityboardIDSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
	{
		url : "ElectricityboardApi",
		type : t,
		data : $("#formElectricityboard").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			onElectricityboardSaveComplete(response.responseText, status);
		}
	});
}); 

function onElectricityboardSaveComplete(response, status){
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Saved.");
			$("#alertSuccess").show();
					
			$("#divItemsGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While Saving.");
		$("#slertError").show();
	}else{
		$("#alertError").text("Unknown Error while Saving.");
		$("#alertError").show();
	}
	$("#hidelectricityboardIDSave").val("");
	$("#formElectricityboard")[0].reset();
}

//UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
		{     
	$("#hidelectricityboardIDSave").val($(this).closest("tr").find('#hidelectricityboardIDUpdate').val());     
	$("#etype").val($(this).closest("tr").find('td:eq(0)').text());    
	$("#branchCode").val($(this).closest("tr").find('td:eq(1)').text());     
	$("#location").val($(this).closest("tr").find('td:eq(2)').text());     
	$("#contactnumber").val($(this).closest("tr").find('td:eq(3)').text()); 
	

});


//Remove Operation
$(document).on("click", ".btnRemove", function(event){
	$.ajax(
	{
		url : "ElectricityboardApi",
		type : "DELETE",
		data : "electricityboardID=" + $(this).data("electricityboardid"),
		dataType : "text",
		complete : function(response, status)
		{
			onElectricityboardDeletedComplete(response.responseText, status);
		}
	});
});

function onElectricityboardDeletedComplete(response, status)
{
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Deleted.");
			$("#alertSuccess").show();
					
			$("#divItemsGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While Deleting.");
		$("#alertError").show();
	}else{
		$("#alertError").text("Unknown Error While Deleting.");
		$("#alertError").show();
	}
}

//CLIENTMODEL
function validateElectricityboardForm() {  
	// Electricityboardtype   
	if ($("#etype").val().trim() == "")  {   
		return "Insert etype .";  
		
	} 
	
	 // branchCode
	if ($("#branchCode").val().trim() == "")  {   
		return "Insert branchCode.";  
	} 
	
	
	//Location
	if ($("#location").val().trim() == "")  {   
		return "Insert location."; 
		 
	}
	 
	 // is numerical value  
	var tmpMobile = $("#contactnumber").val().trim();  
	if (!$.isNumeric(tmpMobile))  {   
		return "Insert a numerical value for Mobile Number.";  
		
	}
	 
	
		

	 
	 return true; 
	 
}
