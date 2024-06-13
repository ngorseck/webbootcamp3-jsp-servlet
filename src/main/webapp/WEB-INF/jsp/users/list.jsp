<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<!-- integration du menu -->
		<jsp:include page="../welcome.jsp"></jsp:include>
		<div class="container">
			<table class="table">
				<thead>
					<tr>
						<th>ID</th>
						<th>Email</th>
						<th>Etat</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${usersList }" var="user">
					  <tr>
						<td>${user.id }</td>
						<td>${user.email }</td>
						<td>
							<c:if test="${user.state == true }">
								Activé
							</c:if>
							<c:if test="${user.state == false }">
								Desactivé
							</c:if>
						</td>
					  </tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="container">
			<form action="admin" method="post">
			  <div class="mb-3">
			    <label for="exampleInputEmail1" class="form-label">First name</label>
			    <input type="text" name="firstName" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
			  </div>
			  <div class="mb-3">
			    <label for="exampleInputEmail1" class="form-label">Last name</label>
			    <input type="text" name="lastName" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
			  </div>
			  <div class="mb-3">
			    <label for="exampleInputEmail1" class="form-label">Email address</label>
			    <input type="text" name="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
			  </div>
			  <div class="mb-3">
			    <label for="exampleInputPassword1" class="form-label">Password</label>
			    <input type="password" name="password" class="form-control" id="exampleInputPassword1">
			  </div>
			  
			  <button type="submit" class="btn btn-primary">Submit</button>
			</form>
		</div>
	</body>
</html>