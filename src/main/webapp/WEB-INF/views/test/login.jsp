<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<br><br><br><br><br><br><br><br>
	<center>
		<table border="1" cellspacing="2" cellpadding="10">
			<form:form action="${pageContext.request.contextPath }/appuser/login"  method="post" modelAttribute="appuser">
				<tr><td colspan="2" align="center">请填写用户信息</td></tr>
					<tr>
					<td>电话：</td>
					<td><form:input path="phoneNo"/><%-- <span style="color:red"><form:errors path="username"/></span> --%></td>
					</tr>
					<tr>
					<td>密码：</td>
					<td><form:input path="password"/><%-- <span style="color:red"><form:errors path="password"/> --%></span></td>
					</tr>
					<tr>
						<td align="center" colspan="2"><input type="submit" value="Login"/></td>
					</tr>
			</form:form>
		</table>
	</center>
</body>
</html>