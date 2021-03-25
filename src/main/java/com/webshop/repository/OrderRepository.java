package com.webshop.repository;

import com.webshop.model.AdminOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<AdminOrder, Long>{
    
}
