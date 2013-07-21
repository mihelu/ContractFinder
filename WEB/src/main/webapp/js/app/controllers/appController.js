function AppCtrl($scope, $location, Auth, Alerts, $rootScope, $http) {

    var loading = false;
    //alerts
    $scope.alerts = Alerts;
    //authorization
    $scope.auth = Auth;

    $scope.loadingIndicator = function () {
        return loading;
    }

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

    $rootScope.$on(
        "event:ajaxStart",
        function () {
            loading = true;
        }
    );

    $rootScope.$on(
        "event:ajaxEnd",
        function () {
            loading = false;
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
