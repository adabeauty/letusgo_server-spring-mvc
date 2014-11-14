'use strict';
angular.module('letusgoApp').service('GoodService', function (localStorageService, $http) {

    this.item = function (category, name, price, unit) {

        return {
            id: null,
            name: name,
            price: price,
            unit: unit,
            category: category
        };
    };

    this.hasExistItem = function (goods, itemName) {

        var exist = _.findIndex(goods, {name: itemName});
        return exist;

    };

    this.saveItem = function (itemCategory, itemName, itemPrice, itemUnit) {
        var currentThis = this;
        $http.get('http://localhost:8080/api/categories').success(function(categories){
            var index = _.findIndex(categories, {name: itemCategory});

            var newItem = currentThis.item(categories[index], itemName, itemPrice, itemUnit);
            $http.post('http://localhost:8080/api/items', {id: newItem.id, name: newItem.name, price: newItem.price,
                unit: newItem.unit, category: newItem.category});
        });

    };

    this.modifyCategoryNum = function (num, itemCategory) {

        $http.get('http://localhost:8080/api/categories').success(function(categories){
            var index = _.findIndex(categories, {name: itemCategory.name});
            categories[index].num = categories[index].num + num;
            $http.put('http://localhost:8080/api/categories/' + categories[index].id, {id: categories[index].id,
                name: categories[index].name, num: categories[index].num}).success();
        });
    };

    this.succeedSave = function(name, itemName, itemPrice, itemUnit){
        this.saveItem(name, itemName, itemPrice, itemUnit);
        this.modifyCategoryNum(1, name);
    };

    this.saveNewGood = function (itemCategory, itemName, itemPrice, itemUnit, callback) {

        var currentThis = this;
        $http.get('http://localhost:8080/api/items').success(function(goods){
            var hasExistItem =  currentThis.hasExistItem(goods, itemName);
            var itemDetailSuccess = itemCategory && itemName && itemPrice && itemUnit;

            if (!itemDetailSuccess) {
                callback([true, false]);
                return false;
            }

            if (hasExistItem !== -1) {
                callback([false, true]);
                return false;
            } else {
                currentThis.succeedSave(itemCategory.name, itemName, itemPrice, itemUnit);
                callback([false, false]);
                return true;
            }
        });
    };

    this.getAllCategories = function (callback) {

        $http.get('http://localhost:8080/api/categories').success(function(categories){
            var allCategories = [];
            _.forEach(categories, function (num) {
                allCategories.push({name: num.name});
            });
            callback(allCategories);
        });
    };

    this.updateItem = function (updateObject) {
        $http.put('http://localhost:8080/api/items/' + updateObject.id, {id: updateObject.id, name: updateObject.name,
        price: updateObject.price, unit: updateObject.unit, category: updateObject.category});
    };

    this.deleteButton = function (item) {
        $http.delete('http://localhost:8080/api/items/' + item.id).success(function(){});
        this.modifyCategoryNum(-1, item.category);
    };

});
