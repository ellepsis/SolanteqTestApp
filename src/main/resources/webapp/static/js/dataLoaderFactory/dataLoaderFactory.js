/**
 * Created by Ellepsis on 07.09.2016.
 */

"use strict";
var taxiApp = angular.module('solanteqTestApp');

taxiApp.factory('dataLoaderFactory', ['$location', '$http', function ($location, $http) {

    var dataLoader = {};
    var host = $location.protocol() + '://' + $location.host() + ':' + $location.port() + '/';

    dataLoader.loadPositions = function () {
        return $http.get(host + 'api/position/getPositions/');
    };

    dataLoader.postNewPosition = function (position) {
        return $http({
            url: host + 'api/position/addNewPosition/',
            method: "POST",
            data: angular.toJson(position),
            headers: {'Content-Type': 'application/json'}
        });
    };

    dataLoader.loadEmployees = function () {
        return $http.get(host + 'api/employee/getEmployees');
    };
    
    return dataLoader;
}]);
