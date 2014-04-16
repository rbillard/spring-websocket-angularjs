angular.module( "userControllers", [] ).controller( "UserCreateCtrl", function( $scope, $http, $rootScope ) {
	
	$scope.user = {};
	
	$scope.create = function() {
		
		$http.post( context, $scope.user, headers )
	        .success( function ( data ) {
	        	console.log( data );
	        	$rootScope.user = data;
	        	window.location = context + "#/comments";
	        })
	        .error( function( data, status, headers, config ) {
	            $scope.errors = data;
	        });
	};
	
});

/** The Comment object refers to the factory we made earlier on services.js */
angular.module("commentControllers", []).controller("CommentCtrl", function($scope, $rootScope, Comment) {
	
	$scope.model = {
		comments: Comment.query(),
		newComment: {
			content: null
		}
	};
	
	/**
	 * The $scope.initiator will be used to set a flag when the user changed an idea. If the user changed an idea, the
	 * local model is updated automatically, however, because the websockets are notifying each client of the changes,
	 * the client would reload the model, which is not necessary in this case. The $scope.initiator flag will prevent
	 * reloading the model in this case.
	 */
	$scope.initiator = false;
	
	$scope.socket = {
		client: null,
		stomp: null
	};
	
	$scope.add = function() {
		$scope.initiator = true;
		var comment = new Comment();
		comment.content = $scope.model.newComment.content;
		comment.user = $rootScope.user;
		comment.votes = 0;
		comment.$save(function(response) {
			$scope.model.comments.push(response);
		});
		$scope.model.newComment.content = "";
	};
	
	$scope.remove = function(/** Comment */ comment, /** Integer */ index) {
		$scope.initiator = true;
		$scope.model.comments.splice(index, 1);
		comment.$remove();
	};
	
	$scope.addVotes = function(/** Comment */ comment, /** Integer */ votes) {
		$scope.initiator = true;
		comment.votes += votes;
		comment.$update();
	};
	
	// WEBSOCKET
	
	/**
	 * This function first verifies if the current user is the initiator (by using the $scope.initiator flag) and then 
	 * updates the model by query'ing the RESTful webservice. When it's done the initiator flag is put to false again.
	 */
	$scope.notify = function(/** Message */ message) {
		if (!$scope.initiator) {
			Comment.query(function(comments) {
				$scope.model.comments = comments;
			});
		}
		$scope.initiator = false;
	};
	
	/**
	 * This function reconnect to the server each time the WebSocket connection is broken, that�s what I do by 
	 * calling the $scope.reconnect() function.
	 */
	$scope.reconnect = function() {
		setTimeout($scope.initSockets, 10000);
	};
	
	/**
	 * This function will open a connection to /spring-live-updates/notify and use the Stomp library to listen to the 
	 * /topic/notify topic. This is the same topic we used in the aspects in the back-end code, so all notifications 
	 * will be sent to the $scope.notify() function.
	 */
	$scope.initSockets = function() {
		$scope.socket.client = new SockJS('/spring-websocket-angularjs/notify');
		$scope.socket.stomp = Stomp.over($scope.socket.client);
		$scope.socket.stomp.connect({}, function() {
			$scope.socket.stomp.subscribe("/topic/notify", $scope.notify);
		});
		$scope.socket.client.onclose = $scope.reconnect;
	};
	
	/**
	 * The first thing done when initializing the controller is that the initSockets() function is called.
	 */
	$scope.initSockets();
	
});
