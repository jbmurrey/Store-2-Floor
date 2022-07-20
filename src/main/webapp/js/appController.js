var mys2fapp = angular.module('s2fapp');
mys2fapp.controller('appController', function($scope, $http) {
		$scope.isLoggedIn = false;
		$scope.username_input = "hello";
		$scope.password_input = "";
		$scope.validateCredentials = function() {
			console.log($scope.username_input);
			console.log($scope.password_input);
		}
		
})