<%@page import="model.Electricityboard"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<meta charset="ISO-8859-1">
<title>Electricityboard Branch Management</title>

<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<link rel="stylesheet" type="text/css" href="css\footer.css"> 
<script src="components/jquery-3.6.0.js"></script>
<script src="components/elec.js"></script>



 <nav class="navbar navbar-expand-md navbar-dark" style="background-color: 	#5353ff">
                   

                    <ul class="navbar-nav">
                        <li><a href="Index.jsp" class="nav-link">ElectroGrid</a></li>
                    </ul>
                 </nav>
               


</head>
<body>




<br>
<br>


<div class="container"> 
		<div class="row">  
		 <br>
            <div class="container col-md-5">
                <div class="card">
                    <div class="card-body">
                       

                        <caption>
                            <h2>
                                Electricityboard Branch Management
                            </h2>
                        </caption>
		
			
				<form id="formElectricityboard" name="formElectricityboard" method="post" action="Electricityboard.jsp">  
					Electricity Board Type:  
					<input id="etype" name="etype" type="text" class="form-control form-control-sm">  
					
					<br> 
					Branch Code:  
					<input id="branchCode" name="branchCode" type="text" class="form-control form-control-sm">  
					
					<br>
					 Location:  
					 <input id="location" name="location" type="text" class="form-control form-control-sm">  
					 
					 <br> 
					 Contact Number:  
					 <input id="contactnumber" name="contactnumber" type="text" class="form-control form-control-sm">  
					 
					
					 
					 
					 <br>  
					 <input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">  
					 <input type="hidden" id="hidelectricityboardIDSave" name="hidelectricityboardIDSave" value=""> 
					 
					 
				</form> 
				  </div>
                </div>
            </div>
    
				<div id="alertSuccess" class="alert alert-success"></div>  
				<div id="alertError" class="alert alert-danger"></div> 
				
				<br>
					
				
            <div class="row">
               

                <div class="container">
                    <h3 class="text-center">Electricityboard Details</h3>
                    <hr>
                    <div class="container text-left">

                        <a href="Index.jsp" class="btn btn-success"style="background-color: #5353ff">Navigate To Home page</a>
                        
                    </div>
                    <br>
                
                   <div id="divItemsGrid">   
					<%    
					    Electricityboard elecObj = new Electricityboard();
						out.print(elecObj.readElectricityboard());   
					%>  
				
					<br>
					<br>
					 <a href="Login.jsp" class="btn btn-success"style="background-color: 	#5353ff">Logout</a>
				</div> 
                   
                </div>
            </div>
				  
 			</div>
 		 
 		</div>    
 		
<br>
	 

</body>

 <!-- Site footer -->
    <footer class="site-footer">
      <div class="container">
        <div class="row">
          <div class="col-sm-12 col-md-6">
            <h6>About</h6>
            <p class="text-justify">Gunasinghe P.S.L<i> </i>PAF Project</p>
          </div>

          

          <div class="col-xs-6 col-md-3">
            <h6>Quick Links</h6>
            <ul class="footer-links">
              <li><a href="Index.jsp">HomePage</a></li>
              <li><a href="">Contact Us</a></li>
              <li><a href="">Contribute</a></li>
              <li><a href="">Privacy Policy</a></li>
              
            </ul>
          </div>
        </div>
        <hr>
      </div>
      <div class="container">
        <div class="row">
          <div class="col-md-8 col-sm-6 col-xs-12">
            <p class="copyright-text">Copyright &copy; 2022 All Rights Reserved by 
         <a href="#">IT20139858</a>.
            </p>
          </div>

          <div class="col-md-4 col-sm-6 col-xs-12">
            <ul class="social-icons">
              <li><a class="facebook" href="#"><i class="fa fa-facebook"></i></a></li>
              <li><a class="twitter" href="#"><i class="fa fa-twitter"></i></a></li>
              <li><a class="dribbble" href="#"><i class="fa fa-dribbble"></i></a></li>
              <li><a class="linkedin" href="#"><i class="fa fa-linkedin"></i></a></li>   
            </ul>
          </div>
        </div>
      </div>
</footer>
</html>