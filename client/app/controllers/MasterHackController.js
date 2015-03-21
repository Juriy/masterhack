(function() {

    angular
        .module('masterhack')
        .controller('MasterHackController', MasterHackController);

    function MasterHackController($scope, $location) {
        $scope.go = function(path) {
            $location.path(path);
        };
    }
})();

