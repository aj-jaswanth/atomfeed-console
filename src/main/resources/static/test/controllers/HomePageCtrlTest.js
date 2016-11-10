describe('HomePageCtrl', function() {

    var scope, ctrl, $httpBackend;

    var results = [
                    {
                      "appName": "App1",
                      "dbUrl": "jdbc:mysql://172.18.2.6/openmrs"
                    },
                    {
                      "appName": "App2",
                      "dbUrl": "jdbc:mysql://172.18.2.6/openmrs"
                    },
                    {
                      "appName": "App3",
                      "dbUrl": "jdbc:mysql://172.18.2.6/openmrs"
                    },
                    {
                      "appName": "App4",
                      "dbUrl": "jdbc:mysql://172.18.2.6/openmrs"
                    },
                    {
                      "appName": "App5",
                      "dbUrl": "jdbc:mysql://172.18.2.6/openmrs"
                    }
                  ];

    beforeEach(function () {
        module('atomFeedConsole');
        inject(function (_$httpBackend_, $rootScope, $controller) {
            $httpBackend = _$httpBackend_;
            $httpBackend.expectGET('apps').
                  respond(results);

            scope = $rootScope.$new();
            ctrl = $controller('HomePageCtrl', {$scope: scope});
        });
    });

    it("it should return 5 app result", function(){
        $httpBackend.flush();
        expect(scope.results.length).toEqual(5)
        expect(scope.results[0].appName).toEqual(results[0].appName);
    });

});