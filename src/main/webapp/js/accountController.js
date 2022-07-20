var mys2fapp = angular.module('s2fapp');
mys2fapp.controller('accountController', function($scope, $http) {
		$scope.getUsers = function() {
		$http.get("/Store2Floor/webapi/user/1")
			.then(function(response) {
				$scope.user = response.data[0];	
				console.log($scope.user);
			},
				function(response) {
					console.log(response.status);
				})
	}
	$scope.getUsers();
	$scope.updateUser = function() {
		let url = "/Store2Floor/webapi/updateUser";
		$http.post(url, $scope.selectedUser)
		.then(function(response) {
			$scope.updatedUser = response.data;
			window.location.reload();
			console.log(response.status);

		},
			function(response) {
				window.location.reload();
				console.log(response.status);
			})
	}
})