package com.webshop.controller;

import com.webshop.service.AdminService;
import com.webshop.model.Product;
import com.webshop.repository.OrderRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService aserv;

    @Autowired
    OrderRepository orepo;

    //--------------------------------------------------------------------------
    @RequestMapping("/adminhomepage")    //To show adminhomepage
    public String viewProductPage(Model model, @Param("keyword") String keyword) {

        List<Product> listProducts = aserv.listAll(keyword);//Filter Search function
        model.addAttribute("listProducts", listProducts);

        return "/admin/adminhomepage";
    }

    //--------------------------------------------------------------------------
    @GetMapping("/showNewProduct")  //(Add Product btn)To add a new product
    public String showProduct(Model model) {

        Product prod = new Product();
        model.addAttribute("product", prod);

        return "/admin/productform";
    }

    //--------------------------------------------------------------------------
    @PostMapping("/saveProduct")    //(Save Product btn)To save a new product
    public String saveProduct(Product product, Model model) {

        aserv.saveProduct(product);

        return "redirect:/admin/adminhomepage";
    }

    //--------------------------------------------------------------------------
    @GetMapping("/product/edit/{id}")  //To update a product
    public String editProduct(@PathVariable long id, Model model) {

        model.addAttribute("product", aserv.updateProductById(id));
        return "/admin/productform";
    }

    //--------------------------------------------------------------------------
    @GetMapping("/product/delete/{id}")  //To delete a product
    public String deletProduct(@PathVariable Long id) {

        aserv.deleteProduct(id);
        return "redirect:/admin/adminhomepage";

    }

    //--------------------------------------------------------------------------
    @GetMapping("/showOrders")  //To show orders page
    public String viewOrdersPage(Model model) {

        model.addAttribute("listOrderProducts", orepo.findAll());
        return "/admin/orders";
    }

    //--------------------------------------------------------------------------
    @GetMapping("/order/manage/{id}")  //To manage a order
    public String manageOrder(@PathVariable Long id) {

        orepo.deleteById(id);
        return "redirect:/admin/showOrders";

    }

}
