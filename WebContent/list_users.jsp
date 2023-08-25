<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
    
    <link rel="stylesheet" href="listTables.css">
    
    <title>List Users</title>
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
    
    	<form action="RegistrationControllerServlet" method=get>
    	
    	<input type="hidden" name="command" value="SEARCH" />
    	
        <div class="search-bar">
        
            <input type="text" name="theSearchName" placeholder="Search">
            <button type="button"><i class="fas fa-search"></i></button>
            
        </div>
        </form>
        
		<table class="styled-table">
            <thead>
                <tr>
						<th>Id</th>
						<th>Email</th>
						<th>Username</th>
						<th>Password</th>
						<th>Favourite Sport</th>
						
					</tr>
            </thead>

            <c:forEach var="tempRegister" items="${REGISTER_LIST}">
                    
                    <!-- set up for each user -->
                    
                    <c:url var="tempLink" value="RegistrationControllerServlet">
                    
                    	<c:param name="command" value="LOAD"/>
                    	<c:param name="registerId" value="${tempRegister.id}"/>
                    	
                    </c:url>
                    
                    <!-- set up a link to delete user -->
					
					<c:url var="DeleteLink" value="RegistrationControllerServlet">
					
					<c:param name="command" value="DELETE"/>
					<c:param name="registerId" value="${tempRegister.id}"/>
						
					</c:url>

            <tbody>
                <tr>
                        <td>${tempRegister.id}</td>
                        <td>${tempRegister.email}</td>
                        <td>${tempRegister.username}</td>
                        <td>${tempRegister.password}</td>
                        <td>${tempRegister.favourite_sport}</td>
                        
                        
                        <!--<span class="btn"> 
                        	<a href="${tempLink}">Edit</a>
							<a href="${DeleteLink}" onClick="if (!(confirm('Are you sure you want to delete this user?'))) return false">Delete</a>
						</span>-->

						
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