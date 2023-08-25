<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="index.css"/>
    
    <script
    src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
    integrity="sha256-pasqAKBDmFT4eHoN2ndd6lN370kFiGUFyTiUHWhU7k8="
    crossorigin="anonymous"></script>
    
    <script>
    $(function() {
    $(".toggle").on("click", function() {
        if ($(".item").hasClass("active")) {
            $(".item").removeClass("active");
        } else {
            $(".item").addClass("active");
        }
    });
});
    </script>

    <title>Yokyo Fun Olympic Games</title>
</head>
<body>
    <nav>
        <ul class="menu">
            <li class="logo"><a href="#">YOKYO FUN OLYMPIC GAMES</a></li>
            <li class="item"><a href="#">Home</a></li>
            <li class="item"><a href="#">About</a></li>
            </li>
            <li class="item button"><a href="selectRole.jsp">Log In</a></li>
            <li class="item button secondary"><a href="registration.jsp">Sign Up</a></li>
            <li class="toggle"><span class="bars"></span></li>
        </ul>
    </nav>

    <div class="content">
        <h1>LET'S GET SPORTY</h1>
        <P>Champions are made when no one is watching.
            <br>Championships are won at practice.<br>
            To the Game.
        </P>
        <div>
            <button type="button"><span></span>READ MORE</button>
        </div>
    </div>

</body>
</html>