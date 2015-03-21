(function() {
	
	angular
		.module('masterhack')
		.controller('UserDetailsController', UserDetailsController);

	function UserDetailsController($routeParams, $scope, UserService) {
		UserService
			.getUser(+$routeParams.id)
			.then(function(payload) {
				$scope.user = payload.data;
			});
	}
})();

