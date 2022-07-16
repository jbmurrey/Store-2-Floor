let mys2fapp = angular.module('s2fapp');
mys2fapp.controller('homeController', function($scope, $http) {
	$scope.getAllItems = function() {
		$http.get("/Store2Floor/webapi/items")
			.then(function(response) {
				$scope.items = response.data;
				console.log($scope.items);
			},
				function(response) {
					console.log(resonse.status);
				})
	}
	$scope.getAllItems();
})