(function() {

    angular
        .module('masterhack')
        .controller('CheckoutController', CheckoutController);

    function CheckoutController($scope, MasterPassService) {


        $scope.processPayment = function(){
            console.log("Click");
            MasterPassService.getPaymentObject().then(
                function(data){
                    Masterpass.client.checkout(data);
                }

            );

        }



    }

})();