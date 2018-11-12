package com.buutcamp.main;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


@Controller
public class FrontPage {


    @GetMapping("/")
    public String frontPageGET(Model model) { return "frontpage"; }


    @GetMapping("/showLoginPage")
    private String showloginpage(Model model) {
        return "loginpage";
    }


    @GetMapping("/logout.done")
    private String logout() {
        return "redirect:/showLoginPage";
    }


    @PostMapping("/checkuser")
    private String checkUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_manager"))){
            return "redirect:/manager/";
        } else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_visitor"))){
            return "redirect:/library/";
        } else {
            return "frontpage";
        }
    }
}
