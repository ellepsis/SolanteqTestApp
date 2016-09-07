/**
 * Created by Ellepsis on 07.09.2016.
 */

"use strict";

angular.module('solanteqTestApp').controller('positionController', ['$scope', 'dataLoaderFactory',
    function ($scope, dataLoaderFactory) {

    $scope.loadPositions = function () {
        dataLoaderFactory.loadPositions($scope.filterParams).success(function (response) {
            $scope.positions = response;
        });
    };

    $scope.addNewPosition = function () {
      dataLoaderFactory.postNewPosition($scope.newPosition).success(function () {
          $scope.loadPositions();
      });
    };

    $scope.loadData = function () {
        $scope.loadPositions();
    }

}]);