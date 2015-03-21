(function() {
    angular
        .module('masterhack')
        .service('MasterPassService', MasterPassService);


    function MasterPassService($http, $q, config) {
        this.getPaymentObject = function() {
            return $http({
                url: config.api + '/masterpass',
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