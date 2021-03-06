/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 31.03.13
 * Time: 21:23
 */
var ContractFinder = angular.module("ContractFinder", ['ui.bootstrap', 'ui', 'ngCookies', 'alertsService', 'authService']);

ContractFinder.config(
    function ($routeProvider, $httpProvider) {
        $routeProvider
            .when(
            "/",
            {
                redirectTo: '/home'
            }
        ).when(
            "/home",
            {
                templateUrl: '/templates/home.html',
                controller: HomeCtrl
            }
        ).when(
            "/profile/:id",
            {
                templateUrl: '/templates/profile.html',
                controller: ProfileCtrl
            }
        ).when(
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
            "/details/:id",
            {
                templateUrl: '/templates/details.html',
                controller: DetailsContractCtrl
            }
        ).when(
            "/register",
            {
                templateUrl: '/templates/register.html',
                controller: RegisterAccountCtrl
            }
        ).when(
            "/forbidden",
            {
                templateUrl: '/templates/forbidden.html'
            }
        ).when(
            "/admin",
            {
                templateUrl: '/templates/admin.html',
                controller: AdminCtrl
            }
        ).when(
            "/noPage",
            {
                templateUrl: '/templates/noPage.html'
            }
        )
            .otherwise(
            {
                redirectTo: "/noPage"
            }
        ),
            $httpProvider.responseInterceptors.push(function ($q, $location, Alerts, $rootScope) {
                return function (promise) {
                    var handleForbidden = function (response) {
                        $location.path('forbidden');
                        return $q.reject(response);
                    }

                    return promise.then(function (response) {
                        $rootScope.$broadcast("event:ajaxEnd");
                        return response;
                    }, function (response) {
                        $rootScope.$broadcast("event:ajaxEnd");
                        if (response.status === 403) {
                            return handleForbidden(response);
                        }
                        if (response.status === 401) {
                            $rootScope.$broadcast("event:unauthorized");
                            return $q.reject(response);
                        }
                        Alerts.fatal(response.data);
                        return $q.reject(response);
                    });
                }
            }),
            $httpProvider.defaults.transformRequest.push(function (data, headersGetter) {
                var $injector, $rootScope;
                $injector = angular.element('#app').injector();
                $rootScope = $injector.get('$rootScope');
                $rootScope.$broadcast("event:ajaxStart");
                return data;
            });
    }
);
