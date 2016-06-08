var atomFeedConsoleApp = angular.module('atomFeedConsole');

atomFeedConsoleApp.controller('HomePageCtrl',['$scope','HomePageService',
 function($scope, HomePageService){
    HomePageService.getAppDetails().then(function(response){
        console.log(JSON.stringify(response.data))
        $scope.results = response.data;
    });

}])

