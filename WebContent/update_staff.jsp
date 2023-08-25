<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Admin update staff</title>

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
      <header>Update Staff</header>
      <form action="StaffControllerServlet" method=get>
      
      <input type="hidden" name="command" value="UPDATE"/>
      <input type="hidden" name="staffId" value="${THE_STAFF.id}"/>

        <div class="field username-field">
            <div class="input-field">
              <input type="text" placeholder="Enter name" class="name" name="name" value="${THE_STAFF.staff_forename}" required/>
            </div>
          </div>

        <div class="field email-field">
          <div class="input-field">
            <input type="text" placeholder="Enter surname" class="surname" name="surname" value="${THE_STAFF.staff_surname}" required/>
          </div>
        </div>
        <div class="field email-field">
          <div class="input-field">
            <input type="text" placeholder="Enter username" class="username" name="username" value="${THE_STAFF.username}" required/>
          </div>
        </div>
        
        <div class="field email-field">
          <div class="input-field">
            <input type="password" placeholder="Enter password" class="password" name="password" value="${THE_STAFF.password}" required/>
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