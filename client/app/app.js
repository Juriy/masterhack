(function() {
	angular
		.module('masterhack', ['ngRoute'])
		.config(configureRoutes);
		.constant('config', {
			api: 'http://localhost:8080'
		});

	function configureRoutes($routeProvider) {
		$routeProvider
			.when('/friends', {
				controller: 'FriendsController',
				templateUrl: 'views/friends.html'
			})
			.when('/user/:id', {
				controller: 'UserDetailsController',
				templateUrl: 'views/book-details.html'
			})
			.otherwise({
				redirectTo: '/'
			});
	}

})();

