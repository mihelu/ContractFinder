function AppCtrl($scope, $location, Auth, Alerts) {

    //alerts
    $scope.alerts = Alerts;
    //authorization
    $scope.credentials = {};

    $scope.auth = Auth;

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
