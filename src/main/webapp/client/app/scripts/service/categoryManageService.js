angular.module('letusgoApp').service('CategoryService', function (localStorageService, $http) {

    this.generateCategory = function(id, name, num){
        return {
            id: id,
            name: name,
            num: num
        }
    };

    this.nameHadExist = function (categories, currentName) {

        var nameExist = _.findIndex(categories, {name: currentName});
        return nameExist;
    };

    this.saveNewCategory = function (categoryName, callback) {
        var currentThis = this;
        $http.get('http://localhost:8080/api/categories').success(function(categories){
            var nameHadExist = currentThis.nameHadExist(categories, categoryName);
            if (!categoryName) {
                callback([true, false]);
                return false;
            }
            if (nameHadExist !== -1) {
                callback([false, true]);
                return false;
            }else{
                $http.post('http://localhost:8080/api/categories', {id: null, name: categoryName, num:0});
                callback([false, false]);
                return true;
            }
        });
    };

    this.updateCategory = function (callback) {
        var updateObeject = localStorageService.get('updateCategory');
        $http.put('http://localhost:8080/api/categories/' + updateObeject.id, {id: updateObeject.id, name: updateObeject.name, num: updateObeject.num}).success(function(){});
        callback();
    };

    this.canDelete = function (categories, object) {

        if (object.num != 0) {
            return false;
        } else {
            $http.delete('http://localhost:8080/api/categories/' + object.id).success(function(){});
            return true;
        }
    };

});
