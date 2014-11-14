'use strict';

angular.module('letusgoApp')
    .controller('CategoryCtrl', function ($scope, CategoryService, localStorageService, $http) {

        function getCategories(){
            $http.get('http://localhost:8080/api/categories').success(function(data){
                $scope.category = data;
            });
        }

        $scope.$emit('to-parent-navigator-incategoryManage');
        $scope.$emit('to-parent-changeClickCount', 1, 0);
        getCategories();

        $scope.editButton = function (updateCategory) {

            localStorageService.set('updateCategory', updateCategory);
        };

        $scope.deleteButton = function (every) {
            $scope.cannotDeleteError = false;
            if(!CategoryService.canDelete($scope.category, every)){
                $scope.cannotDeleteError = true;
            }

            getCategories();
        };
    });
