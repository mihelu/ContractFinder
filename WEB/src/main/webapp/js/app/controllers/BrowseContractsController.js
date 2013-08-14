function BrowseContractsCtrl($scope, $http, Alerts) {

    $scope.availableCategories = [];

    $scope.selectedCategories = [];

    $scope.selected = {};

    var contracts = [];

    $scope.findData = {};

    $scope.contracts = function() {
        return contracts;
    };

    $scope.find = function () {
        Alerts.clearAlerts();
        var categorieIds = [];
        $.each($scope.selectedCategories, function (index, value) {
            if (value != undefined && value.id != undefined){
                categorieIds.push(value.id)
            }
        });
        $scope.findData.categories = categorieIds;
        $http.post('/rest/contract/find', $scope.findData).
            success(function (data) {
                console.log(data);
                contracts = data;
            });
    };

    $scope.$on('$routeChangeSuccess', function (scope, next, current) {
        $scope.find();
    });

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