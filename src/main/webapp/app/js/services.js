angular.module("commentServices", [ "ngResource" ]).factory("Comment", function($resource) {
	return $resource("./comments/:id", {
		id: '@id'
	}, {
		update: {
			method: "PUT"
		},
		remove: {
			method: "DELETE"
		}
	});
});
