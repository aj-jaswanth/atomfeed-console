describe('FeedStatusCtrl', function() {

    var scope, ctrl, $httpBackend,routeParams;

    var results =[
                   {
                     "feedUri": "www.google.com",
                     "failedAt": 1461922538000,
                     "errorMessage": "error message",
                     "eventId": "abcd",
                     "eventContent": "somehow it failed"
                   }
                 ]

    beforeEach(function () {
        module('atomFeedConsole');
        inject(function (_$httpBackend_, $rootScope, $controller,$routeParams) {
            $httpBackend = _$httpBackend_;
            $httpBackend.expectGET('/apps/'+$routeParams.appName+'/failedEvent/'+$routeParams.eventId+'/retryLog').
                  respond(results);

            scope = $rootScope.$new();
            ctrl = $controller('RetryLogCtrl', {$scope: scope});
        });
    });

    it("it should return 1 retry log", function(){
        $httpBackend.flush();
        expect(scope.results.length).toEqual(1);
        expect(scope.results[0].eventId).toEqual(results[0].eventId);
    });

});