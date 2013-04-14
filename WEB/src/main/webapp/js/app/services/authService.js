angular.module('authService', []).factory('Auth', function ($http, Alerts) {

    var authorized;

    return {
        isAuthorized: function () {
            return authorized;
        },
        login: function (credentials) {
            $http.post("/rest/auth/login", credentials).
                success(function (data) {
                    console.log(data);
                }).
                error(function (data, error) {
                    console.log(error);
                    Alerts.addAlert('error',data);
                });
            authorized = true;
        },
        logout: function () {
            Alerts.clearAlerts();
            authorized = false;
        },
        register: function (credentials) {

        }
    };

})
;