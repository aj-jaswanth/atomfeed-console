var atomFeedConsoleApp = angular.module('atomFeedConsole');

atomFeedConsoleApp.controller('RetryLogCtrl',['$scope', '$routeParams','RetryLogService',
 function($scope, $routeParams, RetryLogService){
    console.log('RetryLogCtrl')
    RetryLogService.getRetryLogData($routeParams.appName,$routeParams.eventId).then(function(response){
        $scope.results = response.data;
        $scope.appName = $routeParams.appName
        console.log(response.data)

//        $scope.submitForm = function(){
//             method  : 'POST',
//             url     : '/apps/appName/failedEvent/result.event.id/resetRetryCount',
//        }

    });

}])

