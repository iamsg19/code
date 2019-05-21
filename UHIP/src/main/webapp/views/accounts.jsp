<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All Accounts</title>
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css"/>
<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<style type="text/css" ></style>
<script type="text/javascript">
$(document).ready(function() {
    $('#ssnTable').DataTable( {
        "pagingType": "full_numbers"
    } );
} );
</script>
</head>
<body>
	<center><h3 style="color:red">${status}</h3></center>
	<div class="center">
		<table border="2" class="table" id="ssnTable">
		<thead>
			<tr>
				<th>SSN No.</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
				<th>DOB</th>
				<th>Gender</th>
				<th>Role</th>
				<th>Edit</th>
				<th>Activate/De-Activate</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${!empty listModel}">
				<c:forEach 
					items="${listModel}"
					var="get">
					
					<tr>
						<td>${get.ssn}</td>
						<td>${get.firstName}</td>
						<td>${get.lastName}</td>
						<td>${get.userEmail}</td>
						<td>${get.userDob}</td>
						<td>${get.gender}</td>
						<td>${get.userRole}</td>
						<td><a href="#">Edit</a></td>
						<td>
							<c:if test="${get.activeSwitch=='Y'}">
								<a href="/activateDeactivate/${get.userId}/${get.activeSwitch}" onclick="deactConfirm()">De-Activate</a>
							</c:if>
							<c:if test="${get.activeSwitch=='N'}">
								<a href="/activateDeactivate/${get.userId}/${get.activeSwitch}" onclick="actConfirm()">Activate</a>
							</c:if>
						</td>
					</tr>
			
				</c:forEach>
			</c:if>
		</tbody>
		
	</table>
	<center><h3 style="color:red">${noData}</h3></center>
	</div>
	<script type="text/javascript">
		function deactConfirm()
		{
			return confirm("Sure you want to deactivate this account?");
		}
		
		function actConfirm()
		{
			return confirm("You want to activate this account?");
		}
	</script>
</body>
</html>