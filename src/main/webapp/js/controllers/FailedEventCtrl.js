var atomFeedConsoleApp = angular.module('atomFeedConsole');

atomFeedConsoleApp.controller('FailedEventCtrl',['$scope', '$routeParams','FailedEventService',
 function($scope, $routeParams, FailedEventService){
    FailedEventService.getFailedEventData($routeParams.appName,$routeParams.feedUri).then(function(response){
        $scope.results = response.data
    });
}])