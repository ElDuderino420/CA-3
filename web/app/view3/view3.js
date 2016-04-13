'use strict';

angular.module('myApp.view3', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view3', {
                    templateUrl: 'app/view3/view3.html',
                    controller: 'View3Ctrl'
                });
            }])

        .controller('View3Ctrl', function ($http, $scope) {
            $scope.isFound = false;
            $scope.searchText = null;
            $scope.selectedOption = null;
            $scope.searchOptions = [
                {call: "search", name: "Standard"},
                {call: "vat", name: "CVR Number"},
                {call: "name", name: "Company Name"},
                {call: "produ", name: "Production Unit"},
                {call: "phone", name: "Phone Number"}
            ];
            $scope.selectedCountry = null;
            $scope.country = [
                {call: "dk", name: "DK"},
                {call: "no", name: "NO"}
            ];

            $scope.search = function () {
                $http({
                    method: "GET",
                    url: "http://cvrapi.dk/api?" + $scope.selectedOption + "=" + $scope.searchText + "&country=" + $scope.selectedCountry,
                    skipAuthorization: true,
                    headers: {
                        'User-Agent': "CVR API-CA3 SCHOOL Exercise-David Samuelsen-cph-ds118@cphbusiness.dk"
                    }
                }).then(function successCallback(res) {
                    $scope.foundCompany = res.data;
                    $scope.isFound = true;
                }, function errorCallback(res) {
                    $scope.error = res.status + ": " + res.data.statusText;
                });
            };


        });