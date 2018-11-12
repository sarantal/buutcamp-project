package com.buutcamp.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value="/manager")
public class ManagerPage {


    @GetMapping("/")
    public String adminPageGET() { return "manager"; }


    @GetMapping("/logout.done")
    private String logout() {
        return "redirect:/showLoginPage";
    }
}
