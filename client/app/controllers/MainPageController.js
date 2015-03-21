(function() {

    angular
        .module('masterhack')
        .controller('MainPageController', MainPageController);

    function MainPageController($routeParams, $scope, UserService) {
        $scope.user = UserService.getUser("");

    }
})();

