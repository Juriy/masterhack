(function() {
	
	angular
		.module('masterhack')
		.controller('UserDetailsController', UserDetailsController);

	function UserDetailsController($routeParams, $scope, UserService) {
		UserService
			.getUser('12')
			.then(function(payload) {
				$scope.user = payload.data;
			});
	}
})();

