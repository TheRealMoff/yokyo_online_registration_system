<!-- <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %> -->

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
    
    <link rel="stylesheet" href="watch.css">
    <title>Watch Broadcast</title>
</head>

<body>

<div class="container">
    
    <form action="ViewBroadcastControllerServlet" method="GET">
    
    	<input type="hidden" name="command" value="LOAD"/>
      <input type="hidden" name="broadcastId" value="${THE_BROADCAST.id}"/>
        </form>

			<div class="card">

            <div class="image">
                <media>
                    <iframe width="100%" height="720" src="https://www.youtube.com/embed/${tempBroadcast.video}" frameborder="0" ></iframe>
                </media>
            </div>
            <div class="title">
                <h1>${tempBroadcast.title}</h1>
            </div>
            <div class="des">
                <p>${tempBroadcast.description}</p>
                
                <span class="btn"> 
                  <a href="ViewBroadcastControllerServlet">BACK</a>
				</span>
            </div>
        </div>
        
    </div>

</body>

</html>