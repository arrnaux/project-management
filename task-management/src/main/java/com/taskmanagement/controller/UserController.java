package com.taskmanagement.controller;

import com.taskmanagement.dao.UserDao;
import com.taskmanagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.HashSet;

@Controller
public class UserController {

    @Autowired
    UserDao userDao;

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String getLogin(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public String getLogin(@ModelAttribute User user, Model model, HttpSession session) {
        User currentUser = userDao.findUserByEmailAndPassword(user);
        if (currentUser != null) {
            session.setAttribute("user", currentUser);
            return "redirect:projects";
        } else {
            String status = "error";
            model.addAttribute("status", status);
            return "login";
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/logout")
    public String getLogout(Model model, HttpSession session) {
        session.invalidate();
        String status = "logout";
        model.addAttribute("status", status);
        return "redirect:login";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/register")
    public String getRegister(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public String getRegister(@ModelAttribute User user, Model model, HttpSession session) {
        if (userDao.findUserByEmail(user.getEmail()) == null) {
            user.setProjectsId(new HashSet<>());
            user.setTasksId(new HashSet<>());
            userDao.saveUser(user);
            session.setAttribute("user", user);
            return "redirect:projects";
        } else {
            String status = "error";
            model.addAttribute("status", status);
            return "register";
        }


    }

}
