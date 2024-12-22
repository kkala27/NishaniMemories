<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Welcome to Our World</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&family=Pacifico&display=swap" rel="stylesheet">
    <style>
        /* Global Reset and Basic Styles */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Roboto', sans-serif;
            background: #85C1E9;
            color: #333;
            line-height: 1.6;
            text-align: center;
        }

        h1 {
            font-family: 'Pacifico', cursive;
            color: #fff;
            font-size: 50px;
            font-weight: 400;
            margin-top: 50px;
        }

        h2 {
            font-family: 'Roboto', sans-serif;
            font-size: 28px;
            color: #fff;
            margin-top: 20px;
            font-weight: 400;
        }

        h3 {
            font-size: 22px;
            color: #fff;
            margin-top: 30px;
        }

        /* Clock and Timer Styling */
        #clockdiv {
            font-family: 'Roboto', sans-serif;
            color: #fff;
            display: flex;
            justify-content: center;
            align-items: center;
            margin-top: 50px;
            gap: 30px;
        }

        #clockdiv div {
            background: #1F618D;
            padding: 20px;
            border-radius: 8px;
            display: inline-block;
            text-align: center;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            transition: transform 0.3s ease;
        }

        #clockdiv div:hover {
            transform: scale(1.05);
        }

        #clockdiv span {
            font-size: 48px;
            font-weight: bold;
            display: block;
        }

        .smalltext {
            font-size: 16px;
            font-weight: 500;
            text-transform: uppercase;
        }

        /* Arrow and Link Styles */
        .fas {
            color: #FFD700;
            transition: color 0.3s;
        }

        .fas:hover {
            color: #FF5733;
        }

        a {
            text-decoration: none;
            color: #FFD700;
            font-weight: bold;
            font-size: 22px;
        }

        a:hover {
            color: #FF5733;
        }

        /* Button Styling */
        #Gallery a {
            background-color: #FF6347;
            color: #fff;
            padding: 15px 25px;
            border-radius: 30px;
            font-size: 18px;
            text-transform: uppercase;
            font-weight: bold;
            transition: background-color 0.3s ease;
            display: inline-block;
            margin-top: 30px;
        }

        #Gallery a:hover {
            background-color: #FF4500;
        }

        /* Responsive Design */
        @media (max-width: 768px) {
            #clockdiv {
                flex-direction: column;
                gap: 20px;
            }

            #clockdiv div {
                padding: 15px;
            }

            h1 {
                font-size: 40px;
            }

            h2 {
                font-size: 24px;
            }

            h3 {
                font-size: 18px;
            }
        }
    </style>
</head>

<body>
    <h1>Welcome to Our World, Priyanka</h1>
    <h2>Let's have a glance at our life together...</h2>

    <h3>
        As of <span id="date"></span>, we have completed <span id="days"></span> days in our relationship, and trust me, I have cherished each one of them.
    </h3>

    <h2><b>Time Spent Together</b></h2>

    <div id="clockdiv">
        <div>
            <span class="days" id="day"></span>
            <div class="smalltext">Days</div>
        </div>
        <div>
            <span class="hours" id="hour"></span>
            <div class="smalltext">Hours</div>
        </div>
        <div>
            <span class="minutes" id="minute"></span>
            <div class="smalltext">Minutes</div>
        </div>
        <div>
            <span class="seconds" id="second"></span>
            <div class="smalltext">Seconds</div>
        </div>
    </div>

	<div id="Gallery">
	       <a href="/nextSlide/1">Gallery</a>
    </div>
	<h2><a href="javascript:sendBirthdayUpdates();">Send Birthday Reminder </a></h2>

    <script>
        // Sample script for the countdown and date update
        const startDate = new Date("2023-01-01"); // Change this to your relationship start date
        const currentDate = new Date();

        function updateClock() {
            const timeDifference = currentDate - startDate;
            const days = Math.floor(timeDifference / (1000 * 60 * 60 * 24));
            const hours = Math.floor((timeDifference % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
            const minutes = Math.floor((timeDifference % (1000 * 60 * 60)) / (1000 * 60));
            const seconds = Math.floor((timeDifference % (1000 * 60)) / 1000);

            document.getElementById("day").textContent = days;
            document.getElementById("hour").textContent = hours;
            document.getElementById("minute").textContent = minutes;
            document.getElementById("second").textContent = seconds;
            document.getElementById("date").textContent = currentDate.toLocaleDateString();
            document.getElementById("days").textContent = days;
        }

        setInterval(updateClock, 1000);
        updateClock(); // Initialize on page load
    </script>
</body>

</html>
