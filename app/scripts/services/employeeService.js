angular.module('employeeManagerApp').factory('employeeService',['$http', 'API_BASE', function($http, API_BASE) {

    return { findAll: $http.get(API_BASE + '/api/employee/all')
    .success(function (data) {
        return data;
    })
    .error(function (error) {
        return error;
    }),
    save: function (employee) {
        $http.post(API_BASE + '/api/employee/save', {firstName: employee.firstName, lastName: employee.lastName, age: employee.age})
        .success(function (data) {
            return data;
        })
        .error(function (error) {
            return error;
        });
    }
}
}]);
