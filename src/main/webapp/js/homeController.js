var mys2fapp = angular.module('s2fapp');
mys2fapp.controller('homeController', function($scope, $http) {
	// Declare scopes
	$scope.selectedItem = null;
	$scope.location = null;
	$scope.items = null;
	$scope.moveToStoreDisabled = true;
	$scope.moveToFloorDisabled = true;
	
	$scope.getItems = function() {
		$http.get("/Store2Floor/webapi/items")
			.then(function(response) {
				$scope.items = response.data;
				console.log(response.data);
			},
				function(response) {
					console.log(response.status);
				})
	}
	$scope.moveItem = function(quantity) {
		let url = "/Store2Floor/webapi/updateItem";
		quantity = Number(quantity);
		if ($scope.location == 'Store') {
			$scope.selectedItem.qStore -= quantity;
			$scope.selectedItem.qFloor += quantity;
		}
		else if ($scope.location == 'Floor') {
			$scope.selectedItem.qFloor -= quantity;
			$scope.selectedItem.qStore += quantity;
		}
		$http.post(url, $scope.selectedItem)
			.then(function(response) {
				$scope.updatedItem = response.data;
				window.location.reload();
				console.log(response.status);

			},
				function(response) {
					window.location.reload();
					console.log(response.status);
				})


	}
	$scope.selectItem = function(item, location) {
		$scope.selectedItem = item;
		$scope.location = location;
		if(location == "Store"){
			$scope.moveToStoreDisabled = true;
			$scope.moveToFloorDisabled = false;
		}
		else if(location == "Floor"){
			$scope.moveToFloorDisabled = true;
			$scope.moveToStoreDisabled = false;
		}
	}
	$scope.filter = function(query) {
		url = "/Store2Floor/webapi/items/" + query;
		$http.get(url)
			.then(function(response) {
				$scope.items = response.data;
			},
				function(response) {
					console.log(response.status);
				})

	}
	$scope.getItems();
})