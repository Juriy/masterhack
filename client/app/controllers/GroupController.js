(function() {

    angular
        .module('masterhack')
        .controller('GroupController', GroupController);

    function GroupController($scope, GroupService) {

        $scope.group = GroupService.getGroup();




    }

})();