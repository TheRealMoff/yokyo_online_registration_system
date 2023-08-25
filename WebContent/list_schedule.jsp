<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
    
    <link rel="stylesheet" href="listTables.css">
    
    <title>List Schedule</title>
</head>

<body>

    <div class="header">
        <h1>YOKYO FUN OLYMPICS</h1>
        <p>ADMIN</p>
    </div>

    <div class="topnav">
        <ul>
            <li><a href="AdminDashControllerServlet">Dashboard</a></li>
            <li><a href="BroadcastControllerServlet">Broadcasts</a></li>
            <li class="active"><a href="ScheduleControllerServlet">Schedule</a></li>
            <li><a href="ResultsControllerServlet">Results</a></li>
            <li><a href="selectAccount.jsp">Accounts</a></li>
            <li><a href="LogOutControllerServlet">Logout</a></li>
        </ul>
    </div>

    <div class="content">
    
    	<form action="ScheduleControllerServlet" method="get">
    	
    	<input type="hidden" name="command" value="SEARCH" />
    	
        <div class="search-bar">
        
            <input type="text" name="theSearchName" placeholder="Search">
            <button type="button"><i class="fas fa-search"></i></button>
            
        </div>
        </form>

		<span class="btn"> 
		<a href="add_schedule.jsp">Add Schedule</a> 
		</span>

		<table class="styled-table">
            <thead>
                <tr>
                	<th>Id</th>
                    <th>Sport</th>
                    <th>Venue</th>
                    <th>Time</th>
                    <th>Teams Fixtured</th>
                    <th>Date</th>
                    <th>Action</th>
                </tr>
            </thead>

            <c:forEach var="tempSchedule" items="${SCHEDULE_LIST}">
                    
                    <!-- set up for each scheddule -->
                    
                    <c:url var="tempLink" value="ScheduleControllerServlet">
                    
                    	<c:param name="command" value="LOAD"/>
                    	<c:param name="scheduleId" value="${tempSchedule.id}"/>
                    	
                    </c:url>
                    
                    <!-- set up a link to delete schedule -->
					
					<c:url var="DeleteLink" value="ScheduleControllerServlet">
					
					<c:param name="command" value="DELETE"/>
					<c:param name="scheduleId" value="${tempSchedule.id}"/>
						
					</c:url>

            <tbody>
                <tr>
                        <td>${tempSchedule.id}</td>
                        <td>${tempSchedule.sport}</td>
                        <td>${tempSchedule.venue}</td>
                        <td>${tempSchedule.time}</td>
                        <td>${tempSchedule.teams_fixtured}</td>
                        <td>${tempSchedule.date}</td>
                        <td>
                        
                        <span class="btn"> 
                        	<a href="${tempLink}">Edit</a>
							<a href="${DeleteLink}" onClick="if (!(confirm('Are you sure you want to delete this schedule ?'))) return false">Delete</a>
						</span>

						</td>
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