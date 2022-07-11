
 

 let serverUrl = '';

window.onload = function() {
  console.log("loading login page");
  var profile = document.getElementById("profile").value;
  if(profile=='local'){
	   serverUrl = 'http://localhost:8080/'; 
  }else{
	   serverUrl = 'https://nishani-memories.herokuapp.com/'
  }
};

generateOtp =   function(){
    console.log("Calling java script for send OTP");
    console.log(document.getElementById("num").value);
    var result;
    const request = new XMLHttpRequest();
    const url =  serverUrl+'sendOtp/'+document.getElementById("num").value;
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
    	    	displayStatusDiv1.style.display = 'block';
    	    	displayStatusDiv2.style.display = 'none';
    	    }
    }
    
    resendOtp = function(){
        const request = new XMLHttpRequest();
        const url = serverUrl+'sendOtp/'+document.getElementById("num").value;
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
