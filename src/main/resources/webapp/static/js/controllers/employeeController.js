/**
 * Created by Ellepsis on 07.09.2016.
 */
"use strict";

angular.module('solanteqTestApp').controller('employeeController', ['$scope', '$routeParams', 'alertFactory', 'dataLoaderFactory',
    function ($scope, $routeParams, alertFactory, dataLoaderFactory) {

        $scope.totalPageCount = 1;
        $scope.filterParams = {rowCount: 10, pageNumber: 1};
        $scope.tableRow = 1;
        $scope.employee = {};

        $scope.addNewEmployee = function () {
            if (!checkMinimalDate($scope.employee.birthdayDate)) {
                alertFactory.errorAlert('Не возможно добавить/обновить: Дата не может быть меньше 1902-ого года');
                return;
            }
            dataLoaderFactory.postEmployee($scope.employee).success(function () {
                alertFactory.successAlert('Успешно добавленно/обновленно');
            }).error(function (response) {
                alertFactory.errorAlert('Не возможно добавить/обновить: ' + response.message);
            });
        };

        $scope.loadEmployees = function () {
            if (($scope.filterParams.startDate != undefined && !checkMinimalDate($scope.filterParams.startDate)) ||
                ($scope.filterParams.endDate != undefined && !checkMinimalDate($scope.filterParams.endDate))) {
                alertFactory.errorAlert('Не возможно выполнить поиск: Дата не может быть меньше 1902-ого года');
                return;
            }
            dataLoaderFactory.loadEmployees($scope.filterParams).success(function (response) {
                $scope.employees = response.list;
                $scope.totalPageCount = Math.ceil(response.totalCount / $scope.filterParams.rowCount);
                $scope.isHavePages();
            });
        };

        $scope.loadEmployee = function (employeeId) {
            dataLoaderFactory.loadEmployee(employeeId).success(function (response) {
                $scope.employee = response;
                $scope.employee.birthdayDate = getFormattedDate(new Date(response.birthdayDate));
            }).error(function (response) {
                alertFactory.errorAlert('Не возможно получить данные с сервера:' + response.message);
            });
        };

        $scope.loadPositions = function () {
            dataLoaderFactory.loadPositions().success(function (response) {
                $scope.positions = response;
            });
        };

        $scope.isHavePages = function () {
            $scope.isHaveNextPage = $scope.filterParams.pageNumber < $scope.totalPageCount;
            $scope.isHavePreviousPage = $scope.filterParams.pageNumber > 1;
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

        $scope.loadEditData = function () {
            if ($routeParams.employeeId !== undefined)
                $scope.loadEmployee($routeParams.employeeId);
            $scope.loadPositions();
        };

        $scope.loadData = function () {
            $scope.loadEmployees();
            $scope.loadPositions();
        };

        function getFormattedDate(date) {
            var year = date.getFullYear();
            var month = date.getMonth();
            var day = date.getDate();
            return new Date(year, month, day);
        }

        function checkMinimalDate(date) {
            if (date == null) return false;
            return date >= new Date("01-01-1902");
        }

    }]);
