<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  <c:set var="req" value="${pageContext.request}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Page</title>

<style type="text/css">
body{
    margin:0px;
    background:#ebebeb;
}
.header-contMain {
    width:100%;
    position:relative;
    top:0px;
	background:#1e90fe;
}
.headerMain {
    height:100px;
    background:#1e90fe;
    border:0px solid #CCC;
    width:1200px;
    margin:0px auto;
}
.contentMain {
    width:0px;
    background: #fff;
    border: 1px solid #CCC;
    height: 500px;
    margin: 10px auto
	position:relative;
}
.footerMain {
   position:fixed;
   left:0px;
   bottom:0px;
   height:50px;
   width:100%;
   background:#1e90fe;
}
iframe { 
position: fixed; top: 20px; left: 20px;
 }
 
 .form-style-2{
	max-width: 500px;
	padding: 20px 12px 10px 20px;
	font: 13px Arial, Helvetica, sans-serif;
}
.form-style-2-heading{
	font-weight: bold;
	border-bottom: 2px solid #ddd;
	margin-bottom: 20px;
	font-size: 25px;
	padding-bottom: 3px;
}
.form-style-2 label{
	display: block;
	margin: 0px 0px 15px 0px;
}
.form-style-2 label > span{
	width: 100px;
	font-weight: bold;
	float: left;
	padding-top: 8px;
	padding-right: 5px;
}
.form-style-2 span.required{
	color:red;
}
/*.form-style-2 .date-field{
	width: 40px;
	text-align: center;
}*/
.form-style-2 input.input-field{
	width: 48%;
	
}

.form-style-2 input.input-field, 
.form-style-2 .date-field, 
.form-style-2 .textarea-field, 
 .form-style-2 .select-field{
	box-sizing: border-box;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	border: 1px solid #C2C2C2;
	box-shadow: 1px 1px 4px #EBEBEB;
	-moz-box-shadow: 1px 1px 4px #EBEBEB;
	-webkit-box-shadow: 1px 1px 4px #EBEBEB;
	border-radius: 3px;
	-webkit-border-radius: 3px;
	-moz-border-radius: 3px;
	padding: 7px;
	outline: none;
}
.form-style-2 .input-field:focus, 
/*.form-style-2 .date-field:focus,*/
.form-style-2 .textarea-field:focus,  
.form-style-2 .select-field:focus{
	border: 1px solid #0C0;
}
.form-style-2 .textarea-field{
	height:100px;
	width: 55%;
}
.form-style-2 input[type=submit],
.form-style-2 input[type=button]{
	border: none;
	padding: 8px 15px 8px 15px;
	background: #FF8500;
	color: #fff;
	box-shadow: 1px 1px 4px #DADADA;
	-moz-box-shadow: 1px 1px 4px #DADADA;
	-webkit-box-shadow: 1px 1px 4px #DADADA;
	border-radius: 3px;
	-webkit-border-radius: 3px;
	-moz-border-radius: 3px;
}
.form-style-2 input[type=submit]:hover,
.form-style-2 input[type=button]:hover{
	background: #EA7B00;
	color: #fff;
}
</style>


</head>
<body>


<center><div class="form-style-2">
<div class="form-style-2-heading">Edit Aircon Schedule</div>
<form action="${req.contextPath}/api/aircon_schedule/updateSchedule" method="post">
<input type="hidden" name="id" value="${model.id}">
<label for="field1"><span>Aircon Name <span class="required">*</span></span>
	<select name="aircon_id">
		<c:forEach items="${airconList}" var="aircon" >
		<option value="${aircon.id}">${aircon.name}</option>
		</c:forEach>
	</select>
</label>
<label for="field1"><span>Date <span class="required">*</span></span><input type="date" class="input-field" name="date" value="${model.date }"></label>
<label for="field1"><span>Time Start <span class="required">*</span></span><input type="time" class="input-field" name="start" value="${model.time_start }"></label>
<label for="field1"><span>Time End <span class="required">*</span></span><input type="time" class="input-field" name="end" value="${model.time_end }"></label>


<label><span>&nbsp;</span><input type="submit" value="Update Schedule"></label>
</form>
<a href="./"><button  >GO BACK</button></a>





</body>
</html>