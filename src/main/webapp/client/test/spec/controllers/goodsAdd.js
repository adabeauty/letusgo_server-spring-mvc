describe('test goodsAdd:', function () {
    beforeEach(module('letusgoApp'));
    var $scope, $location, $http, $httpBackend, localStorageService, GoodService, $controller, creatGoodsAddCtrl;
    beforeEach(inject(function ($injector) {

        $scope = $injector.get('$rootScope').$new();
        $location = $injector.get('$location');
        $http = $injector.get('$http');
        $httpBackend = $injector.get('$httpBackend');
        localStorageService = $injector.get('localStorageService');
        GoodService = $injector.get('GoodService');

        $controller = $injector.get('$controller');

        creatGoodsAddCtrl = function () {
            return $controller('GoodsAddCtrl', {
                $scope: $scope,
                $location: $location,
                localStorageService: localStorageService,
                GoodService: GoodService,
                $http: $http
            });
        }
    }));

    describe('outside getAllCategories', function(){
        beforeEach(function(){
            var categoryNames = ['fruits', 'snack'];
            spyOn(GoodService, 'getAllCategories').and.callFake(function(callback){
                callback(categoryNames);
            });
            creatGoodsAddCtrl();
        });
        it('should work', function(){
            expect(GoodService.getAllCategories).toHaveBeenCalled();
            expect($scope.allCategories[0]).toEqual('fruits');
        });

    });

    describe('test saveNewGood', function () {
        beforeEach(function () {
            spyOn(GoodService, 'getAllCategories');

            creatGoodsAddCtrl();
        });

        it('should not skip', function(){
            var result = [false, true];

            spyOn(GoodService, 'saveNewGood').and.callFake(function(itemCategory, itemName, itemPrice, itemUnit, callback){
                callback(result);
            });

            $scope.saveNewGood();

            GoodService.saveNewGood($scope.itemCategory, $scope.itemName, $scope.itemPrice, $scope.itemUnit, function(warning){
                expect($scope.undefinedError).toEqual(false);
                expect($scope.repeatedError).toEqual(true);
            });
            expect(GoodService.saveNewGood).toHaveBeenCalled();
        });

        it('should skip to another view', function () {
            var result = [false, false];

            spyOn(GoodService, 'saveNewGood').and.callFake(function(itemCategory, itemName, itemPrice, itemUnit, callback){
                callback(result);
            });
            spyOn($location, 'path');
            $httpBackend.when('GET', '/api/goods').respond('allItems');

            $scope.saveNewGood();

            GoodService.saveNewGood($scope.itemCategory, $scope.itemName, $scope.itemPrice, $scope.itemUnit, function(warnig){
                expect($scope.undefinedError).toEqual(false);
                expect($scope.repeatedError).toEqual(false);
                expect($location.path).toHaveBeenCalledWith('/goodsManage');
            });
            expect(GoodService.saveNewGood).toHaveBeenCalled();
            $httpBackend.expectGET('/api/goods');
            $httpBackend.flush();
        });
    });

    describe('test cancel', function () {
        beforeEach(function () {
            creatGoodsAddCtrl();
            spyOn($location, 'path');
            $scope.closeAddView();
        });
        it('cancel is ok', function () {
            expect($location.path).toHaveBeenCalledWith('/goodsManage');
        });
    });

});
