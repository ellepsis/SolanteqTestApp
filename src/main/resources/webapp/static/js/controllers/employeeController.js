/**
 * Created by Ellepsis on 07.09.2016.
 */
"use strict";

angular.module('solanteqTestApp').controller('employeeController', ['$scope', 'dataLoaderFactory',
    function ($scope, dataLoaderFactory) {

    $scope.loadEmployees = function () {
        dataLoaderFactory.loadEmployees().success(function (response) {
            $scope.employees = response;
        });
    };

    $scope.loadPositions = function () {
        dataLoaderFactory.loadPositions().success(function (response) {
            $scope.positions = response;
        });
    };

    $scope.loadData = function () {
        $scope.loadEmployees();
        $scope.loadPositions();
    }
}]);
