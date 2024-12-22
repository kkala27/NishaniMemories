

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

var myVar = setInterval(function() {
		myTimer()
	}, 1000);
	var counter = 0;
	function myTimer() {
		const monthNames = [ "January", "February", "March", "April", "May",
				"June", "July", "August", "September", "October", "November",
				"December" ];
		const dateObj = new Date();
		const month = monthNames[dateObj.getMonth()];
		const day = String(dateObj.getDate()).padStart(2, '0');
		const year = dateObj.getFullYear();
		const output = month + '\n' + day + ',' + year;

		const date2 = new Date("feb 15, 2020 19:00:00");
		var now = new Date().getTime();
		var t = date2 - now;
		var t = deadline - now;
		const diffTime = Math.abs(date2 - dateObj);
		const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));
		document.getElementById("date").innerHTML = output;
		document.getElementById("days").innerHTML = diffDays - 1;
	}

	var currenttime = '<!--#config timefmt="%B %d, %Y %H:%M:%S"--><!--#echo var="DATE_LOCAL" -->' // SSI
																									// method
																									// of
																									// getting
																									// server
																									// date
	// var currenttime = '<? print date("F d, Y H:i:s", time())?>' //PHP method
	// of getting server date

	// /////////Stop editting here/////////////////////////////////

	var montharray = new Array("January", "February", "March", "April", "May",
			"June", "July", "August", "September", "October", "November",
			"December")
	var serverdate = new Date(currenttime)

	function padlength(what) {
		var output = (what.toString().length == 1) ? "0" + what : what
		return output
	}

	function displaytime() {
		serverdate.setSeconds(serverdate.getSeconds() + 1)
		var datestring = montharray[serverdate.getMonth()] + " "
				+ padlength(serverdate.getDate()) + ", "
				+ serverdate.getFullYear()
		var timestring = padlength(serverdate.getHours()) + ":"
				+ padlength(serverdate.getMinutes()) + ":"
				+ padlength(serverdate.getSeconds())
	}

	window.onload = function() {
		setInterval("displaytime()", 1000)
	}
	var deadline = new Date("feb 15, 2020 19:00:00").getTime();

	var x = setInterval(function() {

		var now = new Date().getTime();
		var t = now - deadline;
		var days = Math.floor(t / (1000 * 60 * 60 * 24));
		var hours = Math.floor((t % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
		var minutes = Math.floor((t % (1000 * 60 * 60)) / (1000 * 60));
		var seconds = Math.floor((t % (1000 * 60)) / 1000);
		document.getElementById("day").innerHTML = days;
		document.getElementById("hour").innerHTML = hours;
		document.getElementById("minute").innerHTML = minutes;
		document.getElementById("second").innerHTML = seconds;
		if (t < 0) {
			clearInterval(x);
			document.getElementById("demo").innerHTML = "TIME UP";
			document.getElementById("day").innerHTML = '0';
			document.getElementById("hour").innerHTML = '0';
			document.getElementById("minute").innerHTML = '0';
			document.getElementById("second").innerHTML = '0';
		}
	}, 1000);