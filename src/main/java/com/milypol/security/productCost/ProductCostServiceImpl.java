package com.milypol.security.productCost;

import org.springframework.stereotype.Service;

@Service
public class ProductCostServiceImpl implements ProductCostService{
    private final ProductCostRepo productCostRepo;

    public ProductCostServiceImpl(ProductCostRepo productCostRepo) {
        this.productCostRepo = productCostRepo;
    }

    @Override
    public ProductCost getProductCostById(Integer id) {
        return productCostRepo.findById(id).orElseThrow(() -> new RuntimeException("ProductCost not found"));
    }

    @Override
    public ProductCost saveProductCost(ProductCost productCost) {
        return productCostRepo.save(productCost);
    }

    @Override
    public void deleteProductCost(Integer id) {
        productCostRepo.deleteById(id);
    }
}
