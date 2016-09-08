/**
 * Created by Ellepsis on 07.09.2016.
 */
"use strict";

angular.module('solanteqTestApp').controller('employeeController', ['$scope', 'alertFactory', 'dataLoaderFactory',
    function ($scope, alertFactory, dataLoaderFactory) {

        $scope.totalPageCount = 1;
        $scope.filterParams = {rowCount: 10, pageNumber: 1};
        $scope.tableRow = 1;

        $scope.addNewEmployee = function () {
            dataLoaderFactory.postEmployee($scope.employee).success(function () {
                alertFactory.successAlert('Успешно добавленно/обновленно');
            }).error(function (response) {
                alertFactory.errorAlert('Не возможно добавить/обновить: ' + response.message);
            });
        };

        $scope.loadEmployees = function () {
            dataLoaderFactory.loadEmployees($scope.filterParams).success(function (response) {
                $scope.employees = response.list;
                $scope.totalPageCount = Math.ceil(response.totalCount / $scope.filterParams.rowCount);
                $scope.isHavePages();
            });
        };

        $scope.loadPositions = function () {
            dataLoaderFactory.loadPositions().success(function (response) {
                $scope.positions = response;
            });
        };

        $scope.isHavePages = function () {
            $scope.isHaveNextPage = $scope.filterParams.pageNumber < $scope.totalPageCount ? true : false;
            $scope.isHavePreviousPage = $scope.filterParams.pageNumber > 1 ? true : false;
        };

        $scope.getNextPage = function () {
            if ($scope.isHaveNextPage) {
                $scope.filterParams.pageNumber++;
                $scope.loadEmployees();
            }
        };

        $scope.getPreviousPage = function () {
            if ($scope.isHavePreviousPage) {
                $scope.filterParams.pageNumber--;
                $scope.loadEmployees();
            }
        };

        $scope.loadData = function () {
            $scope.loadEmployees();
            $scope.loadPositions();
        };
    }]);
