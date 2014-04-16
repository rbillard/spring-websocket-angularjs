var commentsApp = angular.module( "commentsApp", [
	"ngRoute",
	"commentServices",
	"userControllers",
	"commentControllers"
]);

commentsApp.config( function( $routeProvider ) {
	
	$routeProvider.
		when('/connexion', {
			templateUrl: 'partials/user/user-create.html',
			controller: 'UserCreateCtrl'
		}).
		when('/comments', {
			templateUrl: 'partials/comment/comments.html',
			controller: 'CommentCtrl'
		}).
		otherwise({
			redirectTo: '/connexion'
		});
});
