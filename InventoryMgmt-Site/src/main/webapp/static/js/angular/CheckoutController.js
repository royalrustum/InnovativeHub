var erpApp = angular.module('erpApp', [ 'smart-table' ]);

erpApp
		.controller(
				'CheckoutController',
				function($scope) {
					$scope.checkoutProductsList = [];
					$scope.checkoutSaleDetailsList = [];
					$scope.checkoutSaleTotals = {};
					$scope.checkoutProductsListSubTotal = 0.0;
					$scope.checkoutProductsListTaxTotal = 0.0;
					$scope.checkoutProductsListFullTotal = 0.0;
					$scope.isCustomerSelected = false;
					$scope.selectedCustomer = {};
					$scope.enableCheckout = false;
					
					$scope.addProductItem = function(selectedItem) {
						selectedItem.sNo = $scope.checkoutProductsList.length + 1;
						$scope.checkoutProductsList.push(selectedItem);

						$scope.calculateTotals();
						$scope.configureCheckout();
						$scope.populateSaleDetailsList();
						
						$scope.refresh();
					};

					$scope.removeProductItem = function(selectedItemIndex) {
						$scope.checkoutProductsList
								.splice(selectedItemIndex - 1, 1);

						$scope.updateSerialNos();
						$scope.calculateTotals();
						$scope.configureCheckout();
						$scope.populateSaleDetailsList();
						
						$scope.refresh();
					};

					$scope.calculateTotals = function() {

						$scope.checkoutProductsListSubTotal = 0.0;
						$scope.checkoutProductsListTaxTotal = 0.0;
						$scope.checkoutProductsListFullTotal = 0.0;
						
						for (index = 0; index < $scope.checkoutProductsList.length; index++) {
							$scope.checkoutProductsListSubTotal = $scope.checkoutProductsListSubTotal
									+ $scope.checkoutProductsList[index].price.sellingPrice;

							$scope.checkoutProductsListTaxTotal = $scope.checkoutProductsListTaxTotal
									+ $scope.checkoutProductsList[index].selectedProduct.selectedProdCategory.taxPercent;

							$scope.checkoutProductsListFullTotal = $scope.checkoutProductsListTaxTotal
									+ $scope.checkoutProductsListSubTotal;
						}
					};

					$scope.updateSerialNos = function() {

						for (index = 0; index < $scope.checkoutProductsList.length; index++) {
							$scope.checkoutProductsList[index].sNo = index + 1;
						}
					};

					$scope.addCustomer = function(selectedCustomer) {
						$scope.isCustomerSelected =  true;
						$scope.selectedCustomer = selectedCustomer;
						
						$scope.configureCheckout();
						$scope.refresh();
					};
					
					$scope.removeCustomer = function() {
						$scope.isCustomerSelected =  false;
						$scope.selectedCustomer = {};
						
						$scope.configureCheckout();
						$scope.refresh();
					};
					
					$scope.configureCheckout = function() {
						if($scope.isCustomerSelected == false) {
							$scope.enableCheckout = false;
						} else if($scope.checkoutProductsList.length == 0) {
							$scope.enableCheckout = false;
						} else {
							$scope.enableCheckout = true;
						}
					};
					
					$scope.populateSaleDetailsList = function () {
						$scope.checkoutSaleDetailsList = [];
						
						for (index = 0; index < $scope.checkoutProductsList.length; index++) {
							checkoutSaleDetail = {};
							checkoutSaleDetail.sku = $scope.checkoutProductsList[index];
							
							$scope.checkoutSaleDetailsList.push(checkoutSaleDetail);
							
							$scope.checkoutSaleTotals.subTotal = $scope.checkoutProductsListSubTotal;
							$scope.checkoutSaleTotals.total = $scope.checkoutProductsListFullTotal;
							$scope.checkoutSaleTotals.discount = 0; 
							$scope.checkoutSaleTotals.saleTax = 0;
							
							checkoutSaleDetail.sale = $scope.checkoutSaleTotals;
						}
					}
					
					$scope.refresh = function() {
						setInterval(function() {
							$scope.$apply()
						}, 100);
					};
				});