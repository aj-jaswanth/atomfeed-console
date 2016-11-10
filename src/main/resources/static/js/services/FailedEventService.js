var atomFeedConsoleApp = angular.module('atomFeedConsole');

atomFeedConsoleApp.service('FailedEventService',['$http', function($http){
    var getFailedEventData = function(appName,feedUri){
    console.log('apps/'+appName+'/failedEvent?feedUri='+encodeURIComponent(feedUri))
        return $http.get('apps/'+appName+'/failedEvent?feedUri='+encodeURIComponent(feedUri))
    };

    return{
        getFailedEventData : getFailedEventData
    }
}])

