var atomFeedConsoleApp = angular.module('atomFeedConsole',[
    'ngRoute'
]);

atomFeedConsoleApp.config(['$routeProvider','$httpProvider', function($routeProvider,$httpProvider){
    $routeProvider
        .when('/apps',{
            templateUrl: 'views/homepage.html',
            controller: 'HomePageCtrl'
        })
        .when('/apps/:appName/feedStatus',{
            templateUrl: 'views/FeedStatus.html',
            controller: 'FeedStatusCtrl'
        })
        .otherwise({
            redirectTo: '/apps'
        })

}]);
