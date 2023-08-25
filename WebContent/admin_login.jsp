<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title> Admin Login</title>

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
      <header>Login</header>
      <form action="AdminLoginControllerServlet" method="POST">
      
      <input type="hidden" name="command" value="USERLOG"/>

        <div class="field username-field">
            <div class="input-field">
              <input type="text" placeholder="Enter your username" class="username" name="username" required/>
            </div>
          </div>

        <div class="field create-password">
          <div class="input-field">
            <input
              type="password"
              placeholder="Enter Password"
              class="password"
              name="password"
              required
            />
            <i class="bx bx-hide show-hide"></i>
          </div>
          <span class="error password-error">
            <i class="bx bx-error-circle error-icon"></i>
            <p class="error-text">
              Please enter atleast 8 charatcer with number, symbol, small and
              capital letter.
            </p>
          </span>
        </div>
        
        <div class="forgot_pass">
            <a href="#">Forgot Password?</a>
        </div>

        <h1>Back to home?<a href="index.jsp">Click here</a></h1>

        <div class="input-field button">
          <input type="submit" value="Submit Now" />
        </div>
      </form>
    </div>

    <!-- JavaScript -->
   <!-- <script src="javascript/registration.js"></script> -->
  </body>
</html>