function UserPanelCtrl($scope, Auth, $http, $rootScope) {

    $scope.credentials = {};
    var account = {};
    var contracts = {};
    var offers = {};

    $scope.login = function () {
        Auth.login($scope.credentials, function () {
            $scope.credentials = {};
        });
    }

    $scope.logout = function () {
        Auth.logout();
    }

    $scope.account = function () {
        return account;
    }

    $scope.contracts = function () {
        return contracts;
    }

    $scope.offers = function () {
        return offers;
    }

    $scope.initAccount = function () {
        $http.get("/rest/account/details").
            success(function (data) {
                account = data;
            }).error(function (error) {
                $scope.logout();
            });
    }

    $scope.initContracts = function () {
        $http.get("/rest/contract/account").
            success(function (data) {
                contracts = data;
            }).error(function (error) {
                $scope.logout();
            });
    }

    $scope.initOffers = function () {
        $http.get("/rest/offer/account").
            success(function (data) {
                offers = data;
            }).error(function (error) {
                $scope.logout();
            });
    }

    $rootScope.$on(
        "event:contractAdded",
        function () {
            $scope.initContracts();
        }
    );
};