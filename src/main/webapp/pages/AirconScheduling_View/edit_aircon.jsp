<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  <c:set var="req" value="${pageContext.request}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="resources/style_ars.css" type="text/css">
<title>Update Aircon</title>
</head>
<body>


<center><div class="form-style-2">
<div class="form-style-2-heading">Update Aircon</div>
<form action="${req.contextPath }/api/aircon_schedule/updateAircon" method="post">
<input type="hidden" name="id" value="${model.id}">

<label for="field1"><span>Aircon Name<span class="required">*</span></span><input type="text" class="input-field" name="name" value="${model.name}"></label>
<label for="field1"><span>Aircon Description<span class="required">*</span></span><input type="text" class="input-field" name="description" value="${model.description}"></label>
<label><span>&nbsp;</span><input type="submit" value="Update Aircon"></label>
</form>
<a href="${req.contextPath }/AirconSchedule">&laquo; back</a>

</body>
</html>