<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	/* function showRegisterPage() {

		var form = document.getElementById("loginform");
		form.action = "registerPage";
		form.submit();
	} */
</script>
<style>
.error {
	color: #ff0000;
	font-style: italic;
	font-weight: normal;
}
</style>
<body>

	<form:form action="register" method="post" modelAttribute="userModel">
	<form:errors path="*" cssClass="error" />
		<form:input path="userId" />
		</br>
		User Name: <form:input path="userName" />
		<form:errors path="userName" cssClass="error" />
		</br>
		
		First Name: <form:input path="fName" /><form:errors path="fName" cssClass="error" />
		</br>
		Last Name:  <form:input path="lName" />
		</br>

		<form:select path="country">
			<c:forEach items="${country}" var="country">
				<form:option value="${country.counrtyName}">${country.counrtyName}</form:option>
			</c:forEach>

		</form:select>
		</br>
		<input type="submit" value="Regiser User">


		<select>
			<c:forEach items="${country}" var="country">
				<option value="${country.counrtyName}">${country.counrtyName}</option>
			</c:forEach>
		</select>
	</form:form>
</body>
</html>