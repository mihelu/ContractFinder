function BrowseContractsCtrl($scope, $http, Alerts) {

    var contracts = [];

    $scope.findData = {};

    $scope.contracts = function() {
        return contracts;
    };

    $scope.find = function () {
        $http.post('/rest/contract/find', $scope.findData).
            success(function (data) {
                console.log(data);
                contracts = data;
            });
    };

    $scope.$on('$routeChangeSuccess', function (scope, next, current) {
        $scope.find();
    });
};