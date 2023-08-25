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
    
    <link rel="stylesheet" href="FAQ.css">
    
    
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
            <li ><a href="UserViewScheduleControllerServlet">Schedule</a></li>
            <li><a href="UserViewResultsControllerServlet">Results</a></li>
            <li class="active"><a href="FAQ.jsp">FAQ</a></li>
            <li><a href="LogOutControllerServlet">Logout</a></li>
        </ul>
    </div>

    <div class="footer">
        <p>Developed by Tumelo Thuto Mswela</p>
    </div>
	
</body>
</html>
