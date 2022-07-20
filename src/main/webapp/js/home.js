let s2fapp = angular.module('s2fapp', ['ngRoute']);
s2fapp.config(function($routeProvider) {
	$routeProvider
		.when("/", {
		templateUrl: "login.html"
		})
		.when("/home", {
			templateUrl: "home.html"
		})
		.when("/account", {
			templateUrl: "account.html"
		})
		.when("/transactions", {
			templateUrl: "transactions.html"
		})
		.when("/order", {
			templateUrl: "order.html"
		})
		.otherwise({
			templateUrl: "home.html"
		});
		
		

});