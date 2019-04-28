<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h3 style="color:green">${successMsg}</h3>
<h3 style="color:red">${failureMsg}</h3>
	<form:form method="POST" action="saveSsn" enctype="multipart/form-data" modelAttribute="ssaModelData">
		<table>
			<tr>
				<th>First Name</th>
				<td><form:input path="firstName" placeholder="First Name"/></td>
			</tr>
			<tr>
				<th>Last Name</th>
				<td><form:input path="lastName" placeholder="Last Name"/></td>
			</tr>
			<tr>
				<th>Date of Birth</th>
				<td><form:input path="dob" /></td>
			</tr>
			<tr>
				<th>Gender</th>
				<td><form:radiobuttons path="gender" items="${gender}"/></td>
			</tr>
			<tr>
				<th>Phone No.</th>
				<td><form:input path="phoneNo" placeholder="Phone No"/></td>
			</tr>
			<tr>
				<th>State</th>
				<td><form:select path="state" items="${states}"></form:select></td>
			</tr>
			<tr>
				<th>Select Pic</th>
				<td><form:input path="photo" type="file"/></td>
			</tr>
			<tr>
				<td><input type="Reset" value="Reset" /></td>
				<td><input type="Submit" value="Enroll" /></td>
			</tr>
		</table>
	</form:form>
	<a href="viewData">View SSN data</a>
</body>
</html>