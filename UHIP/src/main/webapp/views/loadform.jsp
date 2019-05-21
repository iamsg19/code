<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Account Form</title>

<!-- <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
	
<script>
  $( function() {
    $( "#datepicker" ).datepicker({
      changeMonth: true,
      changeYear: true
    });
  } );
  </script>
 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
  $("#mail").blur(function(){
	  
	  $.ajax({
		  type: 'GET',
		  url: window.location+'/show',
		  data: {
			  'userEmail' :$("#mail").val()
		  },
		  success :	function(data)
		  {
			  if(data=="Duplicate")
			  {
				$("#errorMsg").html("Email already register");
				$("#create").attr('disabled',true);
			  } 
			  else
			  {
				  $("#create").attr('disabled',false);
			  }
		  }
	});
  });
});
</script>
</head>
<body>
	<center><h1 style="color:red">---Enter the form details---</h1></center>
	<center>
	<h3 style="color:green">${success}</h3>
	<h3 style="color:green">${emailStatus}</h3>
	<h3 stylr="color:red">${failure}</h3>
	<form:form action="createuser" method="POST" id="form" modelAttribute="userAccModel">
		<table width="280px" border="1">
			<tr>
				<th>First Name</th>
				<td><form:input path="firstName" placeholder="Enter First Name"/></td>
			</tr>
			<tr>
				<th>Last Name</th>
				<td><form:input path="lastName" placeholder="Enter Last Name"/></td>
			</tr>
			<tr>
				<th>Email</th>
				<td><form:input id="mail" path="userEmail" placeholder="Enter Email"/></td><span style="color:red"id="errorMsg"></span>
				
			</tr>
			<tr>
				<th>Date of Birth</th>
				<td><form:input path="userDob" id="datepicker" placeholder="Enter DOB"/></td>
			</tr>
			<tr>
				<th>Gender</th>
				<td><form:radiobuttons path="gender" items="${gender}"/></td>
			</tr>
			<tr>
				<th>SSN</th>
				<td><form:input path="ssn" placeholder="Enter SSN"/></td>
			</tr>
			<tr>
				<th>Password</th>
				<td><form:password path="password" placeholder="Enter Password"/></td>
			</tr>
			<tr>
				<th>User Role</th>
				<td><form:select path="userRole" items="${role}"></form:select></td>
			</tr>
			<tr>
				<td><input type="reset" value="Reset"/></td>
				<td><input type="submit" id="create" value="Create User"/></td>
			</tr>
		</table>
	</form:form>
	<h3><a href="/getAllAccounts">Show All Accounts</a></h3>
	</center>
</body>
</html>