<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<spring:message code="hello"/> <br>
	<spring:message code="passLen.pass"/> <br>
	
	<form action="/main">
		<select name="lang">
			<option value="ko">한국어</option>
			<option value="en">english</option>
		</select>
		<input type="submit" value="전송"/>
	</form>
<!-- 	main.jsp TEST -->
<!-- 	<a href="/main?userNm=브라운">link</a>  -->
</body>
</html>