angular.module('letusgoApp').service('CategoryService', function (localStorageService, $http) {

    this.generateCategory = function(id, name, num){
        return {
            id: id,
            name: name,
            num : num
        }
    };

    this.nameHadExist = function (categories, currentName) {

        var nameExist = _.findIndex(categories, {name: currentName});
        return nameExist;
    };

    this.saveNewCategory = function (currentName, callback) {
        var currentThis = this;
        $http.get('/api/categories').success(function(categories){
            var nameHadExist = currentThis.nameHadExist(categories, currentName);
            if (!currentName) {
                callback([true, false]);
                return false;
            }
            if (nameHadExist !== -1) {
                callback([false, true]);
                return false;
            }else{
                var newCategory = currentThis.generateCategory(null, currentName, 0);
                $http.post('/api/categories', {'category': newCategory});
                callback([false, false]);
                return true;
            }
        });
    };

    this.updateCategory = function (callback) {
        var updateObeject = localStorageService.get('updateCategory');
        $http.put('/api/categories/' + updateObeject.ID, {'category': updateObeject}).success(function(){});
        callback();
    };

    this.canDelete = function (categories, object) {

        if (object.num != 0) {
            return false;
        } else {
            $http.delete('/api/categories/' + object.ID).success(function(){});
            return true;
        }
    };

});
