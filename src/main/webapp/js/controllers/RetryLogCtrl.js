var atomFeedConsoleApp = angular.module('atomFeedConsole');

atomFeedConsoleApp.controller('RetryLogCtrl',['$scope', '$routeParams','RetryLogService',
 function($scope, $routeParams, RetryLogService){
    console.log('RetryLogCtrl')
    RetryLogService.getRetryLogData($routeParams.appName,$routeParams.eventId).then(function(response){
        $scope.results = response.data;
        $scope.appName = $routeParams.appName
    })

    $scope.dateFunction = function(result) {
        console.log("in date function")
        var timestamp = result.failedAt
        date = new Date(timestamp )
        return (date.toLocaleDateString()+" "+date.toLocaleTimeString())
    }

}])

