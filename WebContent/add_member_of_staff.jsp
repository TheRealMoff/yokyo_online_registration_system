<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Admin add staff</title>

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
      <header>Add Staff</header>
      <form action="StaffControllerServlet" method="post">
      
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
            <input type="text" placeholder="Enter username" class="username" name="username" required/>
          </div>
        </div>
        
        <div class="field email-field">
          <div class="input-field">
            <input type="password" placeholder="Enter password" class="password" name="password" required/>
          </div>
        </div>

         <h1><a href="StaffControllerServlet">BACK</a></h1>

        <div class="input-field button">
          <input type="submit" value="Submit Now"/>
        </div>
      </form>
    </div>

    <!-- JavaScript -->
   <!-- <script src="javascript/registration.js"></script> -->
  </body>
</html>