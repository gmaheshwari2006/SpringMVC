<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<script type="text/javascript">
function showRegisterPage(){
	
	var form = document.getElementById("loginform");
	form.action = "registerPage";
	form.submit();
}
</script>
<body>
	Welcome to radical tech for spring
	</br> Institute Name is: ${name}

	<form:form action="login" method="get" id="loginform">
		User Name: <input type="text" name="username" id="user">
		</br>
		Password: <input type="text" name="password" id="password">
		</br>

		<input type="submit" value="Enter">
		<select>
			<c:forEach items="${country}" var="contry">
				<option value="${contry.counrtyName}">${contry.counrtyName}</option>
			</c:forEach>
		</select>
		<!-- <input type="button" value="Register" onclick="showRegisterPage();"> -->
		<a href="<c:url value="/registerPage"/>">RegisterUser</a>
		
		<a href="<c:url value="/pamartest?city=delhi"/>">Test Params</a>
		<a href="http://google.com">google</a>
		</br>
		<a href="<c:url value="/pathvar/mumbai?param=15"/>">Test Path Variable</a>



	</form:form>
</body>
</html>