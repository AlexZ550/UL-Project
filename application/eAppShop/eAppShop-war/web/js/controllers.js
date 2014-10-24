'use strict';

/* Controllers */

var shopControllers = angular.module('shopControllers', []);

shopControllers.controller('ProductListCtrl', ['$scope', 'growl', 'ProductList', 'Product', 'CartList', 'Cart',
  function($scope, growl, ProductList, Product, CartList, Cart) {

    $scope.products = ProductList.query();

    $scope.products.$promise.then(function () {
        CartList.query(function(inCart){
            $scope.productsInCart = 0;
            $.each(inCart, function( indexInCart, productInCart ) {
                $.each($scope.products, function( index, product ) {
                    if (product.productid === productInCart.productid)
                    {
                        product.isOutOfStock = productInCart.qty > product.qty;
                        product.cart = productInCart.qty;
                        $scope.productsInCart += product.cart;
                    };
                });
            });
        });
    });

    $scope.product = {'description': '', 'qty': 0, 'price': 0 };
    
    
    $scope.updateQtyInCart = function(product, qty){

        if(product.hasOwnProperty('cart'))
            product.cart += qty ;
        else
            product.cart = 1;
        
        if (product.cart > product.qty) 
            product.cart = product.qty;
        else     
            $scope.productsInCart += qty;
           
        var bookedProduct = jQuery.extend(true, {}, product);     
        bookedProduct.qty = product.cart;
            
        Cart.update(bookedProduct);
    };
    
    $scope.removeProductFromCart = function(product) {
        product.isOutOfStock = false;
        $scope.productsInCart -= product.cart;
        product.cart = 0;
        var removedProduct = jQuery.extend(true, {}, product);     
        removedProduct.qty = product.cart;
        Cart.update(removedProduct);        
        growl.addInfoMessage("Product " + removedProduct.description + " removed from Shopping Cart");
    };
    
    $scope.removeProduct = function(product){
        Product.remove(product);
        var index =  $scope.products.indexOf(product);
        $scope.products.splice(index, 1);
        growl.addInfoMessage("Product " + product.description + " deleted");
    };
    
    $scope.addProduct = function(product){
        if ($scope.productform.$valid)
        {
            Product.create(product).$promise.then(function() {
                ProductList.query(function (products) {
                    $scope.products.push(products[$scope.products.length]);
                    growl.addInfoMessage("Product " + product.description + " created");
                    $scope.product = {'description': '', 'qty': 0, 'price': 0 };
                });
            });
        }
    };
    
    $scope.updateProduct = function(product){
        Product.update(product);
        growl.addInfoMessage("Product " + product.description + " update");
    };
    
    $scope.incQty = function(product){
        product.qty++;
        $scope.updateProduct(product);
    };
    
    $scope.decQty = function(product){
        if (product.qty > 0)
        {
            product.qty--;
            $scope.updateProduct(product);
        }
    };
  }]);


shopControllers.controller('CartListCtrl', ['$scope', 'growl', 'CartList', 'Cart',
  function($scope, growl, CartList, Cart) {
    $scope.products = CartList.query( function (products){
        $.each(products, function( index, product ) {
            product.isOutOfStock = false;
            product.availableQty = 0;
        });
    });
    
    $scope.isCheckedOut = false;
    
    $scope.adjustQty = function(product) {
        product.qty = product.availableQty;
        product.isOutOfStock = false;
        product.availableQty = 0;
        
        var bookedProduct = jQuery.extend(true, {}, product);     
        Cart.update(bookedProduct);        
    };
    
    $scope.removeFromCart = function(product) {
        var index =  $scope.products.indexOf(product);
        $scope.products.splice(index, 1);
        product.qty = 0;
        Cart.update(product);  
    };
    
    $scope.checkOutCart = function(){
        Cart.checkOut().$promise.then(function (productsOutOfStock) {
            console.log(productsOutOfStock);
            $scope.isCheckedOut = productsOutOfStock.length === 0;
                
            $.each($scope.products, function( indexInCart, productInCart ) {
                $.each(productsOutOfStock, function( index, product ) {
                    if (product.productid === productInCart.productid)
                    {
                        productInCart.isOutOfStock = true;
                        productInCart.availableQty = product.qty;
                    };
                });
            });         
            
            if ($scope.isCheckedOut)
                $scope.products = [];
        });
        
        growl.addInfoMessage("Order has been placed");
        
    };

    $scope.cancelCart = function(){
        Cart.cancel().$promise.then(function () {
            $scope.products = CartList.query();
            growl.addInfoMessage("Order has been canceled");
        });
    };   
      
  }]);


shopControllers.controller('ProductDetailCtrl', ['$scope', '$routeParams', 'Product', 'CommentListByProductId', 
  function($scope, $routeParams, Product, CommentListByProductId) {
    $scope.product = Product.get({productId: $routeParams.productId}, function(product) {
    });
    
    $scope.comments = CommentListByProductId.query({productId: $routeParams.productId}, function(e) {
        console.log(e);
    });
    
    $scope.review = {"text":""};
    
    $scope.addComment = function(review){
      review.productid = $scope.product;
      CommentListByProductId.create(review, function(comment) {
        $scope.comments.push(comment);
        console.log(comment);
      });
      $scope.review = {"text":""};;
    };
    
  }]);


shopControllers.controller('UserLogListCtrl', ['$scope', 'LogList', 
  function($scope, LogList) {
    $scope.logs = LogList.query();
  }]);




