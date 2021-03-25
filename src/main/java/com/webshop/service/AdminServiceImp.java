package com.webshop.service;

import com.webshop.model.Product;
import com.webshop.repository.AdminRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImp implements AdminService {

    @Autowired
    AdminRepository arepo;
    
    //--------------------------------------------------------------------------
    @Override
    public List<Product> getProducts() {

        return (List<Product>) arepo.findAll();
    }
    
    //--------------------------------------------------------------------------
    @Override
    public void saveProduct(Product product) {

        arepo.save(product);
    }

    //--------------------------------------------------------------------------
    @Override
    public Product updateProductById(long id) {

        Optional<Product> option = arepo.findById(id);

        Product prod = null;
        if (option.isPresent()) {
            prod = option.get();
        } else {
            System.out.println("Product is not available with id:" + id);
        }

        return prod;

    }

    //--------------------------------------------------------------------------
    @Override
    public void deleteProduct(Long id) {

        arepo.deleteById(id);
    }

    //--------------------------------------------------------------------------
    @Override
    public List<Product> listAll(String keyword) {
        
        if (keyword != null) {      
            
            return arepo.findAll(keyword);  // Filter Search function
            
        }
        return getProducts(); //Hämtad från getProducts function
    }

}
