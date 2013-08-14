function CreateContractCtrl($scope, $http, $location, Alerts, $rootScope) {

    $scope.contract = {};

    $scope.availableCategories = [];

    $scope.selectedCategories = [];

    $scope.selected = {};

    $scope.create = function () {
        Alerts.clearAlerts();
        if ($scope.selectedCategories.length <= 0) {
            Alerts.fatal('Prosze dodać przynajmniej jedną kategorię!');
            return;
        }
        $scope.contract.categories = $scope.selectedCategories;
        console.log($scope.contract);
        $http.post("rest/contract/create", $scope.contract).
            success(function (data) {
                console.log(data);
                Alerts.addAfterRouteChangeAlert('success', 'Zlecenie zostało stworzone');
                $location.path('details/' + data);
                $rootScope.$broadcast("event:contractAdded");
            });
    };

    $scope.initCategories = function () {
        $http.get("rest/category").
            success(function (data) {
                $scope.availableCategories = angular.copy(data);
            });
    };


    $scope.removeCategory = function (category) {
        var i = $scope.selectedCategories.indexOf(category);
        if (i != -1) {
            $scope.selectedCategories.splice(i, 1);
            $scope.availableCategories.push(category);
        }
        ;
        console.log($scope.selectedCategories);
    };

    $scope.addCategory = function () {
        $.each($scope.availableCategories, function (index, value) {
            if (value != undefined && value.id == $scope.selected) {
                $scope.selectedCategories.push(value);
                $scope.availableCategories.splice(index, 1);
            }
        });
        $scope.shouldAddCategoryBeOpen = false;
    };


    $scope.openAddCategory = function () {
        $scope.shouldAddCategoryBeOpen = true;
    }

    $scope.closeAddCategory = function () {
        $scope.shouldAddCategoryBeOpen = false;
    };

    $scope.addCategoryModalOpts = {
        backdropFade: true,
        dialogFade: true
    };

};