<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Admin add athlete</title>

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
      <header>Add Athlete</header>
      <form action="AthleteControllerServlet" method="post">
      
      <input type="hidden" name="command" value="ADD"/>

        <div class="field username-field">
            <div class="input-field">
              <input type="text" placeholder="Enter name" class="name" name="name" required/>
            </div>
          </div>

        <div class="field email-field">
          <div class="input-field">
            <input type="text" placeholder="Enter surname" class="surname" name="surname" required/>
          </div>
        </div>
        <div class="field email-field">
          <div class="input-field">
            <input type="text" placeholder="Enter country" class="country" name="country" required/>
          </div>
        </div>
        <div class="field email-field">
          <div class="input-field">
            <input type="text" placeholder="Enter sport" class="sport" name="sport" required/>
          </div>
        </div>
       
       <div class="field sports-field">
            <div class="input-field">
              <input type="text" placeholder="Enter username" class="username" name="username"/>
            </div>
        </div>
        
        <div class="field email-field">
          <div class="input-field">
            <input type="password" placeholder="Enter password" class="password" name="password" required/>
          </div>
        </div>

        <h1><a href="AthleteControllerServlet">BACK</a></h1> 

        <div class="input-field button">
          <input type="submit" value="Submit Now"/> <!-- OnClick="window.location.href='admindash.jsp'; return: false;" --> 
        </div>
      </form>
    </div>

    <!-- JavaScript -->
   <!-- <script src="javascript/registration.js"></script> -->
  </body>
</html>