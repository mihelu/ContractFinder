angular.module('alertsService',[]).factory('Alerts', function () {

    var alerts = [
        { type: 'error', msg: 'Oh snap! Change a few things up and try submitting again.' },
        { type: 'success', msg: 'Well done! You successfully read this important alert message.' }
    ];

    return {
        getAlerts: function() {
            return alerts;
        },
        addAlert: function (type, msg) {
            alerts.push({type: type, msg: msg});
        },
        closeAlert: function (index) {
            alerts.splice(index, 1);
        },
        clearAlerts: function() {
            alerts.splice(0, alerts.length);
        }
    }

});