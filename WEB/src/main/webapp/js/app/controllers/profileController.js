function ProfileCtrl($scope, $routeParams, $http) {

    var account = {};

    $scope.account = function () {
        return account;
    }

    $scope.initProfile = function() {
        $http.get("/rest/account/details/" + $routeParams.id).
            success(function (data) {
                account = data;
            }).error(function (error) {
                $scope.logout();
            });
    };

};