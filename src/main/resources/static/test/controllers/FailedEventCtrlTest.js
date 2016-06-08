describe('FeedStatusCtrl', function() {

    var scope, ctrl, $httpBackend,routeParams;

    var results =[{"failedAt":1461922584000,"event":{"categories":[],"id":"abcd","content":"","feedUri":null,"updatedDate":null,"title":"","dateCreated":null},"errorMessage":"error message","feedUri":"www.google.com","retries":0,"eventId":"abcd"}]


    beforeEach(function () {
        module('atomFeedConsole');
        inject(function (_$httpBackend_, $rootScope, $controller,$routeParams) {
            $httpBackend = _$httpBackend_;
            $httpBackend.expectGET('/apps/'+$routeParams.appName+'/failedEvent?feedUri='+encodeURIComponent($routeParams.feedUri)).
                  respond(results);

            scope = $rootScope.$new();
            ctrl = $controller('FailedEventCtrl', {$scope: scope});
        });
    });

    it("it should return 1 failed event", function(){
        $httpBackend.flush();
        expect(scope.results.length).toEqual(1)
        expect(scope.results[0].event.id).toEqual(results[0].event.id);
    });
});