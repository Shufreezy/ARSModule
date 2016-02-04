<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <c:set var="req" value="${pageContext.request}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="resources/style_ars.css" type="text/css">
<title>Aircon Reservation System</title>



</head>
<body>


<center><div class="form-style-2">
<div class="form-style-2-heading">Add Aircon Schedule</div>
<form action="${req.contextPath }/api/aircon_schedule/createNewSchedule" method="post">
<label for="field1"><span>Aircon Name<span class="required">*</span></span>
	
	<!--
	${airconList.size()}
	<c:if test="${airconList} eq 0">
		<p>No Air Conditioner existing yet. <a href="">Add new Air Conditioner!</a></p>
	</c:if>-->
	<select name="aircon_id" style="width:235px; height:35px;">
		<c:forEach items="${airconList}" var="aircon" >
		<option value="${aircon.id}">${aircon.name}</option>
		</c:forEach>
	</select>
</label>
<label for="field1"><span>Date<span class="required">*</span> </span><input type="date" class="input-field" name="date" required></label>
<label for="field1"><span>Time Start <span class="required">*</span></span></span><input type="time" class="input-field" name="start" required></label>
<label for="field1"><span>Time End <span class="required">*</span></span></span><input type="time" class="input-field" name="end" required></label>
<!--  <label for="field1"><span>Date Created </span></span><input type="date" class="input-field" name="end" required></label>-->


<label><span>&nbsp;</span><input type="submit" value="Add Schedule"></label>
</form>

<br>
<br>
<center><a href="./" href="form" style="text-transform:uppercase; text-decoration:none; color:orange; font-weight:bold; color:blue;"> VIEW LIST OF AIRCON SCHEDULE</a></center>



</body>
</html>