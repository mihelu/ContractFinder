function UserPanelCtrl($scope, Auth, $http) {

    $scope.credentials = {};
    var account = {};

    $scope.login = function() {
        Auth.login($scope.credentials);
        $scope.credentials = {};
    }

    $scope.logout = function() {
        Auth.logout();
    }

    $scope.account = function() {
        return account;
    }

    $scope.initAccount = function() {
        $http.get("/rest/account/details").
            success(function (data) {
                account = data;
            }).error(function (error) {
                $scope.logout();
            });
    }
};