

generateOtp =   function(){
    console.log("Calling java script for send OTP");
    console.log(document.getElementById("num").value);
    var result;
    const request = new XMLHttpRequest();
    const url = 'http://localhost:8080/sendOtp/'+document.getElementById("num").value;
    request.open("GET", url);
    request.send();

    request.onload = (e) => {
    	result = request.response;
    	 console.log(result);
    	    var displayStatusDiv1 = document.getElementById("div1");
    	    var displayStatusDiv2 = document.getElementById("div2");
    	    if ( result == 'success' ){
    	    	displayStatusDiv1.style.display = 'none';
    	    	displayStatusDiv2.style.display = 'block';
    	    }else if( result == 'Authenticate' ){
    	    	displayStatus.style.display = 'block';
    	    	displayStatusDiv2.style.display = 'none';
    	    }
    }
    
    resendOtp = function(){
        const request = new XMLHttpRequest();
        const url = 'http://localhost:8080/sendOtp/'+document.getElementById("num").value;
        request.open("GET", url);
        request.send();

        request.onload = (e) => {
        	result = request.response;
        	    if ( result == 'success' ){
        	    	alert("Otp sent successfully");
        	    }else{
        	    	alert("There is some issue in sending the otp please try again after some time");
        	    }
        }
    }
}  