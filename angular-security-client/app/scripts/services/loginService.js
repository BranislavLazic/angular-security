(function() {

    'use strict';

    angular.module('employeeManagerApp').factory('loginService', ['$http', '$httpParamSerializer', 'API_BASE',
    function ($http, $httpParamSerializer, API_BASE) {
        return {
            login : function(uname, pwd) {
              var data = {
                  grant_type:"password",
                  username: uname,
                  password: pwd,
                  client_id: "clientIdPassword"
              };
              var request = {
                 method: 'POST',
                 url: API_BASE + '/oauth/token',
                 headers: {
                     "Authorization": "Basic " + btoa("clientIdPassword:secret"),
                     "Content-type": "application/x-www-form-urlencoded; charset=utf-8"
                 },
                 data: $httpParamSerializer(data)
               };
               return $http(request);
            }
        };
    }]);

})();
