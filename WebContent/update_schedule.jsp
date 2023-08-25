<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Update Schedule</title>

    <!-- CSS -->
    <link rel="stylesheet" href="css/registration.css" />

    <!-- Boxicons CSS -->
    <link href="https://unpkg.com/boxicons@2.1.2/css/boxicons.min.css" rel="stylesheet"/>
    
  </head>
  <body>
    <div class="container">
      <header>Update Schedule</header>
      <form action="ScheduleControllerServlet" method="get">
      
      <input type="hidden" name="command" value="UPDATE"/>
      <input type="hidden" name="scheduleId" value="${THE_SCHEDULE.id}"/>

        <div class="field">
            <div class="input-field">
              <input type="text" placeholder="Enter sport" name="sport" value="${THE_SCHEDULE.sport}" required/>
            </div>
          </div>

        <div class="field">
          <div class="input-field">
            <input type="text" placeholder="Enter venue" name="venue" value="${THE_SCHEDULE.venue}" required/>
          </div>
        </div>
        <div class="field">
          <div class="input-field">
            <input type="text" placeholder="Enter time" name="time" value="${THE_SCHEDULE.time}" required/>
          </div>
        </div>
        
        <div class="field">
          <div class="input-field">
            <input type="text" placeholder="Enter teams" name="teams_fixtured" value="${THE_SCHEDULE.teams_fixtured}" required/>
          </div>
        </div>
        
        <div class="field">
          <div class="input-field">
            <input type="date" placeholder="Enter date" name="date" value="${THE_SCHEDULE.date}" required/>
          </div>
        </div>

         <h1><a href="ScheduleControllerServlet">BACK</a></h1>

        <div class="input-field button">
          <input type="submit" value="Submit Now"/>
        </div>
      </form>
    </div>

    <!-- JavaScript -->
   <!-- <script src="javascript/registration.js"></script> -->
  </body>
</html>