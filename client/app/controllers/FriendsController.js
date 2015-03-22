(function() {

	angular
		.module('masterhack')
		.controller('FriendsController', FriendsController);

	function FriendsController($scope, $location, UserService, GroupService) {
		UserService
			.getFriends()
			.then(function(result) {
				$scope.friends = result.data;

                $scope.unselected = $scope.friends;
                $scope.selected = GroupService.getGroup();

                $scope.addToParty = function (user) {
                    $scope.selected.push(user);

                    var index = $scope.unselected.indexOf(user);
                    $scope.unselected.splice(index ,1);
                }

                $scope.toggleSelection = function (user) {

                    var index = $scope.unselected.indexOf(user);
                    if(index>1)
                    $scope.unselected.splice(index ,1);
                }

                $scope.showParty = function (){
                    return $scope.selected.length >0;
                }

			});

        $scope.gotoGroup = function (){
            //GroupService.setGroup($scope.selected);
            $location.url('/group');
        }


	}

})();