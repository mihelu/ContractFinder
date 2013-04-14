/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 31.03.13
 * Time: 21:23
 */
var ContractFinder = angular.module("ContractFinder", ['ui.bootstrap', 'ui', 'alertsService','authService']);

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
            "/create",
            {
                templateUrl: '/templates/create.html',
                controller: CreateContractCtrl
            }
        ).when(
            "/browse",
            {
                templateUrl: '/templates/browse.html',
                controller: BrowseContractsCtrl
            }
        ).when(
            "/register",
            {
                templateUrl: '/templates/register.html',
                controller: RegisterAccountCtrl
            }
        )
            .otherwise(
            {
                redirectTo: "/home"
            }
        );
    }
);
