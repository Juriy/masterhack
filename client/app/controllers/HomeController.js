(function() {

    angular
        .module('masterhack')
        .controller('HomeController', HomeController);

    function HomeController($scope, $location, GroupService, MasterPassService, LocationService ) {



        navigator.geolocation.getCurrentPosition(function (data){
            LocationService.scanNearby(data.coords.latitude, data.coords.longitude).then(
                function(places){
                    $scope.places = places.data;
                }
            );

        });

        $scope.gotoFriends = function(){
            $location.url('/friends')
        }

        GroupService
            .getGroups()
            .then(function(result) {
                $scope.groups = result.data;

                $scope.items = $scope.groups[0].cart.itemsToPay;

            $scope.checkOut = function(){
                MasterPassService.getPaymentObject($scope.items).then(
                    function(data){
                        MasterPass.client.checkout(data);
                    }
                )

            }
            });



    }

})();