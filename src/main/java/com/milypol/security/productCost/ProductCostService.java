package com.milypol.security.productCost;

public interface ProductCostService {
    ProductCost getProductCostById(Integer id);
    ProductCost saveProductCost(ProductCost productCost);
    void deleteProductCost(Integer id);
}
