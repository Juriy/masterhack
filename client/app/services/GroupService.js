(function() {
    angular
        .module('masterhack')
        .service('GroupService', GroupService);



    function GroupService($http, config) {
    var currentGroup = [];

    this.getGroup =  function() {
        return currentGroup;
    }

    this.setGroup = function(group) {
        currentGroup = group;

    }
    //
    //this.createNewGroup = function(){
    //    $http.post( '' {}).success{}.;
    // }
    //



    this.getGroup = function(id) {
            return $http({
                url: config.api + '/groups/' + id,
                method: 'GET'
            });
    };

    function getUserIdList(){
      var res = [];
      for(user in currentGroup){
          res.push(user.userId);
      }
        return res;
    };

    }
})();