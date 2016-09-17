(function() {

    'use strict';

    angular.module('employeeManagerApp').factory('employeeService', ['$http', 'API_BASE', function($http, API_BASE) {
        return {
            findAll: function(callback) {
                    return $http.get(API_BASE + '/api/admin/employees').success(callback);
            },
            save: function (employee) {
                    return $http.post(API_BASE + '/api/admin/employees', { firstName: employee.firstName, lastName: employee.lastName, age: employee.age });
            }
        };
    }]);

})();
