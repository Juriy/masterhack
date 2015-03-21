(function() {
	angular
		.module('masterhack')
		.service('UserService', UserService);


	function UserService($http, $q) {
		this.getFriends = function() {
			return $http({
				url: '/api/12/friends',
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