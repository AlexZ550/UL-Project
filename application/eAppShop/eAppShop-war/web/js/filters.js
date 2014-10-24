'use strict';

/* Filters */

var shopFilters = angular.module('shopFilters', []);
        
        
shopFilters.filter('checkmark', function() {
  return function(input) {
    return input ? '\u2713' : '\u2718';
  };
});

