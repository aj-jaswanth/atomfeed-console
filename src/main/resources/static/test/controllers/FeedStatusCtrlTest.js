describe('FeedStatusCtrl', function() {

    var scope, ctrl, $httpBackend,routeParams;

    var results =[
                   {
                     "feedUri": "http://172.18.2.6:8052/openelis/ws/feed/patient/recent",
                     "lastReadEntry": "tag:atomfeed.ict4h.org:85332fc9-87bf-427b-9a90-05cb865404f6",
                     "countOfFailedEvents": 0
                   },
                   {
                     "feedUri": "http://localhost:8080/openerp-atomfeed-service/feed/sale_order/recent",
                     "lastReadEntry": "tag:atomfeed.ict4h.org:b0fb937a-d086-4b1f-9e05-db8f9c05c268",
                     "countOfFailedEvents": 0
                   },
                   {
                     "feedUri": "www.google.com",
                     "lastReadEntry": "adsfadsf",
                     "countOfFailedEvents": 1
                   }
                 ]

    beforeEach(function () {
        module('atomFeedConsole');
        inject(function (_$httpBackend_, $rootScope, $controller,$routeParams) {
            $httpBackend = _$httpBackend_;
            $httpBackend.expectGET('apps/'+$routeParams.appName+'/feedStatus').
                  respond(results);

            scope = $rootScope.$new();
            ctrl = $controller('FeedStatusCtrl', {$scope: scope});
        });
    });

    it("it should return 3 feeds", function(){
        $httpBackend.flush();
        expect(scope.results.length).toEqual(3)
        expect(scope.results[0].appName).toEqual(results[0].appName);
        if(scope.results[0].countOfFailedEvents.not){
            expect(element(by.id('view button')).isEnabled()).toBe(false);
        }
    });

});