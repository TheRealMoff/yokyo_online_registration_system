<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Add Broadcast</title>

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
      <header>Add Broadcast</header>
      <form action="BroadcastControllerServlet" method="post">
      
      <input type="hidden" name="command" value="ADD"/>

        <div class="field username-field">
            <div class="input-field">
              <input type="text" placeholder="Enter Title" class="title" name="title" required/>
            </div>
          </div>

        <div class="field email-field">
          <div class="input-field">
            <input type="text" placeholder="Enter description" class="description" name="description" required/>
          </div>
        </div>
        <div class="field email-field">
          <div class="input-field">
            <input type="text" placeholder="Enter Sport" class="sport" name="sport" required/>
          </div>
        </div>
        <div class="field email-field">
          <div class="input-field">
            <input type="text" placeholder="Video" class="video" name="video" required/>
          </div>
        </div>
       
       <div class="field sports-field">
            <div class="input-field">
              <input type="date" placeholder="Date" class="upload_date" name="upload_date"/>
            </div>
        </div>
        
        <!-- <h1>Already have an account?<a href="login.jsp">Log In</a></h1> -->

        <div class="input-field button">
          <input type="submit" value="Submit Now" OnClick="window.location.href='admindash.jsp'; return: false;"/> 
        </div>
      </form>
    </div>

  </body>
</html>