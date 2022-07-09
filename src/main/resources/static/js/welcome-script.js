
 let serverUrl = '';

window.onload = function() {
  console.log("loading welcome page");
  var profile = document.getElementById("profile").value;
  if(profile=='local'){
	   serverUrl = 'http://localhost:8080/'; 
  }else{
	   serverUrl = 'https://nishani-memories.herokuapp.com/'
  }
};

    sendBirthdayUpdates = function(){
        const request = new XMLHttpRequest();
        const url = serverUrl+'sendBirthdayUpdates';
        request.open("GET", url);
        request.send();

        request.onload = (e) => {
        	result = request.response;
        	    if ( result == 'success' ){
        	    	alert("Birthday Reminder Sent.");
        	    }else{
        	    	alert("Error Occured while sending Birthday Reminder.");
        	    }
        }
    }


