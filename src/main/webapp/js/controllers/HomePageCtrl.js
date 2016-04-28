var atomFeedConsoleApp = angular.module('atomFeedConsole');

atomFeedConsoleApp.controller('HomePageCtrl',['$scope','HomePageService',
 function($scope, HomePageService){
    HomePageService.getAppDetails().then(function(response){
        $scope.results = response.data;
        console.log(response.data[0].appName)
    });

}])

