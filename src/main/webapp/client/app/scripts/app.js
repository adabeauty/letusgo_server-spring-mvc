'use strict';

/**
 * @ngdoc overview
 * @name letGoApp
 * @description
 * # letGoApp
 *
 * Main module of the application.
 */
angular
    .module('letusgoApp', [
        'ngAnimate',
        'ngCookies',
        'ngResource',
        'ngRoute',
        'ngSanitize',
        'ngTouch',
        'LocalStorageModule'
    ])
    .config(['localStorageServiceProvider', function (localStorageServiceProvider) {
        localStorageServiceProvider.setPrefix('ls');
    }])
    .config(function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'client/app/views/main.html',
                controller: 'MainCtrl'
            })
            .when('/cartList', {
                templateUrl: 'client/app/views/cartList.html',
                controller: 'CartListCtrl'
            })
            .when('/shop', {
                templateUrl: 'client/app/views/shop.html',
                controller: 'ShopCtrl'
            })
            .when('/payList', {
                templateUrl: 'client/app/views/payList.html',
                controller: 'PayListCtrl'
            })
            .when('/categoryManage', {
                templateUrl: 'client/app/views/categoryManage.html',
                controller: 'CategoryCtrl'
            })
            .when('/categoryAdd', {
                templateUrl: 'client/app/views/categoryAdd.html',
                controller: 'CategoryAddCtrl'
            })
            .when('/categoryUpdate', {
                templateUrl: 'client/app/views/categoryUpdate.html',
                controller: 'CategoryUpdateCtrl'
            })
            .when('/goodsManage', {
                templateUrl: 'client/app/views/goodsManage.html',
                controller: 'GoodsCtrl'
            })
            .when('/goodsAdd', {
                templateUrl: 'client/app/views/goodsAdd.html',
                controller: 'GoodsAddCtrl'
            })
            .when('/goodsUpdate', {
                templateUrl: 'client/app/views/goodsUpdate.html',
                controller: 'GoodsUpdateCtrl'
            })
            .otherwise({
                redirectTo: 'client/app/'
            });
    });
