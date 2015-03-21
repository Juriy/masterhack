(function() {
	angular
		.module('masterhack', ['ngRoute'])
		.config(configureRoutes);

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
            .when('/order', {
                controller: 'GroupCartController',
                templateUrl: 'views/cart-details.html'
            })
            .when ('/', {
            controller: 'MainPageController',
            templateUrl: 'views/main-page.html'
        })
			.otherwise({
				redirectTo: '/'
			});
	}

})();

