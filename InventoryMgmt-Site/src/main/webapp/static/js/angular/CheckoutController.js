var erpApp = angular.module('erpApp', []);

erpApp
		.controller(
				'CheckoutController',
				function($scope) {
					$scope.checkoutSaleDetailsList = [];
					$scope.checkoutSaleTotals = {};
					$scope.checkoutProductsListSubTotal = 0.0;
					$scope.checkoutProductsListTaxTotal = 0.0;
					$scope.checkoutProductsListFullTotal = 0.0;
					$scope.isCustomerSelected = false;
					$scope.selectedCustomer = {};
					$scope.enableCheckout = false;
					
					$scope.addProductItem = function(selectedItem) {
						selectedItem.sNo = $scope.checkoutSaleDetailsList.length + 1;
						//$scope.checkoutSaleDetailsList.push(selectedItem);
						
						checkoutSaleDetail = {};
						checkoutSaleDetail.sku = selectedItem;
						checkoutSaleDetail.selectedStock = {};
						checkoutSaleDetail.selectedStock.stockId = "-1";
						
						$scope.checkoutSaleDetailsList.push(checkoutSaleDetail);
						
						$scope.calculateTotals();
						$scope.configureCheckout();
						$scope.populateSaleDetailsList();
						
						$scope.refresh();
					};

					$scope.removeProductItem = function(selectedItemIndex) {
						$scope.checkoutSaleDetailsList
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
						
						for (index = 0; index < $scope.checkoutSaleDetailsList.length; index++) {
							$scope.checkoutProductsListSubTotal = $scope.checkoutProductsListSubTotal
									+ $scope.checkoutSaleDetailsList[index].sku.sellPrice;

							$scope.checkoutProductsListTaxTotal = $scope.checkoutProductsListTaxTotal
									+ $scope.checkoutSaleDetailsList[index].sku.selectedProduct.selectedProdCategory.taxPercent;

							$scope.checkoutProductsListFullTotal = $scope.checkoutProductsListTaxTotal
									+ $scope.checkoutProductsListSubTotal;
						}
					};

					$scope.updateSerialNos = function() {

						for (index = 0; index < $scope.checkoutSaleDetailsList.length; index++) {
							$scope.checkoutSaleDetailsList[index].sku.sNo = index + 1;
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
						} else if($scope.checkoutSaleDetailsList.length == 0) {
							$scope.enableCheckout = false;
						} else {
							$scope.enableCheckout = true;
						}
					};
					
					$scope.populateSaleDetailsList = function () {
						for (index = 0; index < $scope.checkoutSaleDetailsList.length; index++) {
							checkoutSaleDetail = $scope.checkoutSaleDetailsList[index];
							
							$scope.checkoutSaleTotals.subTotal = $scope.checkoutProductsListSubTotal;
							$scope.checkoutSaleTotals.total = $scope.checkoutProductsListFullTotal;
							$scope.checkoutSaleTotals.discount = 0; 
							$scope.checkoutSaleTotals.saleTax = 0;
							
							checkoutSaleDetail.sale = $scope.checkoutSaleTotals;
							}
					}
					
					/*$scope.onSubmit = function() {
						for (index = 0; index < $scope.checkoutSaleDetailsList.length; index++) {
							checkoutSaleDetail = $scope.checkoutSaleDetailsList[index];
							checkoutSaleDetail.selectedStock =  parseInt(checkoutSaleDetail.selectedStock);
						}
					}*/
					
					$scope.refresh = function() {
						setInterval(function() {
							$scope.$apply()
						}, 100);
					};
				});