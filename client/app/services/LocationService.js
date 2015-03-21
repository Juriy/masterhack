(function() {

    angular
        .module('masterhack')
        .service('LocationService', LocationService);

    function LocationService($http) {
        this.scanNearby = function(latitude, longtitude){
            return $http({
                method: 'GET',
                url: config.api + '/restaurants?latitude=' + latitude + '&longtitude=' + longtitude

            })
        }

    }

})();