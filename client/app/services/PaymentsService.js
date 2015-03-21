(function() {
    angular
        .module('masterhack')
        .service('PaymentsService', PaymentsService);


    function PaymentsService($http, $q) {

        var user = {
                publicKey:"",
                privateKey: "",
                card: {
                    addressCity: "OFallon",
                    addressState: "MO",
                    cvc: "123",
                    expMonth: "11",
                    expYear: "19",
                    number: "5105105105105100"
                }
            };



        this.processPayment = function() {
            SimplifyCommerce.generateToken({
                key: user.publicKey,
                card: user.card
            }, simplifyResponseHandler);

        };


        function simplifyResponseHandler(data){


        }
    }
})();