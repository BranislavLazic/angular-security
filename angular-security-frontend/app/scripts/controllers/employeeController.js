(function() {

    'use strict';
    
    angular.module('employeeManagerApp').controller('EmployeeController', ['$scope','employeeService', function ($scope, employeeService) {
        initEmployee();

        employeeService.findAll.success(function (data) {
            $scope.employees = data;
        });

        $scope.saveEmployee = function () {
            employeeService.save($scope.employee);
            initEmployee();
        };

        function initEmployee() {
            $scope.employee = {
                firstName: null,
                lastName: null,
                age: null
            };
        }

    }]);
    
})();


