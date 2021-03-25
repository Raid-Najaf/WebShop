package com.webshop.repository;

import com.webshop.model.Product;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends CrudRepository<Product, Long>{
    
    @Query(" SELECT p FROM Product p WHERE " //Filter Search function
           + " CONCAT(p.id, p.productName, p.productPrice, p.productBrand, p.productCategory) "
           + " LIKE %?1% ")
    List<Product> findAll(String keyword);
    
}
