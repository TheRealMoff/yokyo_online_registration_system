<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">

    <style>
        @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap');

        *{
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Poppins', sans-serif;
        }

        body{
            min-height: 1000px;
        }

        section{
            position: relative;
            width: 100%;
            height: 120vh;
            overflow: hidden;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 50px;
        }

        section::before{
            content: '';
            position: absolute;
            bottom: 0;
            left: 50%;
            width: 100vh;
            height: 100vh;
            background: #009688;
            border-radius: 50%;
            transform-origin: bottom;
            transform: translateX(-50%) scale(4);
        }

        section .content{
            position: relative;
            z-index: 1;
            max-width: 1000px;
            text-align: center;
        }

        section .content h2{
            font-size: 3em;
            color: #fff;
        }

        section .content p{
            font-size: 1.2em;
            color: #fff;
        }

        .card{
            display: inline-block;
            background-color: rgb(176, 175, 173);
            margin-left: 250px;
            margin-top: -200px;
        }
        
    </style>

    <title>Who are you?</title>
</head>
<body>
    <section>
        <div class="content">
            <h2>WHO ARE YOU?</h2>
            <P>Get Sportier!</P>
        </div>
    </section>
    
    <div class="card" style="width: 18rem;">
        <a href="admin_login.jsp" class="btn btn-fix text-left">
        <img class="card-img-top" src="admin.jpg" alt="Card image cap">
        </a>
        <div class="card-body">
          <p class="card-text">Admin</p>
        </div>
        
      </div>

      <div class="card" style="width: 18rem;">
        <a href="login.jsp" class="btn btn-fix text-left">
        <img class="card-img-top" src="images/user.png" alt="Card image cap">
        </a>
        <div class="card-body">
          <p class="card-text">User</p>
        </div>
      </div>

</body>
</html>