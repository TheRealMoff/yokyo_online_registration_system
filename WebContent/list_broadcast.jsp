<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
    
    <link rel="stylesheet" href="listTables.css">
    
    <title>List Broadcast</title>
</head>

<body>

    <div class="header">
        <h1>YOKYO FUN OLYMPICS</h1>
        <p>ADMIN</p>
    </div>

    <div class="topnav">
        <ul>
            <li><a href="AdminDashControllerServlet">Dashboard</a></li>
            <li class="active"><a href="BroadcastControllerServlet">Broadcasts</a></li>
            <li><a href="ScheduleControllerServlet">Schedule</a></li>
            <li><a href="ResultsControllerServlet">Results</a></li>
            <li><a href="selectAccount.jsp">Accounts</a></li>
            <li><a href="LogOutControllerServlet">Logout</a></li>
        </ul>
    </div>

    <div class="content">
    
    	<form action="BroadcastControllerServlet" method="get">
    	
    	<input type="hidden" name="command" value="SEARCH" />
    	
        <div class="search-bar">
        
            <input type="text" name="theSearchName" placeholder="Search">
            <button type="button"><i class="fas fa-search"></i></button>
            
        </div>
        </form>

		<span class="btn"> 
		<a href="add_broadcast.jsp">Add Broadcast</a> 
		</span>

		<table class="styled-table">
            <thead>
                <tr>
                	<th>Id</th>
                    <th>Title</th>
                    <th>Description</th>
                    <th>Sport</th>
                    <th>Video</th>
                    <th>Upload Date</th>
                    <th>Action</th>
                </tr>
            </thead>

            <c:forEach var="tempBroadcast" items="${BROADCAST_LIST}">
                    
                    <!-- set up for each broadcast -->
                    
                    <c:url var="tempLink" value="BroadcastControllerServlet">
                    
                    	<c:param name="command" value="LOAD"/>
                    	<c:param name="broadcastId" value="${tempBroadcast.id}"/>
                    	
                    </c:url>
                    
                    <!-- set up a link to delete broadcast -->
					
					<c:url var="DeleteLink" value="BroadcastControllerServlet">
					
					<c:param name="command" value="DELETE"/>
					<c:param name="broadcastId" value="${tempBroadcast.id}"/>
						
					</c:url>

            <tbody>
                <tr>
                        <td>${tempBroadcast.id}</td>
                        <td>${tempBroadcast.title}</td>
                        <td>${tempBroadcast.description}</td>
                        <td>${tempBroadcast.sport}</td>
                        
                        <media>	
						<td> <iframe style="width: 120px; height: 120px;" src="https://youtube.com/embed/${tempBroadcast.video}" allowfullscreen></iframe></td>
						</media>
						
                        <td>${tempBroadcast.upload_date}</td>
                        <td>
                        
                        <span class="btn"> 
                        	<a href="${tempLink}">Edit</a>
							<a href="${DeleteLink}" onClick="if (!(confirm('Are you sure you want to delete this broadcast?'))) return false">Delete</a>
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