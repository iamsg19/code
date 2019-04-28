<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
.center {
	margin: auto;
	width: auto;
	border: 3px solid #73AD21;
	padding: 10px;
	text-align:center;
}
</style>
</head>
<body>
	<h3>Welcome</h3>
	<div class="center">
		<table border="2" class="table">
		<thead>
			<tr>
				<th>SSN No.</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>DOB</th>
				<th>Gender</th>
				<th>State</th>
				<th>Registered Time</Creation>
			</tr>
		</thead>
		<tbody>
			<c:if test="${!empty ssaModelData}">
				<c:forEach 
					items="${ssaModelData}"
					var="get">
					
					<tr>
						<td>${get.ssn}</td>
						<td>${get.firstName}</td>
						<td>${get.lastName}</td>
						<td>${get.dob}</td>
						<td>${get.gender}</td>
						<td>${get.state}</td>
						<td>${get.createdTime}</td>
					</tr>
			
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	<h3 style="color:red">${dataNotAvl}</h3>
	</div>
	<br>
	
</body>
</html>