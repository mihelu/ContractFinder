angular.module('alertsService',[]).factory('Alerts', function () {

    var alerts = [];
    var afterRouteChangeAlert = [];

    return {
        getAlerts: function() {
            return alerts;
        },
        info: function(msg) {
            this.addAlert('info',msg);
        },
        fatal: function(msg) {
            this.addAlert('error',msg);
        },
        ok: function(msg) {
            this.addAlert('success',msg);
        },
        addAlert: function (type, msg) {
            alerts.push({type: type, msg: msg});
        },
        closeAlert: function (index) {
            alerts.splice(index, 1);
        },
        clearAlerts: function() {
            alerts.splice(0, alerts.length);
        },
        addAfterRouteChangeAlert: function(type,msg) {
           afterRouteChangeAlert.push({type: type, msg: msg});
        },
        showAfterRouteChangeAlerts: function() {
            alerts = angular.copy(afterRouteChangeAlert);
            afterRouteChangeAlert.splice(0,afterRouteChangeAlert.length);
        }
    }

});