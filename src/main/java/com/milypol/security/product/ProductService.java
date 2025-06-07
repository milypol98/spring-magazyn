package com.milypol.security.product;

import com.milypol.security.maintenanceCost.ProductCost;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Integer id);
    Product saveProduct(Product product);
    void deleteProduct(Integer id);
//    List<Product> getAllProductsByCarId(Integer carId);

}
