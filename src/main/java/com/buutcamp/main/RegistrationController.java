package com.buutcamp.main;

import com.buutcamp.entities.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
public class RegistrationController {

    @Autowired //defined in securityConfig
    private UserDetailsManager userDetailsManager;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @GetMapping("/register")
    public String showRegistrationPage(Model model) {

        model.addAttribute("myuser", new MyUser());

        List<String> roles = new ArrayList<String>();
        roles.add("visitor");
        roles.add("manager");
        model.addAttribute("roles",roles);

        return "registration-form";
    }


    @PostMapping("/processRegister")
    public String processRegistration(
            @Valid @ModelAttribute("myuser") MyUser user,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute(
                    "registrationError","Please fill all fields");
            //using redirect to escape POST request
            return "redirect:/register";
        }

        String username = user.getUserName();

        if (userDetailsManager.userExists(username)) {
            redirectAttributes.addFlashAttribute(
                    "registrationError","Username already exists");
            //using redirect to escape POST request
            return "redirect:/register";
        }


        //TODO: user with same email already exists
        //  email not saved to database currently


        //validation passed -> add user to database
        String encodedPassword = "{bcrypt}" + passwordEncoder.encode(user.getPassword());
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList();
        authorities.add(new SimpleGrantedAuthority("ROLE_visitor"));

        //add other roles, if necessary
        if (!user.getRole().equals("visitor")) {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+user.getRole()));
        }

        //create a new user, and save to database
        User tempUser = new User(username,encodedPassword,authorities);
        userDetailsManager.createUser(tempUser);
        redirectAttributes.addFlashAttribute(
                "registrationComplete", "Registration successful");

        return "redirect:/showLoginPage";

    }

}
