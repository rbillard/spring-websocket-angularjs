<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html class="no-js" lang="fr">
<head>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>Spring - Live updates</title>
	
	<link rel="stylesheet" href="lib/bootstrap/dist/css/bootstrap.min.css" />
	<link rel="stylesheet" href="app/css/app.css" />
	
	<%--
	Next to AngularJS we also need angular-resource which makes it easier to integrate RESTful webservices in the client.
	SockJS and STOMP are both used for the WebSocket communication, SockJS being the lower level component providing a
	WebSocket client (+ fallbacks if not supported) and STOMP providing a protocol above WebSockets. Showdown is a library
	to convert markdown markup syntax to plain HTML so we can render it.
	--%>
	<script type="text/javascript" src="lib/angular/angular.min.js"></script>
	<script type="text/javascript" src="lib/angular-route/angular-route.min.js"></script>
	<script type="text/javascript" src="lib/angular-resource/angular-resource.min.js"></script>
	<script type="text/javascript" src="lib/sockjs/sockjs.min.js"></script>
	<script type="text/javascript" src="lib/stomp-websocket/lib/stomp.min.js"></script>
	<script type="text/javascript" src="lib/showdown/compressed/showdown.js"></script>
	<script type="text/javascript" src="app/js/app.js"></script>
	<script type="text/javascript" src="app/js/services.js"></script>
	<script type="text/javascript" src="app/js/controllers.js"></script>
	
	<script type="text/javascript">
		<c:url value="/" var="context" />
		var context = "${ context }";
		var headers = { headers: { 'Content-Type': 'application/json; charset=UTF-8' } };
	</script>

</head>

<body ng-app="commentsApp">

	<div class="container">
	
		<h1>Spring - Websocket - Angularjs</h1>
		<div id="content" ng-view></div>

	</div>
	
</body>
</html>