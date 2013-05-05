function AppCtrl($scope, $location, Auth, Alerts, $rootScope, $cookies) {

    //alerts
    $scope.alerts = Alerts;
    //authorization
    $scope.auth = Auth;

    $scope.navClass = function (page) {
        var currentRoute = $location.path().substring(1) || 'home';
        return page === currentRoute ? 'active' : '';
    };

    $scope.$on(
        "$routeChangeSuccess",
        function (scope, next, current) {
            Alerts.clearAlerts();
            Alerts.showAfterRouteChangeAlerts();
        }
    );

    $rootScope.$on(
        "event:unauthorized",
        function () {
            Alerts.addAfterRouteChangeAlert('warn', 'Wymagane zalogowanie!');
            Auth.logout();
        }
    );

    $scope.$watch(function () {
        return Auth.isAuthorized()
    }, function (newVal, oldVal) {
        if (newVal === undefined && oldVal !== undefined) {
            Auth.logout();
        }
    }, true);
};
