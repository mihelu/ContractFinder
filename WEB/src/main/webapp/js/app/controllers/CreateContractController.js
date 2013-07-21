function CreateContractCtrl($scope, $http, $location, Alerts, $rootScope) {

    $scope.contract = {};

    $scope.create = function() {
        console.log($scope.contract);
        $http.post("rest/contract/create", $scope.contract).
            success(function (data) {
                console.log(data);
                Alerts.addAfterRouteChangeAlert('success', 'Zlecenie zostało stworzone');
                $location.path('details/' + data);
                $rootScope.$broadcast("event:contractAdded");
            });
    };

    $scope.uploadComplete = function (content, completed) {
        if (completed && content.length > 0) {
            console.log(content);
        }
    };

};