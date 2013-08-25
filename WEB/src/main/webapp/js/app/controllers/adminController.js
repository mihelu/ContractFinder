function AdminCtrl($scope, $http) {

    $scope.categories = {}

    $scope.categoryName = '';

    $scope.ping = function () {
        $http.get("/rest/auth/pingAdmin");
    };

    $scope.initCategories = function () {
        $http.get("/rest/category/all").
            success(function (data) {
                $scope.categories = angular.copy(data);
            });
    };

    $scope.removeCategory = function (id) {
        $http.post("/rest/category/changeStatus/" + id, "true").
            success(function (data) {
                $scope.initCategories();
            });
    };

    $scope.restoreCategory = function (id) {
        $http.post("/rest/category/changeStatus/" + id, "false").
            success(function (data) {
                $scope.initCategories();
            });
    };

    $scope.createCategory = function() {
        console.log($scope.categoryName);
        $http.post("rest/category/create", $scope.categoryName).
            success(function (data) {
                $scope.initCategories();
            });
        $scope.categoryName = '';
        $scope.shouldCreateCategoryBeOpen = false;
    };

    $scope.openCreateCategory = function () {
        $scope.categoryName = '';
        $scope.shouldCreateCategoryBeOpen = true;
    }

    $scope.closeCreateCategory = function () {
        $scope.categoryName = '';
        $scope.shouldCreateCategoryBeOpen = false;
    };

    $scope.createCategoryModalOpts = {
        backdropFade: true,
        dialogFade: true
    };

};