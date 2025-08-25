package com.milypol.security.product;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepo productRepo;

    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public List<ProductPosition> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public ProductPosition getProductById(Integer id) {
        return productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public ProductPosition saveProduct(ProductPosition product) {
        return productRepo.save(product);
    }

    @Override
    public void deleteProduct(Integer id) {
        productRepo.deleteById(id);
    }

    @Override
    public List<ProductPosition> getAllProductsByWarehouseId(Integer warehouseId) {
        return productRepo.findAllByWarehouse_Id(warehouseId);
    }


}
