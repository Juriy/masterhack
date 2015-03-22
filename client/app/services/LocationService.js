(function() {

    angular
        .module('masterhack')
        .service('LocationService', LocationService);

    function LocationService($http, config) {
        this.scanNearby = function(latitude, longitude){
            return $http({
                method: 'GET',
                url: config.api + '/restaurants?latitude=' + latitude + '&longitude=' + longitude

            })
        }

    }

})();