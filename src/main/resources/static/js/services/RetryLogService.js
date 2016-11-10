var atomFeedConsoleApp = angular.module('atomFeedConsole');

atomFeedConsoleApp.service('RetryLogService',['$http', function($http){
    var getRetryLogData = function(appName,eventId){
        return $http.get('apps/'+appName+'/failedEvent/'+eventId+'/retryLog')
    };

    return{
        getRetryLogData : getRetryLogData
    }
}])

