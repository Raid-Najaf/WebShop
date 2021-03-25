package com.webshop.service;

import com.webshop.model.Product;
import java.util.List;

public interface AdminService {

    List<Product> getProducts();

    void saveProduct(Product product);

    Product updateProductById(long id);

    void deleteProduct(Long id);
    
    public List<Product>listAll(String keyword); // Filter Search function
}
