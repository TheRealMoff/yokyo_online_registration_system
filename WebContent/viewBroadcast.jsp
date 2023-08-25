<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
    <link rel="stylesheet" href="userdash.css">
    <link rel="stylesheet" href="viewBroadcast.css">
    <title>View Broadcast</title>
</head>

<body>

    <div class="header">
        <h1>YOKYO FUN OLYMPICS</h1>
        <p>LET'S GET SPORTY</p>
    </div>

    <div class="topnav">
        <ul>
            <li><a href="userdash.jsp">Home</a></li>
            <li class="active"><a href="ViewBroadcastControllerServlet">Broadcasts</a></li>
            <li><a href="livestream.jsp">Livestream</a></li>
            <li><a href="UserViewScheduleControllerServlet">Schedule</a></li>
            <li><a href="UserViewResultsControllerServlet">Results</a></li>
            <li><a href="FAQ.jsp">FAQ</a></li>
            <li><a href="LogOutControllerServlet">Logout</a></li>
        </ul>
    </div>

    <form action="ViewBroadcastControllerServlet" method="GET">
    	<div class="search-bar1">
    	
    		<input type="hidden" name="command" value="SEARCH" />
    		
        	<input type="text"  name="theSearchName" placeholder="Search here...">
        	
        	<button type="button"><i class="fas fa-search"></i></button>
        		</div>
        </form>

    <div class="container">
    
     <c:forEach var="tempBroadcast" items="${BROADCAST_LIST}">

			<!-- set up for each broadcast -->

			<c:url var="tempLink" value="ViewBroadcastControllerServlet">

				<c:param name="command" value="WATCH" />
				<c:param name="broadcastId" value="${tempBroadcast.id}" />

			</c:url>

			<!-- set up a link to delete broadcast -->

			<c:url var="DeleteLink" value="ViewBroadcastControllerServlet">

				<c:param name="command" value="DELETE" />
				<c:param name="broadcastId" value="${tempBroadcast.id}" />

			</c:url>

			<div class="card">

            <div class="image">
                <media>
                    <iframe width="100%" height="100%" src="https://www.youtube.com/embed/${tempBroadcast.video}" frameborder="0" allowfullscreen></iframe>
                </media>
            </div>
            <div class="title">
                <h1>${tempBroadcast.title}</h1>
            </div>
            <div class="des">
                <p>${tempBroadcast.description}</p>
                
                <span class="btn"> 
                  <a href="${tempLink}">PLAY</a>
				</span>
            </div>
        </div>
        </c:forEach>
    </div>

    <div class="footer">
        <p>Developed by Tumelo Thuto Mswela</p>
    </div>
</body>

</html>