<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <base href="/">
    <link rel="stylesheet" href="css/style.css">
    <script src="/js/login-script.js" defer></script>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .form-container {
            background: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            width: 400px;
        }

        .imgcontainer {
            text-align: center;
            margin: 20px 0;
        }

        .imgcontainer img {
            width: 100px;
            border-radius: 50%;
        }

        h1 {
            text-align: center;
            color: #154360;
            font-size: 24px;
        }

        label {
            font-weight: bold;
            color: #333;
            display: block;
            margin-bottom: 8px;
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        button {
            width: 100%;
            padding: 10px;
            background-color: #1F618D;
            color: #fff;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            margin: 10px 0;
        }

        button:hover {
            background-color: #154360;
        }

        .resend-link {
            color: #FF0000;
            font-weight: bold;
            text-align: center;
            display: block;
            margin: 10px 0;
            text-decoration: none;
        }

        .resend-link:hover {
            text-decoration: underline;
        }

        .error-message {
            color: red;
            font-size: 14px;
            text-align: center;
        }

        .hidden {
            display: none;
        }

        .container {
            margin-top: 20px;
        }
    </style>
</head>
<body>

<div class="form-container">
    <form method="post" action="validateOtp">
        <input type="hidden" id="profile">

        <div class="imgcontainer">
            <img src="/getImage/login-page.jpg" alt="Avatar">
        </div>

        <h1>Please Login</h1>

        <div class="container" id="div1">
            <label for="num">Enter Mobile Number</label>
            <input type="text" placeholder="Enter Mobile Number" name="num" id="num" required>
            <button type="button" onclick="javascript:generateOtp()">Generate OTP</button>
        </div>

        <div class="container hidden" id="div2">
            <label for="otp">Validate OTP</label>
            <input type="password" placeholder="Enter OTP" name="otp" id="otp" required>
            <a href="javascript:resendOtp();" class="resend-link">Resend OTP</a>
            <button type="submit">Submit</button>
            <div class="error-message">${error}</div>
        </div>
    </form>
</div>

<script>
    console.log('Fetching session attribute');
    var profile = "<%= (String) session.getAttribute("profile") %>";
    console.log(profile);
    document.getElementById("profile").value = profile;
</script>

</body>
</html>
