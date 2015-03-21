(function() {
	angular
		.module('masterhack')
		.service('UserService', UserService);


	function UserService($http, $q, config) {
		this.getFriends = function() {
			return $http({
				url: config.api + '/users/12/friends',
				method: 'GET',
				params: {
					
				}
			});

		};

		this.getUser = function(id) {
			return $http({
				url: config.api + '/users/' + id,
				method: 'GET'
			});
		};
	}
})();