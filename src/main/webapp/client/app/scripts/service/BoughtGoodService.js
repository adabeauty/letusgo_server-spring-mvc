'use strict';
angular.module('letusgoApp').service('BoughtGoodsService', function (localStorageService, $http) {

    this.CartItem = function (id, item, num) {
        return {
            id: id,
            num: num,
            item: item
        };
    };

    this.hasExistGoods = function (name, boughtGoods) {

        var boughtGood;
        if(boughtGoods.length !== 0){
            for (var i = 0; i < boughtGoods.length; i++) {
                if (name === boughtGoods[i].item.name) {
                    boughtGood = boughtGoods[i];
                }
            }
        }
        return boughtGood;
    };

    this.addCartNum= function (item, callback) {

        var currentThis = this;
        $http.get('http://localhost:8080/api/cartItems').success(function(data){
            var boughtGood = currentThis.hasExistGoods (item.name, data);

            if(boughtGood){
                boughtGood.num++;
                $http.put('http://localhost:8080/api/cartItems' + item.id, {'cartItem': data}).success();
            }else{
                $http.post('http://localhost:8080/api/cartItems', {'cartItem': currentThis.CartItem(null, item, 1)}).success();
            }
            callback(data);
        });
    };

    this.cartList = function (className, boughtgoods) {

        return {
            categoryName: className,
            boughtgoods: boughtgoods
        };
    };

    this.getGoodsArray = function(boughtGoods){

        var goodsObject = _.groupBy(boughtGoods, function (num) {
            return num.item.category;
        });
        var goodsArray = _.map(goodsObject);
        return  goodsArray;
    };

    this.generateCartGoods = function(boughtGoods){

        var goodsArray = this.getGoodsArray(boughtGoods);
        var curerntThis = this;
        var cartGoodsArray = [];
        _.forEach(goodsArray, function(every){
            var category = curerntThis.cartList(every[0].item.category.name, every);
            cartGoodsArray.push(category );
        });
        return cartGoodsArray;
    };

    this.getTotalMoney = function (boughtGoods) {

        var totalMoney = 0;
        _.forEach(boughtGoods, function (num) {
            totalMoney += num.num * num.item.price;
        });

        return totalMoney;
    };

    this.getClickCount = function(data){

        var sum = 0;
        _.forEach(data, function (item) {
            sum += item.num;
        });
        return sum;
    };

    this.refreshData = function(callback){
        var currentThis = this;
        $http.get('http://localhost:8080/api/cartItems').success(function(data){
            var result = {
                totalAmount: currentThis.getTotalMoney(data),
                cartGoods: currentThis.generateCartGoods(data),
                totalCount: currentThis.getClickCount(data)
            };
            callback(result);
        });
    };
    this.addClickcount = function (direction, number,callback) {

        var currentThis = this;
        $http.get('http://localhost:8080/api/cartItems').success(function(data){
            var clickCount = currentThis.getClickCount(data);
            callback(clickCount);
        });

    };
    this.decreaseOrDelete = function(boughtGoods, i){
        if (boughtGoods[i].num === 1) {
            boughtGoods[i].num--;
            _.remove(boughtGoods, function (boughtGood) {
                return boughtGood.num === 0;
            });
        } else {
            boughtGoods[i].num--;
        }
        $http.post('http://localhost:8080/api/cartItems', {'cartItem': boughtGoods}).success();
        return boughtGoods;
    };

    this.processNum = function (boughtGoods, direction, i) {

        if (direction === 1) {
            boughtGoods[i].num++;
            $http.put('http://localhost:8080/api/cartItems/' + boughtGoods[i].item.Id, {'cartItem': boughtGoods[i]});
        } else {
            this.decreaseOrDelete(boughtGoods, i);
        }

    };
    this.modifyCartItemNum = function (cartItem, direction, callback) {

        var currentThis = this;
        $http.get('http://localhost:8080/api/cartItems').success(function(boughtGoods){
            _.forEach(boughtGoods, function(every, index){
                if (every.item.name === cartItem.item.name) {
                    currentThis.processNum(boughtGoods, direction, index);
                }
                callback();
            });
        });
    };

    this.deleteItem = function (cartItem) {
        $http.delete('http://localhost:8080/api/cartItems/' + cartItem.item.Id).success(function(){});
    };

    this.clearDate = function () {
        $http.post('http://localhost:8080/api/payment');
    };

});