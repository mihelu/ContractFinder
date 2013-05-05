function RegisterAccountCtrl($scope, Auth) {

    $scope.account = {}

    $scope.personal = [
        {key: true, value: 'Osoba prywatna'},
        {key: false, value: 'Firma'}
    ];

    $scope.personalKey = true;

    $scope.register = function () {
        $scope.account.personal = $scope.personalKey;
        Auth.register($scope.account, function () {
            reset();
        });
    }

    var reset =  function() {
        $scope.account = angular.copy({});
        $scope.personalKey = true;
    }
};