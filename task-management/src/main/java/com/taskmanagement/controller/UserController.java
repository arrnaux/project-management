package com.taskmanagement.controller;

import com.taskmanagement.model.User;
import com.taskmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String getLogin() {
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public String getLogin(@RequestParam String email, @RequestParam String password, Model model) {
        User user = userService.userLogin(email, password);
        if(user != null) {
            model.addAttribute("user", user);
            return "home";
        } else {
            String status = "error";
            model.addAttribute("status", status);
            return "login";
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/logout")
    public String getLogout(Model model) {
        String status = "logout";
        model.addAttribute("status", status);
        return "login";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/register")
    public String getRegister() {
        return "register";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public String getRegister(@RequestParam String name, @RequestParam String email, @RequestParam String password,
                              @RequestParam String passwordRe, Model model) {
        if(password.equals(passwordRe)) {
            if(userService.isEmailFree(email)) {
                User user = new User();
                user.setName(name);
                user.setEmail(email);
                user.setPassword(password);
                userService.createUser(user);
                return "home";
            } else {
                String status = "emailError";
                model.addAttribute("status", status);
                return "register";
            }
        } else {
            String status = "passError";
            model.addAttribute("status", status);
            return "register";
        }

    }

}
