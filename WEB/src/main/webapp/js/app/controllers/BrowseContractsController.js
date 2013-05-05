function BrowseContractsCtrl($scope, $http, Alerts) {

    $scope.test = function() {
        $http.get("/rest/account/test").
            success(function (data) {
                Alerts.info('test');
            });
    }

    $scope.$on(
        "$routeChangeSuccess",
        function () {

            Alerts.addAlert('info', 'browse alert');

        }
    );
};