/**
 * Created by Ellepsis on 07.09.2016.
 */
"use strict";

angular.module('solanteqTestApp').controller('employeeController', ['$scope', 'dataLoaderFactory',
    function ($scope, dataLoaderFactory) {

        $scope.totalPageCount = 1;
        $scope.filterParams = {rowCount: 10, pageNumber: 1};
        $scope.tableRow = 1;


        $scope.loadEmployees = function () {
            dataLoaderFactory.loadEmployees($scope.filterParams).success(function (response) {
                $scope.employees = response.list;
                $scope.totalPageCount = Math.ceil(response.totalCount / $scope.filterParams.rowCount);
            });
        };

        $scope.loadPositions = function () {
            dataLoaderFactory.loadPositions().success(function (response) {
                $scope.positions = response;
            });
        };

        $scope.getNextPage = function () {
            if ($scope.filterParams.pageNumber < $scope.totalPageCount) {
                $scope.filterParams.pageNumber++;
                $scope.loadEmployees();
            }
        };

        $scope.getPreviousPage = function () {
            if ($scope.filterParams.pageNumber > 1) {
                $scope.filterParams.pageNumber--;
                $scope.loadEmployees();
            }
        };

        $scope.loadData = function () {
            $scope.loadEmployees();
            $scope.loadPositions();
        }
    }]);
