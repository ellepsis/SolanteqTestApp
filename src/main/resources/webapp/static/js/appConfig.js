/**
 * Created by Ellepsis on 07.09.2016.
 */

"use strict";
var solanteqTestApp = angular.module('solanteqTestApp', ['ngRoute']);

solanteqTestApp.config(function ($routeProvider) {

    $routeProvider.when('/employees', {
        templateUrl: "htmlForms/employeeForm.html",
        controller: "employeeController"
    });

    $routeProvider.when('/positions', {
        templateUrl: "htmlForms/positionTableForm.html",
        controller: "positionController"
    });

});