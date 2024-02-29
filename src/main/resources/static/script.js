document.addEventListener("DOMContentLoaded", function () {
	var dateObject = new Date();
	var dateString = dateObject.toISOString().split('T')[0]

	document.getElementById("query-link").href = "/date/" + dateString;
});
