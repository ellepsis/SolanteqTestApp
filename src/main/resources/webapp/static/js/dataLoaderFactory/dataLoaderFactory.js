/**
 * Created by Ellepsis on 07.09.2016.
 */

"use strict";
var taxiApp = angular.module('solanteqTestApp');

taxiApp.factory('dataLoaderFactory', ['$location', '$http', function ($location, $http) {

    var dataLoader = {};
    var host = $location.protocol() + '://' + $location.host() + ':' + $location.port() + '/';

    dataLoader.loadPositions = function () {
        return $http.get(host + 'api/getPositions/');
    };
    
    return dataLoader;
}]);
