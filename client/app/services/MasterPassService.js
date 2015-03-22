(function() {
    angular
        .module('masterhack')
        .service('MasterPassService', MasterPassService);


    function MasterPassService($http, $q, config) {
        this.getPaymentObject = function(items) {
            return $http({
                url: config.api + '/masterpass',
                method: 'POST',
                data: items
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