'use strict'

angular.module('employeeManagerApp').factory('loginService', ['$http', 'API_BASE', function ($http, API_BASE) {
    return { login : function(uname, pwd) {
        return $http.post(API_BASE + '/user/login', { username: uname, password: pwd })
            .then(function (response) {
                return response.data.token;
            });
        }
    }
}]);
