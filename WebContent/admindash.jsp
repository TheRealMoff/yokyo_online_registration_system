<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
    
    <link rel="stylesheet" href="admin.css">
    
    <title>Admin Home</title>
</head>

<body>

    <div class="header">
        <h1>YOKYO FUN OLYMPICS</h1>
        <p>ADMIN</p>
    </div>

    <div class="topnav">
        <ul>
            <li class="active"><a href="">Dashboard</a></li>
            <li><a href="BroadcastControllerServlet">Broadcasts</a></li>
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
        <table class="styled-table">
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Description</th>
                    <th>Sport</th>
                    <th>Video</th>
                    <th>Upload Date</th>
                </tr>
            </thead>

            <c:forEach var="tempBroadcast" items="${BROADCAST_LIST}">
                    
                    <!-- set up for each broacast -->
                    
                    <c:url var="tempLink" value="BroadcastControllerServlet">
                    
                    	<c:param name="command" value="LOAD"/>
                    	<c:param name="broadcastID" value="${tempBroadcast.id}"/>
                    	
                    </c:url>
                    
                    <!-- set up a link to delete broadcast -->
					
					<c:url var="DeleteLink" value="BroadcastControllerServlet">
					
					<c:param name="command" value="DELETE"/>
					<c:param name="staffId" value="${tempBroaddcast.id}"/>
						
					</c:url>

            <tbody>
                <tr>
                        <td>${tempBroadcast.title}</td>
                        <td>${tempBroadcast.description}</td>
                        <td>${tempBroadcast.sport}</td>
                        
                        <media>	
						<td> <iframe style="width: 100px; height: 100px;" src="https://youtube.com/embed/${tempBroadcast.video}" allowfullscreen></iframe></td>
						</media>
						
                        <td>${tempBroadcast.upload_date}</td>
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