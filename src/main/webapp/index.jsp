<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<br><br><br><br><br><br><br><br>
	<center>
		<form:form action="${ctx}/appuser/easyRegist" method="post">
			手机:<input type="text" name="phoneNo"/><br><br>
			密码:<input type="text" name="password"/><br><br>
			重复密码:<input type="text" name="reppassword"/><br><br>
			<input type="submit" value="简单注册"/>
		</form:form>
		<hr>
		<form:form action="${ctx}/yuanfen/login" method="post">
			乘友/车友:<input type="text" name="phoneNo"/><br><br>
			电话:<input type="text" name="password"/><br><br>
			<input type="submit" value="登录"/>
		</form:form>
		<hr>
		<form:form action="${ctx}/yuanfen/tripStatus" method="post">
			乘友/车友:<input type="text" name="tripStatus"/><br><br>
			电话:<input type="text" name="phoneNo"/><br><br>
			<input type="submit" value="设置乘友/车友"/>
		</form:form>
		<hr>
		<a href="${ctx}/yuanfen/perfectedDrive?username=hzc&gender=nan&height=175&phoneNo=1234">详细注册</a>
		<hr>
		<form:form action="${ctx}/yuanfen/perfectedDrive" method="post">
			<input type="hidden" name="username"/><br><br>
			<input type="hidden" name="gender"/><br><br>
			<input type="hidden" name="height"/><br><br>
			<input type="hidden" name="birth"/><br><br>
			<input type="hidden" name="location"/><br><br>
			<input type="hidden" name="maritalStatus"/><br><br>
			<input type="hidden" name="education"/><br><br>
			<input type="hidden" name="prefession"/><br><br>
			<input type="hidden" name="income"/><br><br>
			<input type="hidden" name="interest"/><br><br>
			<input type="hidden" name="phoneNo"/><br><br>
			<input type="submit" value="乘友完善信息"/>
		</form:form>
		<hr>
		<form:form action="${ctx}/yuanfen/perfectedCarFriend" method="post">
			<input type="hidden" name="username" value="hzc"/><br><br>
			<input type="hidden" name="gender" value="nan"/><br><br>
			<input type="hidden" name="height" value="173"/><br><br>
			<input type="hidden" name="birth" value="1993-4-20"/><br><br>
			<input type="hidden" name="location" value="hubei"/><br><br>
			<input type="hidden" name="maritalStatus" value="weihun"/><br><br>
			<input type="hidden" name="education" value="daxue"/><br><br>
			<input type="hidden" name="prefession" value="IT"/><br><br>
			<input type="hidden" name="income" value="5000"/><br><br>
			<input type="hidden" name="interest" value="music"/><br><br>
			<input type="hidden" name="phoneNo" value="123456"/><br><br>
			
			<input type="hidden" name="carId" value="22222"/><br><br>
			<input type="hidden" name="carNo" value="benz"/><br><br>
			<input type="hidden" name="carIdBirth" value="2010-1-1"/><br><br>
			<input type="hidden" name="carIs" value="benren"/><br><br>
			<input type="submit" value="车友完善信息"/>
		</form:form>
		<hr>
		<form:form action="${ctx}/yuanfen/uploadphoto" enctype="multipart/form-data">
			<input type="file" name="file"/>
			<input type="submit" value="上传 "/>
		</form:form>
		<hr>
		<a href="${ctx}/myself/finddata?phoneNo=15071468991&tripStatus='乘友'">查询乘友资料</a>
		<hr>
		<form:form action="${ctx}/myself/finddata" method="post">
			<input type="hidden" name="phoneNo" value="123456">
			<input type="hidden" name="tripStatus" value="车友">
			<input type="submit" value="查询车友资料"/>
		</form:form>
		<hr>
		<form:form action="${ctx}/myself/drivedatamodify" method="post">
			<input type="hidden" name="phoneNo" value="15071468991">
			<input type="hidden" name="username" value="洪峥灿">
			<input type="hidden" name="location" value="孝感">
			<input type="hidden" name="maritalStatus" value="未婚">
			<input type="hidden" name="education" value="大学">
			<input type="hidden" name="prefession" value="IT工程师">
			<input type="hidden" name="income" value="3000-5000">
			<input type="hidden" name="interest" value="洪峥灿">
			<input type="submit" value="修改乘友资料"/>
		</form:form>
		<hr>
		<form:form action="${ctx}/myself/carfrienddatamodify" method="post">
			<input type="hidden" name="phoneNo" value="15171468991">
			<input type="hidden" name="username" value="洪峥灿">
			<input type="hidden" name="location" value="孝感">
			<input type="hidden" name="maritalStatus" value="未婚">
			<input type="hidden" name="education" value="大学">
			<input type="hidden" name="prefession" value="IT工程师">
			<input type="hidden" name="income" value="3000-5000">
			<input type="hidden" name="interest" value="看电视">
			<input type="hidden" name="carId" value="鄂K99999">
			<input type="hidden" name="carNo" value="法拉利99">
			<input type="hidden" name="carIdBirth" value="1990-1-1">
			<input type="hidden" name="carIs" value="本人">
			<input type="submit" value="修改车友资料"/>
		</form:form>
		<hr>
		<form:form action="${ctx}/myself/updatefricondition" method="post">
			<input type="hidden" name="phoneNo" value="111111">
			<input type="hidden" name="tripStatus" value="车友">
			<input type="hidden" name="ageRa" value="15-20">
			<input type="hidden" name="heightRa" value="165-175">
			<input type="hidden" name="incomeRa" value="3000-5000">
			<input type="hidden" name="education" value="大学">
			<input type="hidden" name="maritalStatus" value="未婚">
			<input type="hidden" name="location" value="北京">
			<input type="submit" value="修改择友条件"/>
		</form:form>
		<hr>
		<form:form action="${ctx}/myself/heartModify" method="post">
			<input type="hidden" name="phoneNo" value="111111">
			<input type="hidden" name="tripStatus" value="车友">
			<input type="hidden" name="heart" value="111111的内心独白">
			<input type="submit" value="修改内心独白"/>
		</form:form>
		<hr>
		<form:form action="${ctx}/trip/addTrip" method="post">
			<input type="hidden" name="phoneNo" value="1234">
			<input type="hidden" name="tripStatus" value="车友">
			<input type="hidden" name="startArea" value="生命科学园">
			<input type="hidden" name="tripTime" value="2016-2-8">
			<input type="hidden" name="endArea" value="平西府">
			<input type="hidden" name="drive" value="30">
			<input type="hidden" name="regularORtimely" value="固定线路">
			<input type="submit" value="添加车友线路"/>
		</form:form>
		<hr>
		<form:form action="${ctx}/trip/addTrip" method="post">
			<input type="hidden" name="phoneNo" value="1234">
			<input type="hidden" name="tripStatus" value="乘友">
			<input type="hidden" name="startArea" value="生命科学园">
			<input type="hidden" name="tripTime" value="2016-2-8">
			<input type="hidden" name="endArea" value="平西府">
			<input type="hidden" name="drive" value="30">
			<input type="hidden" name="regularORtimely" value="及时线路">
			<input type="submit" value="添加乘友线路"/>
		</form:form>
		<hr>
		<form:form action="${ctx}/trip/findTrip" method="post">
			<input type="hidden" name="phoneNo" value="12345">
			<input type="hidden" name="tripStatus" value="车友">
			<input type="submit" value="查找线路/1、车友"/>
		</form:form>
		<hr>
		<form:form action="${ctx}/account/findAccount" method="post">
			<input type="hidden" name="phoneNo" value="1">
			<input type="hidden" name="tripStatus" value="车友">
			<input type="submit" value="查看自己的账户"/>
		</form:form>
		<hr>
		<form:form action="${ctx}/account/findAccount" method="post">
			<input type="hidden" name="phoneNo" value="1">
			<input type="hidden" name="tripStatus" value="乘友">
			<input type="submit" value="查看自己的账户"/>
		</form:form>
		<hr>
		<form:form action="${ctx}/account/updateAccount" method="post">
			<input type="text" name="phoneNo"><br>
			<input type="text" name="tcar" ><br>
			<input type="text" name="flower" ><br>
			<input type="submit" value="充值账户"/>
		</form:form>
		<hr>
		<form:form action="${ctx}/account/updateAccount" method="post">
			<input type="text" name="phoneNo"><br>
			<input type="text" name="tcar" ><br>
			<input type="text" name="flower" ><br>
			<input type="submit" value="消费账户"/>
		</form:form>
		<hr>
		<hr>
		<form:form action="${ctx}/Fujin/fujin" method="post">
			<input type="text" name="phoneNo"><br>
			<input type="text" name="tripStatus" ><br>
			<input type="submit" value="查看附近"/>
		</form:form>
		<hr>
		<form:form action="${ctx}/myself/quickRegist" method="post">
			电话:<input type="text" name="phoneNo"><br>
			乘客、车主:<input type="text" name="tripStatus" ><br>
			昵称:<input type="text" name="username" ><br>
			性别:<input type="text" name="gender" ><br>
			身高:<input type="text" name="height" ><br>
			生日:<input type="text" name="birth" ><br>
			地区:<input type="text" name="location" ><br>
			婚姻:<input type="text" name="maritalStatus" ><br>
			教育:<input type="text" name="education" ><br>
			工作:<input type="text" name="prefession" ><br>
			收入:<input type="text" name="income" ><br>
			兴趣:<input type="text" name="interest" ><br>
			车牌号:<input type="text" name="carId" ><br>
			车型号:<input type="text" name="carNo" ><br>
			上牌日期:<input type="text" name="carIdBirth" ><br>
			车辆所属:<input type="text" name="carIs" ><br>
			<input type="submit" value="快速注册"/>
		</form:form>
	</center>
</body>
</html>