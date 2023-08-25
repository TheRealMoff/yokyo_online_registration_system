<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Admin update athlete</title>

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
      <header>Update Athlete</header>
      <form action="AthleteControllerServlet" method=get>
      
      <input type="hidden" name="command" value="UPDATE"/>
      <input type="hidden" name="athleteId" value="${THE_ATHLETE.id}"/>

        <div class="field username-field">
            <div class="input-field">
              <input type="text" placeholder="Enter name" name="name" value="${THE_ATHLETE.forename}" required/>
            </div>
          </div>

        <div class="field email-field">
          <div class="input-field">
            <input type="text" placeholder="Enter surname"  name="surname" value="${THE_ATHLETE.surname}" required/>
          </div>
        </div>
        <div class="field email-field">
          <div class="input-field">
            <input type="text" placeholder="Enter country"  name="country" value="${THE_ATHLETE.country}" required/>
          </div>
        </div>
        <div class="field email-field">
          <div class="input-field">
            <input type="text" placeholder="Enter sport"  name="sport" value="${THE_ATHLETE.sport}" required/>
          </div>
        </div>
       
       <div class="field sports-field">
            <div class="input-field">
              <input type="text" placeholder="Enter username" name="username" value="${THE_ATHLETE.username}" required/>
            </div>
        </div>
        
        <div class="field email-field">
          <div class="input-field">
            <input type="password" placeholder="Enter password" name="password" value="${THE_ATHLETE.password}" required/>
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