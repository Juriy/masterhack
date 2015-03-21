(function() {

	angular
		.module('masterhack')
		.controller('FriendsController', FriendsController);

	function FriendsController($scope, UserService) {
		UserService
			.getFriends()
			.then(function(result) {
				$scope.friends = result.users;
			});
	}

})();