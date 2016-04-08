var atomFeedConsoleApp = angular.module('atomFeedConsole');

atomFeedConsoleApp.service('FeedStatusService',['$http', function($http){
    var getFeedData = function(appName){
        return $http.get('/apps/'+appName+'/feedStatus')
    };

    return{
        getFeedData : getFeedData
    }
}])
