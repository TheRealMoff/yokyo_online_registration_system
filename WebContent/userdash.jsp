<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
    <link rel="stylesheet" href="userdash.css">
    <link rel="stylesheet" href="usersearch.css">
    
    <style type="text/css">
    
    
    </style>
    
    
    <title>Home</title>
</head>
<body>

    <div class="header">
        <h1>YOKYO FUN OLYMPICS</h1>
        <p>LET'S GET SPORTY</p>
    </div>

    <div class="topnav">
        <ul>
            <li class="active"><a href="userdash.jsp">Home</a></li>
            <li><a href="ViewBroadcastControllerServlet">Broadcasts</a></li>
            <li><a href="livestream.jsp">Livestream</a></li>
            <li><a href="UserViewScheduleControllerServlet">Schedule</a></li>
            <li><a href="UserViewResultsControllerServlet">Results</a></li>
            <li><a href="FAQ.jsp">FAQ</a></li>
            <li><a href="LogOutControllerServlet">Logout</a></li>
        </ul>
    </div>

   <div class="container">
        <div class="search-box">
            <i class="bx bx-search"></i>
            <input type="text" placeholder="Search a sport">
        </div>

        <div class="images">
            <div class="image-box" data-name="football">
                <img src="https://media.gq-magazine.co.uk/photos/5d13a4d5003d756ecdae71db/master/pass/lionel-messi-gq-14feb18_getty_b.jpg" alt="">
                <h6>Football</h6>
            </div>
            <div class="image-box" data-name="swimming">
                <img src="https://bandcaquatics.com/wp-content/uploads/2017/11/iStock-487264426.jpg" alt="">
                <h6>Swimming</h6>
            </div>
            <div class="image-box" data-name="athletics">
                <img src="https://www.kreedon.com/wp-content/uploads/2019/06/athletics-Kreedon.jpg" alt="">
                <h6>Athletics</h6>
            </div>
            <div class="image-box" data-name="archery">
                <img src="https://img.olympicchannel.com/images/image/private/t_16-9_640/f_auto/v1538355600/primary/o2dn1lo38v9i5o5xnvbd" alt="">
                <h6>Archery</h6>
            </div>
            <div class="image-box" data-name="basketball">
                <img src="https://cloudfront-us-east-1.images.arcpublishing.com/advancelocal/2SALOJIEOJBB7JNUNCL2HPITUM.JPG" alt="">
                <h6>Basketball</h6>
            </div>
            <div class="image-box" data-name="highjump">
                <img src="https://media.newyorker.com/photos/59097967019dfc3494ea32d1/master/w_2560%2Cc_limit/Thomas-ClearingTheBar-ThePhilosophyOfTheHighJump.jpg" alt="">
                <h6>High Jump</h6>
            </div>
            <div class="image-box" data-name="fencing">
                <img src="https://s.yimg.com/ny/api/res/1.2/vNURVSOK6mlZoFk5w0j.6g--/YXBwaWQ9aGlnaGxhbmRlcjt3PTEyMDA7aD04MDA-/https://s.yimg.com/os/creatr-uploaded-images/2021-07/6308ab80-eddd-11eb-bf75-119aa62e1cf3" alt="">
                <h6>Fencing</h6>
            </div>
            <div class="image-box" data-name="shooting">
                <img src="https://img.olympicchannel.com/images/image/private/t_social_share_thumb/f_auto/primary/fhwzbspsmanvhrbqqsoa" alt="">
                <h6>Shooting</h6>
            </div>
            <div class="image-box" data-name="volleyball">
                <img src="https://imageio.forbes.com/specials-images/imageserve/61eab04990c7a11ddc7ac51b/0x0.jpg?format=jpg&width=1200" alt="">
                <h6>Volleyball</h6>
            </div>
            <div class="image-box" data-name="golf">
                <img src="https://www.nzherald.co.nz/resizer/SSqz243H7IPAGBzd8YbbYZ1aG00=/576x324/smart/filters:quality(70)/cloudfront-ap-southeast-2.images.arcpublishing.com/nzme/PHSATEZJFJB6ZPC3NLAND3AVU4.JPG" alt="">
                <h6>Golf</h6>
            </div>
        </div>
    </div>

    <div class="footer">
        <p>Developed by Tumelo Thuto Mswela</p>
    </div>
    
    <script src="javascript/search.js"></script>
</body>
</html>
