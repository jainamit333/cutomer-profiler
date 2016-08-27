var neoApp = angular.module("neo", ['ngRoute']);

/**/


neoApp.controller("metaDataController",function($scope,$http){


    $scope.metaData = null;
    $scope.metaDataUrl = "";
    $http.get($scope.metaDataUrl).success(function(s){

    }).error(function(e){

    });


});
neoApp.controller("actionController",function($scope){


});