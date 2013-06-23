function CreateContractCtrl($scope, $http, $location, Alerts) {

    $scope.contract = {};

    $scope.create = function() {
        $http.post("rest/contract/create", $scope.contract).
            success(function (data) {
                console.log(data);
                Alerts.addAfterRouteChangeAlert('success', 'Zlecenie zostało stworzone');
                $location.path('details/' + data);
            });
    }

};