package com.btpns.training.latihan3.controller;

import com.btpns.training.latihan3.entity.UserEntity;
import com.btpns.training.latihan3.service.PasswordConverterService;
import com.btpns.training.latihan3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordConverterService passwordConverterService;

    @RequestMapping(value = "/main")
    public String mainPage(Model model) {
        return "udahLogin";
    }

    @RequestMapping(value = "/")
    public String home(Model model){
        return "redirect:/main";
    }

    @RequestMapping(value = "/register")
    public String register(Model model, @ModelAttribute("userEntity") UserEntity userEntity){
        return "register";
    }

    @RequestMapping(value = "/saveRegistration")
    public String saveRegistration(Model model, @ModelAttribute("userEntity")UserEntity userEntity){
        userEntity.setPassword(passwordConverterService.convertPassword(userEntity.getPassword()));
        userService.insertUser(userEntity);
        return "redirect:/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage (Model model, String error, String logout, HttpServletRequest request,
                        @ModelAttribute("userEntity") UserEntity userEntity){
        if (error != null){
            model.addAttribute("error", "Your Username and Password is invalid");
        }
        if (logout != null){
            model.addAttribute("message","Logout Success !");
        }
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutRequestMatcher (Model model, String error, String logout){
        if (error != null){
            model.addAttribute("error", "Your Username and Password is invalid");
        }
        if (logout != null){
            model.addAttribute("message","Logout Success!");
        }
        return "login";
    }
}