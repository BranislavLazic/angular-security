(function() {
    'use strict';

    angular.module('employeeManagerApp')
        .controller('LoginController', ['$scope', '$rootScope', 'loginService', '$http', 'store', '$location', function ($scope, $rootScope, loginService, $http, store, $location) {
            $scope.user = {
                username: null,
                password: null
            };

            $scope.login = function () {
                loginService.login($scope.user.username, $scope.user.password).success(function (result, status, headers, config) {
                    // Saves token to local storage and redirects to "employees" page
                    store.set('access_token', result.access_token);
                    $location.path('/employees');
                });
            };

    } ]);

})();
