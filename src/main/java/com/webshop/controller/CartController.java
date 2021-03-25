package com.webshop.controller;

import com.webshop.model.Product;
import com.webshop.model.Cart;
import com.webshop.repository.CartRepository;
import com.webshop.repository.ProductRepository;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CartController {

    @Autowired
    CartRepository crepo;

    @Autowired
    ProductRepository prepo;

    //--------------------------------------------------------------------------
    @RequestMapping("/addToMyCart/{id}")//(Save Product to my cart green btn)To add product to my cart
    public String saveProductToMyCart(@PathVariable long id, HttpServletRequest request) {

        Product pro = prepo.findById(id).get();

        Cart car = new Cart();

        car.setProductName(pro.getProductName());
        car.setProductBrand(pro.getProductBrand());
        car.setProductCategory(pro.getProductCategory());
        car.setProductPrice(pro.getProductPrice());
        car.setEmail(request.getRemoteUser());

        crepo.save(car);


        return "redirect:/user/userhomepage";
    }

}
