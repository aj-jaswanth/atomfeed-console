var atomFeedConsoleApp = angular.module('atomFeedConsole');

atomFeedConsoleApp.controller('FailedEventCtrl',['$scope', '$http', '$routeParams','FailedEventService', '$window',
 function($scope, $http, $routeParams, FailedEventService, $window){
    console.log('failedEventCtrl')

    FailedEventService.getFailedEventData($routeParams.appName,$routeParams.feedUri).then(function(response){
        $scope.results = response.data;
        $scope.appName = $routeParams.appName

    })

    $scope.resetRetryCount = function(result) {
        console.log('/apps/'+$scope.appName+'/failedEvent/'+result.event.id+'/resetRetryCount')
        $http({
            method: 'POST',
            url: '/apps/'+$scope.appName+'/failedEvent/'+result.event.id+'/resetRetryCount'
        }).
        success(function(response){
            $window.location.reload();
            console.log("success")
        })
    }

    $scope.dateFunction = function(result) {
                console.log("in date function")
                var timestamp = result.failedAt
                date = new Date(timestamp )
                return (date.toLocaleDateString()+" "+date.toLocaleTimeString())
            }

}])

