var atomFeedConsoleApp = angular.module('atomFeedConsole');

atomFeedConsoleApp.controller('FailedEventCtrl',['$scope', '$routeParams',
 function($scope, $routeParams){
    console.log('FailedEventCtrl', $routeParams)
}])