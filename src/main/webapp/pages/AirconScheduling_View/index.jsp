<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  <c:set var="req" value="${pageContext.request}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="${req.contextPath }/AirconSchedule/resources/style_ars.css" type="text/css">
<title>Aircon Reservation System</title></head>
<body>
<div class="header">
	<div class="searchform">
          <form id="formsearch" name="formsearch" method="get" action="${req.contextPath }/AirconSchedule">
           <select onchange="goToURL()" name="searchType" style="height: 29px;">
            <option <c:if test="${searchType.equals('Aircon Name')}">selected</c:if>>Aircon Name</option>
            <option <c:if test="${searchType.equals('Date')}">selected</c:if> >Date</option>
            <option <c:if test="${searchType.equals('Time')}">selected</c:if>>Time</option>
           </select>
          <script>
           function goToURL(){
        	   document.formsearch.submit();
           }
          </script>
            <span>
            <c:if test="${searchType.equals('Aircon Name')}">
            	<input name="airconName" class="editbox_search" id="editbox_search" maxlength="100"  placeholder="Input Name"type="text" />
            </c:if>
            <c:if test="${searchType.equals('Date')}">
            	<input name="date"  type="date" class="time_search"/>
            </c:if>
            <c:if test="${searchType.equals('Time')}">
            	<input name="time_start" placeholder="HH:MM AM/PM"type="time" class="time_search"/>
            	<input name="time_end"   placeholder="HH:MM AM/PM"type="time" class="time_search"/>
            </c:if>
            
            </span>
            <input src="${req.contextPath }/AirconSchedule/resources/search.gif" class="button_search" type="image" />
           
          </form>
    </div>
</div>
${error}
<br><br><br>
 <center>
 
	<table  border="1" width="600px">
		<thead>
		<tr style="background-color: #02ACEE; color: #2D3134; text-align: center;
	     font-size: 12px; font-weight: bold; font-family: Calibri;">
			<td>Aircon Name</td>
			<td>Aircon Description</td>
			<td>Edit | Delete</td>
		</tr>
		</thead>
		<c:forEach items="${airconList}" var="aircon" >
		<c:if test="${not aircon.deleted_flag}">
		<tr style="background-color: white; color: black; text-align: center;" height="30px">
			<td>${aircon.name }</td>
			<td>${aircon.description }</td>
			<td>
			<a href="${req.contextPath }/AirconSchedule/editAirConditioner?id=${aircon.id}" style="text-decoration:none; color:red; font-weight:bold;"><img src="${req.contextPath }/AirconSchedule/resources/edit-icon.png" height="20"></a>&nbsp;
			<a href="${req.contextPath }/api/aircon_schedule/deleteAircon?id=${aircon.id}" style="text-decoration:none; color:red; font-weight:bold;"><img src="${req.contextPath }/AirconSchedule/resources/trash-icon.png" height="20"></a>
		</tr>
		</c:if>
		</c:forEach>
		<tr style="background-color: white; color: black; text-align: center;" height="30px">
		<form action = "${req.contextPath }/api/aircon_schedule/addNewAircon" method="POST">
		<td style="font-weight: bold;">Aircon Name:<input type="text" name="name" required></td>
		<td style="font-weight: bold;">Aircon Description:<input type="text" name="description" required></td>
		<td><input type="submit" class="add_aircon_btn" value="Add"></td>
		</form>
		</tr>
	</table>
	<br><br><br>
	<table border="1" width="600px">
 	<thead>
	    <tr
	     style="background-color: #02ACEE; color: #2D3134; text-align: center;
	     font-size: 12px; font-weight: bold; font-family: Calibri;">
	     
	     <h2 style="font-family: Arial; font-size: 20px; color: #2d3134;">Aircon Schedules</h2>
	     
	     <td>Aircon Name</td>
	     <td>Date (yyy/mmm/ddd)</td>
	     <td>Time Start</td>
	     <td>Time End</td>
	     <td>Edit | Delete</td>
	    </tr>
    </thead>    
    <tbody>
    
    <c:forEach items="${scheduleList}" var="schedule" >
    	<c:if test="${not schedule.delete_flag}">
		<tr style="background-color: white; color: black; text-align: center;" height="30px">
			<td>${schedule.aircon_name}</td>
			<td><fmt:formatDate pattern="yyyy/MM/dd" value="${schedule.date}" /></td>
			<td><fmt:formatDate pattern="hh:mm aa" value="${schedule.time_start }" /></td>
			<td><fmt:formatDate pattern="hh:mm aa" value="${schedule.time_end }" /></td>
			<td><a href="${req.contextPath }/AirconSchedule/edit?id=${schedule.id}" style="text-decoration:none; color:red; font-weight:bold;"><img src="${req.contextPath }/AirconSchedule/resources/edit-icon.png" height="20"></a>&nbsp;
			<a href="api/aircon_schedule/deleteSchedule?id=${schedule.id}" style="text-decoration:none; color:red; font-weight:bold;"><img src="${req.contextPath }/AirconSchedule/resources/trash-icon.png" height="20"></a>
			</td>
		</tr>
		</c:if>
	</c:forEach>
	</tbody>
	</table>
</center>
<br>

  <div class="page-control">
  <p>
  	<a href="ars-module4?page=" style="text-decoration:none;"> &laquo; prev </a> 
  
  	<a href="\ars-module4?page=" class="next" style="text-decoration:none;"> next &raquo; </a>
  	</p>
  	
  </div>
  <center><a href="${req.contextPath }/AirconSchedule/addNewSchedule" href="form" class="add_sched_btn"> Add New Schedule</a></center>
  
</body>
</html>