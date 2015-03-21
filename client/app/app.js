(function() {
	angular
		.module('masterhack', ['ngRoute'])
		.config(configureRoutes)
		.constant('config', {
			api: 'http://localhost:8080'
		});

	function configureRoutes($routeProvider) {
		$routeProvider
			.when('/home', {
				controller: 'PotController',
				templateUrl: 'views/home.html'
			})
			.when('/group', {
				controller: 'GroupController',
				templateUrl: 'views/group.html'
			})
			.when('/friends', {
				controller: 'FriendsController',
				templateUrl: 'views/friends.html'
			})
			.when('/user/:id', {
				controller: 'UserDetailsController',
				templateUrl: 'views/user-details.html'
			})
            .when('profile', {
                controller: 'UserDetailsController',
                templateUrl: 'views/user-details.html'
            })
			.otherwise({
				controller: 'PotController',
				templateUrl: 'views/home.html'
			});
	}

})();

