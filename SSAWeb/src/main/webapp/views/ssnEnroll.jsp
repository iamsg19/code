<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SSN Enrollment</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
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
</head>
<body>
	<h3 style="color: green">${successMsg}</h3>
	<h3 style="color: red">${failureMsg}</h3>
	<form:form id="form" method="POST" action="saveSsn"
		enctype="multipart/form-data" modelAttribute="ssaModelData">
		<table>
			<tr>
				<th>First Name</th>
				<td><form:input id="firstname" path="firstName"
						placeholder="First Name" /></td>
				<div id="error"></div>
			</tr>
			<tr>
				<th>Last Name</th>
				<td><form:input id="lastname" path="lastName"
						placeholder="Last Name" /></td>
			</tr>
			<tr>
				<th>Date of Birth</th>
				<td><form:input path="dob" id="datepicker" /></td>
			</tr>
			<tr>
				<th>Gender</th>
				<td><form:radiobuttons id="gender" path="gender"
						items="${gender}" /></td>
			</tr>
			<tr>
				<th>Phone No.</th>
				<td><form:input id="phoneno" path="phoneNo"
						placeholder="Phone No" /></td>
			</tr>
			<tr>
				<th>State</th>
				<td><form:select id="state" path="state" items="${states}"></form:select></td>
			</tr>
			<tr>
				<th>Select Pic</th>
				<td><form:input id="photo" path="photo" type="file" /></td>
			</tr>
			<tr>
				<td><input type="Reset" value="Reset" /></td>
				<td><input type="Submit" value="Enroll" id="submit" /></td>
			</tr>
		</table>
	</form:form>
	<!-- <script src="https://code.jquery.com/jquery-1.11.1.min.js"></script> -->
	
	<script
		src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
	<!-- <script
		src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script> -->
	<script >
		$(document).ready(function(){
			$("#form").validate({
				
				rules	:	{
					firstName	:	'required',
					lastName	:	'required',
					gender	:	'required',
					state	:	'required',
					photo	:	'required',
					dob	:	'required'
				},
				
				messages	:	{
					
					firstname	:	'First Name is required',
					lastname	:	'Last name is required',
					dob	:	'Date of birth is required',
					state	:	'state is required',
					photo	:	'photo is required'
				}
			});
		});
	</script>
	
	<a href="viewData">View SSN data</a>
</body>
</html>