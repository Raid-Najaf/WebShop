package com.webshop.controller;

import com.webshop.service.UserService;
import com.webshop.model.User;
import com.webshop.model.Product;
import com.webshop.service.AdminService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GuestController {

    @Autowired
    private UserService userv;

    @Autowired
    private AdminService aserv;

    //--------------------------------------------------------------------------
    @GetMapping("/")      //To View HomePage
    public String showLoginPage(Model model, @Param("keyword") String keyword) {

        model.addAttribute("user", new User());
        model.addAttribute("title", "guesthomepage Page");
        List<Product> listProducts = aserv.listAll(keyword);//show all products
        model.addAttribute("listProducts", listProducts);       
        return "/guesthomepage";
    }

    //--------------------------------------------------------------------------
    @GetMapping("/registration")        //To view registration page
    public String showRegistrationPage(Model model) {

        model.addAttribute("title", "Registration Page");

        model.addAttribute("user", new User());

        return "registration";
    }

    //--------------------------------------------------------------------------
    @PostMapping("/process")          //To ave the registration
    public String showSuccessPage(@Valid @ModelAttribute("user") User user, BindingResult result) {

//        user.setRole("ROLE_ADMIN");
        user.setRole("ROLE_USER");

        user.setPassword(userv.enCryptedPassword(user));

        if (result.hasErrors()) {
            System.out.println(result.toString());
            return "registration";
        } else {

            userv.save(user);

        }

        return "login";
    }

    //--------------------------------------------------------------------------
    @GetMapping("/login")           //To view login page
    public String showLogin(Model model) {

        model.addAttribute("title", "Login Page");

        return "login";
    }

    //--------------------------------------------------------------------------
    @RequestMapping("/default")   //To login and choose if you are User or Admin     
    public String defaultAfterLogin(HttpServletRequest request) {

        if (request.isUserInRole("ROLE_ADMIN")) {
            return "redirect:/admin/adminhomepage";
        }

        return "redirect:/user/userhomepage";
    }
    
    
    

}
