(function() {

    'use strict';
    
    angular.module('employeeManagerApp').controller('EmployeeController', ['$scope','employeeService', function ($scope, employeeService) {
        initEmployee();

        employeeService.findAll.success(function (data) {
            $scope.employees = data;
        });

        $scope.saveEmployee = function () {
            employeeService.save($scope.employee)
                .then(function(response) {
                    if(response.status === 200) {
                        $scope.employees.push(response.config.data);
                    }
                    
                }, function(response) {
                    // Handle error
                    console.log(response);
            });
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


