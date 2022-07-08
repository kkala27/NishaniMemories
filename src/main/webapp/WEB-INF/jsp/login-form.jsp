<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<base href="/" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<script type="text/javascript" src="/js/login-script.js"></script>
</head>
<body>

	<!--Step 1 : Adding HTML-->
	<form method="post" action="validateOtp">
		<div class="child">
			<div class="imgcontainer">
				<img src="/getImage/IMG_1646.jpeg" alt="Avatar" class="avatar">
			</div>
		</div>
		<div class="child">
			<br> <br> <br> <br>
			<div class="containerTail" style="background-color: #154360">
				<h1>
					<center>Please Login
				</h1>
			</div>
			<div class="container" style="background-color: #1F618D" id="div1">
				<br> <br> <label><b> Enter Mobile Number </b></label><br>
				<input type="text" placeholder="Enter Mobile Number" name="num"
					id="num" required> <br>
				<button type="button" onclick="javascript:generateOtp()">Generate
					OTP</button>
				<br>
			</div>
			<div class="container"
				style="background-color: #1F618D; display: none" id="div2">
				<br> <br> <label><b> Validate OTP </b></label><br> <input
					type="text" placeholder="Validate OTP" name="otp" required>
				<br>
				<a href="javascript:resendOtp();" style = "font-weight: bold;color: #FF0000;">Resend OTP</a>
				<br>
				<button type="submit">submit</button>
				<br>
				<div style="color: red">${error}</div>
			</div>
		</div>
	</form>
</body>

</html>