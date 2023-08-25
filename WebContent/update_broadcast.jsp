<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Update Broadcast</title>

    <!-- CSS -->
    <link rel="stylesheet" href="css/registration.css" />

    <!-- Boxicons CSS -->
    <link
      href="https://unpkg.com/boxicons@2.1.2/css/boxicons.min.css"
      rel="stylesheet"
    />
  </head>
  <body>
    <div class="container">
      <header>Update Broadcast</header>
      <form action="BroadcastControllerServlet" method="get">
      
      <input type="hidden" name="command" value="UPDATE"/>
      <input type="hidden" name="broadcastId" value="${THE_BROADCAST.id}"/>

        <div class="field username-field">
            <div class="input-field">
              <input type="text" placeholder="Enter Title" class="title" name="title" value="${THE_BROADCAST.title}" required/>
            </div>
          </div>

        <div class="field email-field">
          <div class="input-field">
            <input type="text" placeholder="Enter description" class="description" name="description" value="${THE_BROADCAST.description}" required/>
          </div>
        </div>
        <div class="field email-field">
          <div class="input-field">
            <input type="text" placeholder="Enter Sport" class="sport" name="sport" value="${THE_BROADCAST.sport}" required/>
          </div>
        </div>
        <div class="field email-field">
          <div class="input-field">
            <input type="text" placeholder="Video" class="video" name="video" value="${THE_BROADCAST.video}" required/>
          </div>
        </div>
       
       <div class="field sports-field">
            <div class="input-field">
              <input type="date" placeholder="Date" class="upload_date" name="upload_date" value="${THE_BROADCAST.upload_date}" required/>
            </div>
        </div>
        
        <h1><a href="BroadcastControllerServlet">BACK</a></h1>

        <div class="input-field button">
          <input type="submit" value="Submit Now" OnClick="window.location.href='admindash.jsp'; return: false;"/> 
        </div>
      </form>
    </div>

  </body>
</html>