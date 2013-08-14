function DetailsContractCtrl($scope, $routeParams, $http, $location, Alerts, $rootScope) {

    var contract = {};

    $scope.contract = function () {
        return contract;
    }

    $scope.offer = {};
    $scope.tmpOffer = {};
    $scope.offerAllowed = false;

    $scope.isOfferAllowed = function() {
        return $scope.offerAllowed;
    }

    $scope.getOffer = function() {
        console.log($scope.offer);
        return $scope.offer;
    }

    $scope.initContract = function () {
        $http.get("/rest/contract/details/" + $routeParams.id).
            success(function (data) {
                contract = data;
                console.log(data);
            }).error(function (error) {
                $location.path('noPage');
            });

        $http.get("/rest/offer/contract/" + $routeParams.id).
            success(function (data) {
                console.log(data);
                if(data.allowed == true) {
                    $scope.offer = data.offer;
                }
                $scope.offerAllowed = data.allowed;
            }).error(function (error) {
            });
    }

    $scope.openAddOffer = function () {
        $scope.shouldAddOfferBeOpen = true;
        $scope.tmpOffer = angular.copy($scope.offer);
    }

    $scope.closeAddOffer = function () {
        $scope.shouldAddOfferBeOpen = false;
        $scope.tmpOffer = angular.copy($scope.offer);
    };

    $scope.addOfferModalOpts = {
        backdropFade: true,
        dialogFade: true
    };

    $scope.createOffer = function() {
        $scope.offer = angular.copy($scope.tmpOffer);
        var contract = new Object();
        contract.id = $routeParams.id;
        $scope.offer.contract = contract;
        $scope.$apply($scope.offer);
        $http.post("rest/offer/create", $scope.offer).
            success(function (data) {
                console.log(data);
                Alerts.clearAlerts();
                Alerts.info('Twoja oferta zostala zapisana');
                $rootScope.$broadcast("event:offerAdded");
                $scope.initContract();
            });
        $scope.shouldAddOfferBeOpen = false;
    }

};
