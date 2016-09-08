/**
 * Created by Ellepsis on 07.09.2016.
 */

"use strict";

angular.module('solanteqTestApp').controller('positionController', ['$scope', 'dataLoaderFactory', 'alertFactory',
    function ($scope, dataLoaderFactory, alertFactory) {

        $scope.loadPositions = function () {
            dataLoaderFactory.loadPositions($scope.filterParams).success(function (response) {
                $scope.positions = response;
            });
        };

        $scope.addNewPosition = function () {
            dataLoaderFactory.postNewPosition($scope.newPosition).success(function () {
                $scope.loadPositions();
                alertFactory.successAlert('Успешно добавленно/обновленно');
            }).error(function (response) {
                alertFactory.errorAlert('Не возможно добавить/обновить: ' + response.message);
            });
        };

        $scope.loadData = function () {
            $scope.loadPositions();
        }

    }]);