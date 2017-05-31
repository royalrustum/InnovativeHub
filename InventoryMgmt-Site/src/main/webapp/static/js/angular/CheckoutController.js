var erpApp = angular.module('erpApp', [ 'smart-table' ]);

erpApp.controller('CheckoutController', function($scope) {
	var selectedProduct = {
		productImage : "Kir",
		sNo : 1,
		sellingPrice : 100,
		quantity : 10,
		discount : 1,
		total : 100
	};
	$scope.checkoutProductsList = [];
	$scope.total = 0;

	$scope.addItem = function(selectedItem) {
		$scope.checkoutProductsList.push(selectedProduct);
		$scope.total = 120;

		$scope.refresh();
	};

	$scope.refresh = function() {
		setInterval(function() {
			$scope.$apply()
		}, 100);
	}
});