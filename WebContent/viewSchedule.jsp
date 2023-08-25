<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
    <link rel="stylesheet" href="userdash.css">
    <link rel="stylesheet" href="usertables.css">
    <title>View Schedule</title>
</head>
<body>

    <div class="header">
        <h1>YOKYO FUN OLYMPICS</h1>
        <p>LET'S GET SPORTY</p>
    </div>

    <div class="topnav">
        <ul>
            <li><a href="userdash.jsp">Home</a></li>
            <li><a href="ViewBroadcastControllerServlet">Broadcasts</a></li>
            <li><a href="livestream.jsp">Livestream</a></li>
            <li class="active"><a href="UserViewScheduleControllerServlet">Schedule</a></li>
            <li><a href="UserViewResultsControllerServlet">Results</a></li>
            <li><a href="FAQ.jsp">FAQ</a></li>
            <li><a href="LogOutControllerServlet">Logout</a></li>
        </ul>
    </div>

    <div class="content">
    
    <form action="UserViewScheduleControllerServlet" method="GET">
    	<div class="search-bar">
    	
    		<input type="hidden" name="command" value="SEARCH" />
    		
        	<input type="text"  name="theSearchName" placeholder="Search here...">
        	
        	<button type="button"><i class="fas fa-search"></i></button>
        		</div>
        </form>
    
    <table class="styled-table">
        <thead>
            <tr>
                <th>Sport</th>
                <th>Venue</th>
                <th>Time</th>
                <th>Teams Fixtured</th>
                <th>Date</th>
            </tr>
        </thead>
        
        	<tbody>
            <c:forEach var="tempSchedule" items="${SCHEDULE_LIST}">
					
					<!-- set up a for each schedule -->
					
					<c:url var="tempLink" value="UserViewScheduleControllerServlet">
					
					<c:param name="command" value="LOAD"/>
					<c:param name="scheduleId" value="${tempSchedule.id}"/>
					
					</c:url>
					
					<tr>
						<td>${tempSchedule.sport}</td>
						<td>${tempSchedule.venue}</td>
						<td>${tempSchedule.time}</td>
						<td>${tempSchedule.teams_fixtured}</td>
						<td>${tempSchedule.date}</td>
					</tr>
				</c:forEach>
        </tbody>
    </table>
</div>

    <div class="footer">
        <p>Developed by Tumelo Thuto Mswela</p>
    </div>
</body>
</html>
