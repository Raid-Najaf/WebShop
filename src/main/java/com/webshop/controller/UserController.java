package com.webshop.controller;

import com.webshop.service.AdminService;
import java.util.List;
import com.webshop.model.AdminOrder;
import com.webshop.model.Cart;
import com.webshop.model.Product;
import com.webshop.repository.CartRepository;
import com.webshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    //--------------------------------------------------------------------------
    @Autowired
    private AdminService aserv;

    @Autowired
    private CartRepository crepo;
    
    @Autowired
    private OrderRepository orepo;

    //--------------------------------------------------------------------------
    @GetMapping("/userhomepage") //To show userhomepage & Filter Search function
    public String viewProductPage(Model model, @Param("keyword") String keyword) {

        List<Product> listProducts = aserv.listAll(keyword);//show all products
        model.addAttribute("listProducts", listProducts);

        return "/user/userhomepage";
    }

    //--------------------------------------------------------------------------
    @GetMapping("/showMyCart")  //(mycart red btn)To view shopping cart
    public String viewShoppingCart(Model model) {

        model.addAttribute("listCartProducts", crepo.findAll());

        return "/user/mycart";
    }

    //--------------------------------------------------------------------------
    @GetMapping("/cart/delete/{id}")  //To delete a product from the cart
    public String deletProductFromCart(@PathVariable Long id) {

        crepo.deleteById(id);

        return "redirect:/user/showMyCart";
    }
    
    
    //--------------------------------------------------------------------------
    @RequestMapping("/addToOrder/{id}")
    public String addFromCardToOrder(@PathVariable long id) {

        Cart cart = crepo.findById(id).get();
        
        AdminOrder order = new AdminOrder();
        
        order.setEmail(cart.getEmail());
        order.setProductName(cart.getProductName());
        order.setProductPrice(cart.getProductPrice());
        order.setProductBrand(cart.getProductBrand());
        order.setProductCategory(cart.getProductCategory());

        orepo.save(order);
        
        crepo.delete(cart);

        return "redirect:/user/showMyCart";
    }

}
