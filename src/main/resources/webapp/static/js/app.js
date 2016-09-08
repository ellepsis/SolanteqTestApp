/**
 * Created by Ellepsis on 07.09.2016.
 */
"use strict";

var solanteqTestApp = angular.module("solanteqTestApp", ['ngRoute', 'ngFlash']);

solanteqTestApp.factory('alertFactory', ['Flash', function (Flash) {
    return {
        successAlert: function (message) {
            var message = '<strong> Успешно </strong>' + message;
            Flash.create('success', message);
        },
        errorAlert: function (message) {
            message = '<strong> Ошибка </strong>' + message;
            Flash.create('danger', message);
        }
    };
}]);
