'use strict';

angular.module('myApp.view4', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/view4', {
    templateUrl: 'app/view4/view4.html'
  });
}])

        .controller('View4Ctrl', function ($http, $scope) {
            
            http.get("http://ca-mb1337.rhcloud.com/SemesterSeed/api/Currency/getRates")
    .then(function (response) {$scope.rates = response.data.rates;});

            
            


        });