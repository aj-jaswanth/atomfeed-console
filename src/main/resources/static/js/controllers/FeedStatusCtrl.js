var atomFeedConsoleApp = angular.module('atomFeedConsole');

atomFeedConsoleApp.controller('FeedStatusCtrl',['$scope', '$routeParams','FeedStatusService',
 function($scope, $routeParams, FeedStatusService){
    FeedStatusService.getFeedData($routeParams.appName).then(function(response){
        $scope.results = response.data;
        $scope.appName = $routeParams.appName
    });

    $scope.checkDisableStatus = function(result){
        if(result.countOfFailedEvents == 0){
            return true;
        }
        return false;
    }

}])

