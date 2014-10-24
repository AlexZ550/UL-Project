'use strict';

/* Services */

var shopServices = angular.module('shopServices', ['ngResource']);

shopServices.factory('Product', ['$resource',
  function($resource){
    return $resource('webresources/product/:productId', {}, {
      query: {method:'GET', isArray:false},
      update: {method: 'PUT', isArray:false},
      create: {method: 'POST'},
      remove: {method: 'DELETE'}
    });
  }]);

shopServices.factory('Auth', 
    function ($rootScope) {
      return {
        authorize: function(accessLevel, is_admin) {
            if(is_admin && accessLevel === "admin")
                return true;
            return false;
        }
      };
    }
);


shopServices.factory('ProductList', ['$resource',
  function($resource){
    return $resource('webresources/product', {}, {
      query: {method:'GET', isArray:true}
    });
  }]);

shopServices.factory('CartList', ['$resource',
  function($resource){
    return $resource('webresources/cart', {}, {
      query: {method:'GET', isArray:true}
    });
  }]);


shopServices.factory('CommentListByProductId', ['$resource',
  function($resource){
    return $resource('webresources/comment/:productId', {}, {
      query: {method:'GET', isArray:true},
      create: {method:'POST'}
    });
  }]);


shopServices.factory('User', ['$resource',
  function($resource){
    return $resource('webresources/user', {}, {
      get: {method:'GET', isArray:false}
    });
  }]);


shopServices.factory('CartList', ['$resource',
  function($resource){
    return $resource('CartServlet', {}, {
      query: {method:'GET', isArray:true}
    });
  }]);

shopServices.factory('LogList', ['$resource',
  function($resource){
    return $resource('webresources/log', {}, {
      query: {method:'GET', isArray:true}
    });
  }]);

shopServices.factory('Cart', ['$resource',
  function($resource){
    return $resource('CartServlet/:productId', {}, {
      update: {method: 'PUT', isArray:false},
      checkOut: {method: 'POST', isArray:true},
      cancel: {method: 'DELETE'}
    });
  }]);



shopServices.factory('sessionTimeoutInterceptor', ['$q', '$location', 'growl', function ($q, $location, growl){ 
                return {
                    response: function(response) {
                        //console.log('sessionTimeoutInterceptor checks response');
                        if (angular.isString(response.data) && response.data.indexOf('Login') != -1)
                          window.location.reload();   
                        return response;
                    },
                    responseError: function(rejection) {
                        if(rejection.status === 401 || rejection.status === 405) {
                            window.location.reload(); 
                        }
                        growl.addErrorMessage(rejection.statusText);
                        return $q.reject(rejection);
                    }
                };
            }])
            .config(['$httpProvider', function($httpProvider) { 
                    $httpProvider.interceptors.push('sessionTimeoutInterceptor'); 
            }]);

