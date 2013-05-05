angular.module('authService', []).factory('Auth', function ($http, $location, $cookies, Alerts) {

    return {
        isAuthorized: function () {
            return $cookies['sessionId'] && $cookies['accessToken'];
        },
        login: function (credentials) {
            $http.post("/rest/auth/login", credentials).
                success(function (data) {
                    $location.path('home');
                    Alerts.clearAlerts();
                });
        },
        logout: function () {
            $http.get("/rest/auth/logout").
                success(function (data) {
                    delete $cookies['sessionId'];
                    delete $cookies['accessToken'];
                    $location.path('home');
                    Alerts.clearAlerts();
                });
        },
        register: function (account, callback) {
            var self = this;
            $http.post("rest/account/register", account).
                success(function (data) {
                    Alerts.addAfterRouteChangeAlert('success', 'Konto zostało stworzone');
                    self.login({login: account.login, password: account.password});
                });
            callback();
        }
    };

})
;