(function() {
	angular
		.module('masterhack')
		.service('SessionService', SessionService);

	function SessionService() {
		this.getId = function {
			return 12;
		}
	}
})();