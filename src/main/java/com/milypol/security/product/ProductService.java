package com.milypol.security.product;

import java.util.List;

public interface ProductService {
    List<ProductPosition> getAllProducts();
    ProductPosition getProductById(Integer id);
    ProductPosition saveProduct(ProductPosition product);
    void deleteProduct(Integer id);
    List<ProductPosition> getAllProductsByWarehouseId(Integer warehouseId);


}
