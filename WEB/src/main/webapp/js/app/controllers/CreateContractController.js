function CreateContractCtrl($scope, $http, $location, Alerts) {

    $scope.contract = {};

    $scope.create = function() {
        console.log($scope.contract);
        $http.post("rest/contract/create", $scope.contract).
            success(function (data) {
                console.log(data);
                Alerts.addAfterRouteChangeAlert('success', 'Zlecenie zostało stworzone');
                $location.path('details/' + data);
            });
    };

    $scope.uploadComplete = function (content, completed) {
        if (completed && content.length > 0) {
            console.log(content);
        }
    };

};