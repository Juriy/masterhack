(function () {
    angular
        .module('masterhack')
        .service('GroupService', GroupService);

    function GroupService($http, config) {
        var currentGroup = [];

        this.getGroup = function () {
            return currentGroup;
        }

        this.setGroup = function (group) {
            currentGroup = group;

        }

        this.createNewGroup = function (groupId) {
            var users = getUserIdList();
            $http({
                method: 'PUT',
                url: config.api + '/groups/?groupId=' + groupId,
                data: users


            })
                .success(
                function (data) {
                    return data;
                }
            )
        };

        this.getGroup = function (id) {
            return $http({
                url: config.api + '/groups/' + id,
                method: 'GET'
            });
        };

        this.getUsersByGroup = function (groupId) {
            return $http(
                {
                    method: 'GET',
                    url: config.api + '/groups/' + groupId + '/users/'
                }
            );
        };

        this.payAmount = function (groupId, userId, amount) {
            return $http(
                {
                    method: 'POST',
                    url: config.api + '/groups/' + groupId + '/users/' + userId + '/' + amount
        });
    };

        this.getItems = function (groupId) {
            return $http({
                method: 'GET',
                url: config.api + '/groups/' + groupId + '/items/'
            })
        };

        this.addItems = function (groupId, items) {
            return $http({
                method: 'POST',
                url: config.api + '/groups/' + groupId,
                data: items
            })
        };

        this.getTotalBill = function(groupId) {
            return $http({
                method: 'GET',
                url: config.api + '/groups/' + groupId + '/totalBill'
            })
        }

        this.getRemainBill = function(groupId) {
            return $http({
                method: 'GET',
                url: config.api + '/groups/' + groupId + '/remainBill'
            })
        }


        this.addUserToGroup = function (groupId, users) {
            $http({
                method: 'POST',
                url: config.api + '/groups/?groupId=' + groupId,
                data: users


            })
                .success(
                function (data) {
                    return data;
                }
            )
        };



        function getUserIdList() {
            var res = [];
            for (user in currentGroup) {
                res.push(user.userId);
            }
            return res;
        };








}

})

();