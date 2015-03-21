(function() {
	angular
		.module('masterhack')
		.service('SessionService', SessionService);

	function SessionService() {
		var user_id = 12;

        this.getId = function() {
			return user_id;
		}

	}
})();