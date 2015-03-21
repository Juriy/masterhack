(function() {

    angular
        .module('masterhack')
        .controller('HomeController', HomeController);

    function HomeController($scope, $location, GroupService) {
        GroupService
            .getGroups()
            .then(function(result) {
                $scope.groups = result;


            });



    }

})();