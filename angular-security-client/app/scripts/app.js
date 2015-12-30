(function() {
    
    'use strict';

    angular
    .module('employeeManagerApp', [
        'ngAnimate',
        'ngCookies',
        'ngResource',
        'ngRoute',
        'ngSanitize',
        'ngTouch',
        'angular-storage',
        'angular-jwt',
        'ui.router'
    ])
    .config(function ($routeProvider, $httpProvider) {
        $routeProvider
        .when('/', {
            templateUrl: 'views/main.html',
            controller: 'MainController',
            controllerAs: 'main'
        })
        .when('/login', {
            templateUrl: 'views/login.html',
            controller: 'LoginController'
        })
        .when('/employees', {
            templateUrl: 'views/employees.html',
            controller: 'EmployeeController',
            auth: true
        })
        .when('/logout', {
            template: ' ',
            controller: ['$scope','$location', 'store', '$rootScope', function ($scope, $location, store, $rootScope) {
                store.remove('jwt_token');
                $rootScope.loggedIn = false;
                $location.path('/login');
            }]
        })
        .otherwise({
            redirectTo: '/'
        });
        // Interecepts every request sent from client application and stores token to Authorization header
        $httpProvider.interceptors.push('AuthenticationHttpInterceptor');

    }).service('AuthenticationHttpInterceptor', ['store','$rootScope', function(store, $rootScope) {
        this.request = function(config) {

	    if(store.get('jwt_token')) {

                config.headers.Authorization = store.get('jwt_token');
                $rootScope.loggedIn = true;
            }
            return config;
        };
    }]).run(function (store, $rootScope, $location, $route) {
        $rootScope.$on('$locationChangeStart', function (e, next, current) {
            var nextPath = $location.path();
            var nextRoute = $route.routes[nextPath];
            if(nextRoute && nextRoute.auth && !store.get('jwt_token')) {
                e.preventDefault();
                $location.path('/login');
            }
        });
    });

})();
