(function() {
  
  'use strict';

  angular.module('employeeManagerApp')
    .controller('MainController',['$scope', function ($scope) {
      $scope.title = 'Welcome to employee manager!';
    }]);

})();
