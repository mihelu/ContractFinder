function DetailsContractCtrl($scope, $routeParams, $http, $location) {

    var contract = {};

    $scope.contract = function () {
        return contract;
    }

    $scope.initContract = function () {
        $http.get("/rest/contract/details/" + $routeParams.id).
            success(function (data) {
                contract = data;
            }).error(function (error) {
                $location.path('noPage');
            });
    }

};
