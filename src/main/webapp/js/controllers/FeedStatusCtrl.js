var atomFeedConsoleApp = angular.module('atomFeedConsole');

atomFeedConsoleApp.controller('FeedStatusCtrl',['$scope', '$routeParams','FeedStatusService', '$location',
 function($scope, $routeParams, FeedStatusService, $location){
    FeedStatusService.getFeedData($routeParams.appName).then(function(response){
        $scope.results = response.data;
        $scope.appName = $routeParams.appName
    });


}])

