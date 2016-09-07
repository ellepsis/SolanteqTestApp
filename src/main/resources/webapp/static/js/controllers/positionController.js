/**
 * Created by Ellepsis on 07.09.2016.
 */

"use strict";

angular.module('solanteqTestApp').controller('positionController', ['$scope', 'dataLoaderFactory',
    function ($scope, dataLoaderFactory) {

    $scope.loadPositions = function () {
        dataLoaderFactory.loadPositions().success(function (response) {
            $scope.positions = response;
        });
    };

    $scope.loadData = function () {
        $scope.loadPositions();
    }

}]);