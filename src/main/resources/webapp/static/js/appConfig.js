/**
 * Created by Ellepsis on 07.09.2016.
 */

"use strict";
var solanteqTestApp = angular.module('solanteqTestApp');

solanteqTestApp.config(function ($routeProvider) {

    $routeProvider.when('/employees', {
        templateUrl: "htmlForms/employeesTableForm.html",
        controller: "employeeController"
    });

    $routeProvider.when('/positions', {
        templateUrl: "htmlForms/positionTableForm.html",
        controller: "positionController"
    });

    $routeProvider.when('/employees/addNew/', {
        templateUrl: "htmlForms/employeeInputForm.html",
        controller: "positionController"
    });

    $routeProvider.when('/employees/changeInfo/:employeeId', {
        templateUrl: "htmlForms/employeeInputForm.html",
        controller: "positionController"
    })

});