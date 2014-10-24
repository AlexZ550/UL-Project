'use strict';

/* Directives */


var shopDirectives = angular.module('shopDirectives', []);

/*
shopDirectives.directive('accessLevel', ['$rootScope', 'Auth', function($rootScope, Auth) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var prevDisp = element.css('display');
            $rootScope.$watch('user.is_admin', function(is_admin) {
                if(!Auth.authorize(attrs.accessLevel))
                    element.css('display', 'none');
                else
                    element.css('display', prevDisp);
            });
        }
    };
}]);
*/


shopDirectives.directive('accessLevel', ['User', function(User) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
                    if (!scope.hasOwnProperty('user'))
                        scope.user = User.get();
                    
                    scope.user.$promise.then(function () {
                        if (!scope.user.is_admin && attrs.accessLevel === 'admin') {
                            element.css('display', 'none');
                        }
                        if (scope.user.is_admin && attrs.accessLevel === 'user') {
                            element.css('display', 'none');
                        }
                    });
            }
        };
    }]);

/*
shopDirectives.directive('format', ['$filter',
  function($filter) {
    return {
      require: '?ngModel',
      link: function(scope, elem, attrs, ctrl) {
        if (!ctrl) return;


        ctrl.$formatters.unshift(function(a) {
          elem[0].value = ctrl.$modelValue;
          elem.priceFormat({
            prefix: '',
            centsSeparator: ',',
            thousandsSeparator: '.'
          });
          return elem[0].value;
        });


        ctrl.$parsers.unshift(function(viewValue) {
          elem.priceFormat({
            prefix: '',
            centsSeparator: ',',
            thousandsSeparator: '.'
          });
          return elem[0].value;
        });
      }
    };
  }
]);

*/