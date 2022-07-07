<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<base href="/" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>

	<c:if test="${empty result}">
		<!--Step 1 : Adding HTML-->
		<form method="post" action="generateOtp">
			<div class="child">
				<div class="imgcontainer">
					<img src="/getImage/IMG_1646.jpeg" alt="Avatar" class="avatar">
				</div>
			</div>
			<div class="child">
				<div class="container" style="background-color: #1F618D">
					<br> <br> <br> <br>
					<div class="containerTail" style="background-color: #154360">
						<h1>
							<center>Please Login
						</h1>
					</div>
					<br> <br> <label><b> Enter Mobile Number </b></label><br>
					<input type="text" placeholder="Enter Mobile Number" name="num"
						required>
					<button type="submit">Generate OTP</button>
					<br>
				</div>
			</div>
		</form>
	</c:if>
	<c:if test="${not empty result}">
		<form method="post" action="validateOtp">
			<div class="child">
				<div class="imgcontainer">
					<img src="/getImage/IMG_1646.jpeg" alt="Avatar" class="avatar">
				</div>
			</div>
			<div class="child">
				<div class="container" style="background-color: #1F618D">
					<br> <br> <br> <br>
					<div class="containerTail" style="background-color: #154360">
						<h1>
							<center>Please Login
						</h1>
					</div>
					<br> <br> <label><b> Validate OTP </b></label><br> <input
						type="text" placeholder="Validate OTP" name="otp" required>
					<button type="submit">submit</button>
					<br>
					<div style="color: red">${error}</div>
				</div>
			</div>
		</form>
	</c:if>
</body>

</html>