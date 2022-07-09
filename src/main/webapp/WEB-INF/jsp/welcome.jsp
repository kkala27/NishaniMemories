<html>
<head>
<title></title>
<style>
body {
	background: #85C1E9;
	font-family: sans-serif;
}

h1 {
	color: #000000;
	font-weight: 100;
	font-size: 40px;
	margin: 40px 0px 20px;
	font-family: "Lucida Handwriting", "Brush Script MT", monospace;
}

h2 {
	font-family: "Lucida Console", "Courier New", Cursive;
}

#clockdiv {
	font-family: sans-serif;
	color: #fff;
	display: inline-block;
	font-weight: 100;
	text-align: center;
	font-size: 30px;
}

#clockdiv>div {
	padding: 10px;
	border-radius: 3px;
	background: #1F618D;
	display: inline-block;
}

#clockdiv div>span {
	padding: 15px;
	border-radius: 3px;
	background: #154360;
	display: inline-block;
}

smalltext {
	padding-top: 5px;
	font-size: 16px;
	font-weight: bold;
}

a:link {
	text-decoration: none;
}

a:visited {
	text-decoration: none;
}

a:hover {
	text-decoration: underline;
}

a:active {
	text-decoration: underline;
}
</style>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<script type="text/javascript" src="/js/script.js"></script>
</head>
<body>
	<h1 font-family:"LucidaConsole", "CourierNew", monospace;>Welcome
		to our world Priyanka</h1>
		<br>
		<h2>Lets have a glance to our life together.............</h2>
		<br>
		<br>
		<h3>
			As of <span id="date"></span> we have completed <span id=days></span>
			days in our relationship and trust me I have cherished each one of
			them.
		</h3>
		<br>
		<br>
		<h2>
			<b><center>Time Spent Together
		</h2>
		<div id="clockdiv" style="width: 1500px; margin: 0 auto;">
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
		<br>
		<br>

		<h2>
			Click on arrow to begin our journey <i style='font-size: 24px'
				class='fas'><a href="/nextSlide/1">&#xf0a9;</a></i>
		</h2>

		<div id="Send-Birthday-Updates">
			<a href="javascript:sendBirthdayUpdates();"
				style="font-weight: bold; color: #FF0000;">Send Birthday
				Reminder</a>
		</div>
</body>
</html>