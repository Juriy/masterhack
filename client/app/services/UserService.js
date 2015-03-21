(function() {
	angular
		.module('masterhack')
		.service('UserService', UserService);


	function UserService($http, $q) {
		this.getUsers = function() {
			return $http({
				url: '/api/users',
				method: 'GET',
				params: {
					
				}
			});

		};

		this.getUser = function(id) {
			return $http({
				url: '/api/users/' + id,
				method: 'GET'
			});
		};
	}
})();