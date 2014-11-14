'use strict';

angular.module('letusgoApp')
    .controller('ShopCtrl', function ($scope, BoughtGoodsService, $http) {

        $scope.$emit('to-parent-navigator-inshop');
        $scope.$emit('to-parent-changeClickCount', 1, 0);

        $http.get('http://localhost:8080/api/items').success(function(goods){
            $scope.allItems = goods;
        });

        $scope.addCartNum = function (item) {

            BoughtGoodsService.addCartNum(item, function(){
                $scope.$emit('to-parent-changeClickCount', 1, 1);
            });
        };

    });
