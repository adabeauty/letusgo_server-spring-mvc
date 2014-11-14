'use strict';
angular.module('letusgoApp')
    .controller('GoodsAddCtrl', function ($scope, $location, localStorageService, GoodService, $http) {

        GoodService.getAllCategories(function(categoryNames){
            $scope.allCategories = categoryNames;
        });

        $scope.saveNewGood = function () {

            GoodService.saveNewGood($scope.itemCategory, $scope.itemName, $scope.itemPrice, $scope.itemUnit, function(warning){
                $scope.undefinedError = warning[0];
                $scope.repeatedError = warning[1];

                var canSkip = warning[0] === false && warning[1] ===false;
                if(canSkip){
                    $location.path('/goodsManage');
                    $http.get('http://localhost:8080/api/items').success(function(goods){
                        $scope.allItems = goods;
                    });
                }
            });
        };

        $scope.closeAddView = function () {
            $location.path('/goodsManage');
        };
    });
