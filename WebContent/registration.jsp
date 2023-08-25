<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Registration</title>

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
      <header>Signup</header>
      <form action="RegistrationControllerServlet" method="post">
      
      <input type="hidden" name="command" value="ADD"/>

        <div class="field username-field">
            <div class="input-field">
              <input type="text" placeholder="Enter your username" class="username" name="username" required/>
            </div>
          </div>

        <div class="field email-field">
          <div class="input-field">
            <input type="email" placeholder="Enter your email" class="email" name="email" required/>
          </div>
        </div>
        <div class="field create-password">
          <div class="input-field">
            <input
              type="password"
              placeholder="Create password"
              class="password"
              name="password"
              required
            />
            <i class="bx bx-hide show-hide"></i>
          </div>
        </div>
        <div class="field confirm-password">
          <div class="input-field">
            <input
              type="password"
              placeholder="Confirm password"
              class="cPassword"
              required
            />
            <i class="bx bx-hide show-hide"></i>
          </div>
        </div>
       
       <div class="field sports-field">
            <div class="input-field">
              <input type="text" placeholder="Enter your favourite Sport" class="favourite_sport" name="favourite_sport"/>
            </div>
        </div>
        
        <div class="terms">
            <input type="checkbox" id="checkbox">
            <label for="checkbox"> I agree to the<a href="#"> Terms & Condition</a></label>
        </div>

        <div class="g-recaptcha" data-sitekey="6Ld_9gQmAAAAAL_UzAoydjjfi6CIxCAsum114EhR">

        </div>

        <h1>Already have an account?<a href="login.jsp">Log In</a></h1>

        <div class="input-field button">
          <input type="submit" value="Submit Now" OnClick="window.location.href='login.jsp'; return: false;" />
        </div>
      </form>
    </div>

    <!-- JavaScript -->
   <!-- <script src="javascript/registration.js"></script> -->
   <script src="https://www.google.com/recaptcha/api.js?"
        async defer>
    </script>
  </body>
</html>