(function() {

    angular
        .module('masterhack')
        .controller('HomeController', HomeController);

    function HomeController($scope, $location, GroupService, MasterPassService ) {
        GroupService
            .getGroups()
            .then(function(result) {
                $scope.groups = result.data;

            $scope.checkOut = function(){
                MasterPassService.getPaymentObject().then(
                    function(data){
                        MasterPass.client.checkout(data);
                    }
                )

            }
            });



    }

})();