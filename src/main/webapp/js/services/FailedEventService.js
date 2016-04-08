var atomFeedConsoleApp = angular.module('atomFeedConsole');

atomFeedConsoleApp.service('FailedEventService',['$http', function($http){
    var getFailedEventData = function(appName,feedUri){
        return $http.get('/apps/'+appName+'/failedEvent?feedUri='+feedUri)
    };

    return{
        getFailedEventData : getFailedEventData
    }
}])
