function AppCtrl($scope, $location, Alerts) {

    $scope.alerts = Alerts.getAlerts();

    $scope.addAlert = function () {
        Alerts.addAlert('success', 'TEST');
    };

    $scope.closeAlert = function (index) {
        Alerts.closeAlert(index);
    };

    $scope.clearAlerts = function () {
        Alerts.clearAlerts();
    };

    $scope.navClass = function (page) {
        var currentRoute = $location.path().substring(1) || 'home';
        return page === currentRoute ? 'active' : '';
    };

    $scope.$on(
        "$routeChangeSuccess",
        function () {

            Alerts.clearAlerts();

        }
    );

};
