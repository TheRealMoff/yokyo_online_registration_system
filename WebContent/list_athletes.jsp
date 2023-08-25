<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
    
    <link rel="stylesheet" href="listTables.css">
    
    <title>List Athletes</title>
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
            <li><a href="ScheduleControllerServlet">Schedule</a></li>
            <li ><a href="ResultsControllerServlet">Results</a></li>
            <li class="active"><a href="selectAccount.jsp">Accounts</a></li>
            <li><a href="LogOutControllerServlet">Logout</a></li>
        </ul>
    </div>

    <div class="content">
    
    	<form action="AthleteControllerServlet" method="get">
    	
    	<input type="hidden" name="command" value="SEARCH" />
    	
        <div class="search-bar">
        
            <input type="text" name="theSearchName" placeholder="Search">
            <button type="button"><i class="fas fa-search"></i></button>
            
        </div>
        </form>
        
        <span class="btn"> 
		<a href="add_athlete.jsp">Add Athlete</a> 
		</span>

		<table class="styled-table">
            <thead>
                <tr>
						<th> Id </th>
                        <th> Forename</th>
                        <th> Surname </th>
                        <th> Country </th>
                        <th> Sport </th>
                        <th> Username </th>
                        <th> Password </th>
                        <th> Action </th>

					</tr>
            </thead>

            <c:forEach var="tempAthlete" items="${ATHLETE_LIST}">
                    
                    <!-- set up for each athlete -->
                    
                    <c:url var="tempLink" value="AthleteControllerServlet">
                    
                    	<c:param name="command" value="LOAD"/>
                    	<c:param name="athleteId" value="${tempAthlete.id}"/>
                    	
                    </c:url>
                    
                    <!-- set up a link to delete athlete -->
					
					<c:url var="DeleteLink" value="AthleteControllerServlet">
					
					<c:param name="command" value="DELETE"/>
					<c:param name="athleteId" value="${tempAthlete.id}"/>
						
					</c:url>

            <tbody>
                <tr>
                        <td>${tempAthlete.id}</td>
						<td>${tempAthlete.forename}</td>
						<td>${tempAthlete.surname}</td>
						<td>${tempAthlete.country}</td>
						<td>${tempAthlete.sport}</td>
						<td>${tempAthlete.username}</td>
						<td>${tempAthlete.password}</td>
                        <td>
                        
                        <span class="btn"> 
                        	<a href="${tempLink}">Edit</a>
							<a href="${DeleteLink}" onClick="if (!(confirm('Are you sure you want to delete this athlete?'))) return false">Delete</a>
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