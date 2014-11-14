describe('test categoryManage:', function () {

    beforeEach(module('letusgoApp'));
    var $scope, $http, $httpBackend, CategoryService, localStorageService, $controller, creatCategoryCtrl;
    beforeEach(inject(function ($injector) {

        $scope = $injector.get('$rootScope').$new();
        $http = $injector.get('$http');
        $httpBackend = $injector.get('$httpBackend');
        CategoryService = $injector.get('CategoryService');
        localStorageService = $injector.get('localStorageService');

        $controller = $injector.get('$controller');
        creatCategoryCtrl = function () {
            return $controller('CategoryCtrl', {
                $scope: $scope,
                CategoryService: CategoryService,
                localStorageService: localStorageService,
                $http: $http
            });
        }
    }));

    describe('outside function', function () {
        beforeEach(function () {
            spyOn($scope, '$emit');
            $httpBackend.when('GET', '/api/categories').respond([{}, {}, {}]);
            creatCategoryCtrl();
        });

        it('should work', function () {
            expect($scope.$emit).toHaveBeenCalledWith('to-parent-navigator-incategoryManage');
            expect($scope.$emit).toHaveBeenCalledWith('to-parent-changeClickCount', 1, 0);
            $httpBackend.expectGET('/api/categories');
            $httpBackend.flush();

        });
    });

    describe('test editButton()', function () {
        var updateObject = {ID: 'TF1001', name: '饮料类', num: '1'};;
        beforeEach(function () {
            creatCategoryCtrl();
            spyOn(localStorageService, 'set');
            $scope.editButton(updateObject );
        });
        it('editButton is ok', function () {
            expect(localStorageService.set).toHaveBeenCalledWith('updateCategory', updateObject);
        });
    });

    describe('test deleteButton()', function () {
        var cannotDeleteCategory = {ID: 'TF1001', name: '饮料类', num: '1'};
        var canDeleteCategory = {ID: 'TF1001', name: '饮料类', num: '0'};
        beforeEach(function () {
            creatCategoryCtrl();
        });
        it('deleteButton is ok', function () {
            spyOn(CategoryService, 'canDelete').and.returnValue(false);
            $scope.deleteButton(cannotDeleteCategory);

            expect($scope.cannotDeleteError).toEqual(true);
            expect(CategoryService.canDelete).toHaveBeenCalledWith($scope.category, cannotDeleteCategory);
        });
        it('deleteButton can not delete category with number', function () {
            spyOn(CategoryService, 'canDelete').and.returnValue(true);
            $scope.deleteButton(canDeleteCategory);

            expect($scope.cannotDeleteError).toEqual(false);
            expect(CategoryService.canDelete).toHaveBeenCalledWith($scope.category, canDeleteCategory);
        });
    });


});
