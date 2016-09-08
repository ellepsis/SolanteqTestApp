/**
 * Created by Ellepsis on 07.09.2016.
 */

"use strict";
var solanteqTestApp = angular.module('solanteqTestApp');

solanteqTestApp.factory('dataLoaderFactory', ['$location', '$http', function ($location, $http) {

    var dataLoader = {};
    var host = $location.protocol() + '://' + $location.host() + ':' + $location.port() + '/';

    //Copy object to local one
    //If we not make this, user filter values in UI will be changed.
    function convertFilterParams(filterParams) {
        var localFilterParams = jQuery.extend({}, filterParams);
        localFilterParams.pageNumber = (filterParams.pageNumber - 1 < 0 ) ? 0 : filterParams.pageNumber - 1;
        return localFilterParams;
    }

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

    dataLoader.loadEmployees = function (filterParams) {
        var localFilterParams = convertFilterParams(filterParams);
        return $http({
            url: host + 'api/employee/getEmployees',
            method: "get",
            params: localFilterParams
        });
    };

    dataLoader.loadEmployee = function (employeeId) {
        return $http({
            url: host + 'api/employee/getEmployee/',
            method: "get",
            params: {employeeId: employeeId}
        })
    };

    dataLoader.postEmployee = function (employee) {
        return $http({
            url: host + 'api/employee/addNewEmployee/',
            method: "POST",
            data: angular.toJson(employee),
            headers: {'Content-Type': 'application/json'}
        });
    };

    return dataLoader;
}]);
