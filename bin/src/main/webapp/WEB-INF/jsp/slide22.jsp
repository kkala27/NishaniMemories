
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/image.css" />
</head>
<style>
body {
	background: #85C1E9;
	font-family: sans-serif;
}

form {
	display: flex; /* equal height of the children */
}

.child {
	flex: 1; /* additionally, equal width */
	padding: 1em;
}

/*assign full width inputs*/
input[type=text], input[type=password] {
	width: 60%;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	box-sizing: border-box;
}
/*set a style for the buttons*/
button {
	background-color: #4CAF50;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	cursor: pointer;
	width: 60%;
}
/* set a hover effect for the button*/
button:hover {
	opacity: 0.8;
}
/*set extra style for the cancel button*/
.cancelbtn {
	width: auto;
	padding: 10px 18px;
	background-color: #f44336;
}

/*set padding to the container*/
.container {
	padding: 8px;
	height: 500px;
}

.containerTail {
	padding: 8px;
}
/*set the forgot password text*/
span.psw {
	float: right;
	padding-top: 16px;
}
/*set styles for span and cancel button on small screens*/
@media screen and (max-width: 300px) {
	span.psw {
		display: block;
		float: none;
	}
	.cancelbtn {
		width: 100%;
	}
}

h1 {
	color: #FFFFFF;
	font-weight: 100;
	font-size: 40px;
	margin: 40px 0px 20px;
	font-family: "Lucida Handwriting", "Brush Script MT", monospace;
}

a {
	text-decoration: none;
	display: inline-block;
	padding: 8px 16px;
}
a:hover {
	background-color: #ddd;
	color: black;
}

.previous {
	background-color: #f1f1f1;
	color: black;
}

.next {
	background-color: #f1f1f1;
	color: black;
}
</style>

<body>


	<!--Step 1 : Adding HTML-->
	<form method="post" action="validateLogin">
		<div class="child">
		<a href="/welcome" class="previous">&#9750; Home</a>
			<div class="imgcontainer">
				<img src="/getImage/slide22.jpg" alt="Avatar" class="avatar">
			</div><br><br>
			<a href="/nextSlide/22" class="previous">&laquo; Previous</a> <  a href="/nextSlide/25"
				class="next">Next &raquo;</a>
		</div>
		<div class="child">
			<div class="container" style="background-color: #1F618D">
				<div class="containerTail" style="background-color: #154360">
					<h1>
						<center>Go Goa Gone.
					</h1>
				</div>
<p>Another one from Goa and this was a day to remember we decided to visit North Goa on scooty and it was a long ride and once we reached there Batra forced us to do those vodka shots. Everyone handled it pretty well except for you. I knew it was a terrible idea because we had a fight just before leaving the hotel and as soon as the third shot hit you, started the unconsolable crying all the bad memories started flashing around your eyes. Damnn your BAD TRIPS !!!!! </p>
			</div>
		</div>
	</form>
</body>

</html>