var erpApp = angular.module('erpApp', [ 'smart-table' ]);

erpApp.controller('CheckoutController', function($scope) {
	$scope.checkoutProductsList = [];
	$scope.checkoutProductsListSubTotal = 0.0;
	
	$scope.addItem = function(selectedItem) {
		selectedItem.sNo = $scope.checkoutProductsList.length + 1;
		$scope.checkoutProductsList.push(selectedItem);
		
		$scope.calculateTotals();

		$scope.refresh();
	};

	$scope.calculateTotals = function() {
		
		$scope.checkoutProductsListSubTotal = 0.0;
		
		for(index = 0; index < $scope.checkoutProductsList.length; index++){
			$scope.checkoutProductsListSubTotal = 
				$scope.checkoutProductsListSubTotal + $scope.checkoutProductsList[index].price.sellingPrice;
         }
	}
	
	$scope.refresh = function() {
		setInterval(function() {
			$scope.$apply()
		}, 100);
	}
});