(function() {

    'use strict';

    angular
    .module('employeeManagerApp', [
        'ngCookies',
        'ngResource',
        'ngRoute',
        'angular-storage',
        'angular-jwt'
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
            controller: ['$scope', 'API_BASE', '$http', '$location', 'store', '$rootScope',
              function ($scope, API_BASE, $http, $location, store, $rootScope) {
                  // Remove token from local storage
                  store.remove('access_token');
                  // Invalidate token on backend side
                  $http.get(API_BASE + '/oauth/revoke-token');
                  $rootScope.loggedIn = false;
                  $location.path('/login');
              }
            ]
        })
        .otherwise({
            redirectTo: '/'
        });
        // Interecepts every request sent from client application and stores token to Authorization header
        $httpProvider.interceptors.push('AuthenticationHttpInterceptor');

    }).service('AuthenticationHttpInterceptor', ['store','$rootScope', function(store, $rootScope) {
        this.request = function(config) {

	    if(store.get('access_token')) {
                config.headers.Authorization = 'Bearer ' + store.get('access_token');
                $rootScope.loggedIn = true;
            }
            return config;
        };
    }]).run(function (store, $rootScope, $location, $route) {
        $rootScope.$on('$locationChangeStart', function (e, next, current) {
            var nextPath = $location.path();
            var nextRoute = $route.routes[nextPath];
            if(nextRoute && nextRoute.auth && !store.get('access_token')) {
                e.preventDefault();
                $location.path('/login');
            }
        });
    });

})();
