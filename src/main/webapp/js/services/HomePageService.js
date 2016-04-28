var atomFeedConsoleApp = angular.module('atomFeedConsole');

atomFeedConsoleApp.service('HomePageService',['$http', function($http){
    var getAppDetails = function(){
        return $http.get('/apps')
    };

    return{
        getAppDetails : getAppDetails
    }
}])
