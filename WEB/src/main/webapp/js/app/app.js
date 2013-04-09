/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 31.03.13
 * Time: 21:23
 */
var ContractFinder = angular.module("ContractFinder", ['ui.bootstrap', 'ui', 'alertsService']);

ContractFinder.config(
    function ($routeProvider) {
        $routeProvider
            .when(
            "/home",
            {
                templateUrl: '/templates/home.html',
                controller: HomeCtrl
            }
        )
            .when(
            "/test1",
            {
                templateUrl: '/templates/test1.html',
                controller: Test1Ctrl
            }
        ).when(
            "/test2",
            {
                templateUrl: '/templates/test2.html',
                controller: Test2Ctrl
            }
        )
            .otherwise(
            {
                redirectTo: "/home"
            }
        );
    }
);


function HomeCtrl($scope) {

};

function Test1Ctrl($scope) {

};

function Test2Ctrl($scope) {

};

function AuthController($scope, $location, $routeParams, $http) {

    $scope.login = function () {
        $scope.authorized = true;
//        $http({method: 'GET', url: 'rest/test'}).

    };

    $scope.osoby = [];

    $scope.loadOsoby = function () {
        $http.get('rest/test1').
            success(function (data, status, headers, config) {
                console.log(status);
                console.log("success");
                console.log(data);
                $scope.osoby = data;
            }).
            error(function (data, status, headers, config) {
                console.log(config);
                console.log("fail");
                $scope.addAlert("error", "fail");
            });
    }

    $scope.logout = function () {
        $scope.authorized = false;
        $location.path("home")
    };

    $scope.authorized = true;

    $scope.isAuthorized = function () {
        return $scope.authorized;
    };
}


