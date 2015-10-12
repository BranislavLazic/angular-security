angular.module('employeeManagerApp')
    .controller('LoginController', ['$scope', '$rootScope', 'loginService', '$http', 'store', '$location', function ($scope, $rootScope, loginService, $http, store, $location) {
        $scope.user = {
            username: null,
            password: null
        };

        $scope.login = function () {
            loginService.login($scope.user.username, $scope.user.password).then(function(token) {
                $scope.token = token;
                // Saves token to local storage and redirects to "employees" page
                store.set('jwt', token);
                $location.path('/employees');
            });
        };

} ]);
