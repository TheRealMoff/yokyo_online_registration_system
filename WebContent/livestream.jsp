<!-- <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %> -->

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
    
    <link rel="stylesheet" href="watch.css">
    <title>Watch Livestream</title>
</head>

<body>

<div class="container">

			<div class="card">

            <div class="image">
                <media>
                    <iframe src="https://player.twitch.tv/?channel=wardell&parent=http://localhost:8081/" frameborder="0" allowfullscreen="true" scrolling="no" height="378" width="620"></iframe>
                </media>
            </div>
            <div class="title">
                <h1>Title: </h1>
            </div>
            <div class="des">
                <p>Description: </p>
                
                <span class="btn"> 
                  <a href="ViewBroadcastControllerServlet">BACK</a>
				</span>
            </div>
        </div>
        
    </div>

</body>

</html>