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
						// $scope.checkoutSaleDetailsList.push(selectedItem);

						checkoutSaleDetail = {};

						checkoutSaleDetail.sku = selectedItem;
						checkoutSaleDetail.sku.saleDetailRowTotal = checkoutSaleDetail.sku.sellPrice;
						checkoutSaleDetail.sku.saleDetailRowSubTotal = 0;

						// We don't need to send Image data back.
						checkoutSaleDetail.sku.selectedProduct.productImage = "";
						checkoutSaleDetail.sku.selectedProduct.base64FileBytesString = "";

						// Init.
						checkoutSaleDetail.quantity = 1;
						checkoutSaleDetail.discountPct = 0;

						checkoutSaleDetail.selectedStock = {};
						checkoutSaleDetail.selectedStock.stockId = "-1";

						$scope.checkoutSaleDetailsList.push(checkoutSaleDetail);

						$scope.calcuateRowSubTotal(checkoutSaleDetail);
						
						$scope.calculateTotals();
						$scope.configureCheckout();
						$scope.populateSaleDetailsList();

						$scope.refresh();
					};

					$scope.removeProductItem = function(selectedItemIndex) {
						$scope.checkoutSaleDetailsList.splice(
								selectedItemIndex - 1, 1);

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
									+ $scope.checkoutSaleDetailsList[index].sku.saleDetailRowSubTotal;

							$scope.checkoutProductsListTaxTotal = $scope.checkoutProductsListTaxTotal
									+ $scope.checkoutSaleDetailsList[index].sku.sellPriceSaleTax;

							$scope.checkoutProductsListFullTotal = $scope.checkoutProductsListFullTotal
									+ $scope.checkoutSaleDetailsList[index].sku.saleDetailRowTotal;
						}
					};

					$scope.updateSerialNos = function() {

						for (index = 0; index < $scope.checkoutSaleDetailsList.length; index++) {
							$scope.checkoutSaleDetailsList[index].sku.sNo = index + 1;
						}
					};

					$scope.addCustomer = function(selectedCustomer) {
						$scope.isCustomerSelected = true;
						$scope.selectedCustomer = selectedCustomer;

						$scope.configureCheckout();
						$scope.refresh();
					};

					$scope.removeCustomer = function() {
						$scope.isCustomerSelected = false;
						$scope.selectedCustomer = {};

						$scope.configureCheckout();
						$scope.refresh();
					};

					$scope.configureCheckout = function() {
						if ($scope.isCustomerSelected == false) {
							$scope.enableCheckout = false;
						} else if ($scope.checkoutSaleDetailsList.length == 0) {
							$scope.enableCheckout = false;
						} else if ($scope.isStockSelectedForAllSaleDetails() == false) {
							$scope.enableCheckout = false;
						} else {
							$scope.enableCheckout = true;
						}
					};

					$scope.isStockSelectedForAllSaleDetails = function() {
						var isStockSelected = true;

						for (index = 0; index < $scope.checkoutSaleDetailsList.length; index++) {
							checkoutSaleDetail = $scope.checkoutSaleDetailsList[index];

							if (checkoutSaleDetail.selectedStock.stockId == "-1") {
								isStockSelected = false;
							}
						}

						return isStockSelected;
					}

					$scope.populateSaleDetailsList = function() {
						for (index = 0; index < $scope.checkoutSaleDetailsList.length; index++) {
							checkoutSaleDetail = $scope.checkoutSaleDetailsList[index];

							$scope.checkoutSaleTotals.subTotal = $scope.checkoutProductsListSubTotal;
							$scope.checkoutSaleTotals.total = $scope.checkoutProductsListFullTotal;
							$scope.checkoutSaleTotals.discount = 0;
							$scope.checkoutSaleTotals.saleTax = 0;

							checkoutSaleDetail.sale = $scope.checkoutSaleTotals;
						}
					}

					/*
					 * $scope.onSubmit = function() { for (index = 0; index <
					 * $scope.checkoutSaleDetailsList.length; index++) {
					 * checkoutSaleDetail =
					 * $scope.checkoutSaleDetailsList[index];
					 * checkoutSaleDetail.selectedStock =
					 * parseInt(checkoutSaleDetail.selectedStock); } }
					 */
					$scope.onTextChange = function(sNo) {
						checkoutSaleDetail = $scope.checkoutSaleDetailsList[sNo - 1];
						$scope.calcuateRowSubTotal(checkoutSaleDetail);
						$scope.calculateTotals();
						
						$scope.refresh();
					}

					$scope.calcuateRowSubTotal = function(checkoutSaleDetail) {

						checkoutSaleDetail.sku.saleDetailRowTotal = checkoutSaleDetail.sku.sellPrice
								* checkoutSaleDetail.quantity;
						checkoutSaleDetail.sku.saleDetailRowTotal = checkoutSaleDetail.sku.saleDetailRowTotal
								- (checkoutSaleDetail.sku.saleDetailRowTotal
										* checkoutSaleDetail.discountPct * .01);

						checkoutSaleDetail.sku.saleDetailRowSubTotal = checkoutSaleDetail.sku.saleDetailRowTotal;
						checkoutSaleDetail.sku.sellPriceSaleTax = checkoutSaleDetail.sku.saleDetailRowTotal
								* checkoutSaleDetail.sku.selectedProduct.selectedProdCategory.taxPercent
								* .01;

						checkoutSaleDetail.sku.saleDetailRowTotal = checkoutSaleDetail.sku.saleDetailRowTotal
								+ checkoutSaleDetail.sku.sellPriceSaleTax;
					}

					$scope.refresh = function() {
						setInterval(function() {
							$scope.$apply()
						}, 100);
					};
				});