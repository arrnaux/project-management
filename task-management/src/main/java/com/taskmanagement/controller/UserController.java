package com.taskmanagement.controller;

import java.util.HashSet;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taskmanagement.dao.UserDao;
import com.taskmanagement.model.User;

import static com.taskmanagement.utils.PasswordEncoder.hashPassword;
import static com.taskmanagement.utils.PasswordEncoder.salt;

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
    public String getLogin(@ModelAttribute User user, Model model, HttpSession session)
    {
        try {
            user.setPassword(hashPassword(user.getPassword(), salt));
            User currentUser = userDao.findUserByEmailAndPassword(user);
            if (currentUser != null) {
                session.setAttribute("user", currentUser);
                return "redirect:projects";
            } else {
                String status = "error";
                model.addAttribute("status", status);
                return "login";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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
        try {

            if (userDao.findUserByEmail(user.getEmail()) == null) {
                user.setProjectsId(new HashSet<>());
                user.setTasksId(new HashSet<>());
                user.setPassword(hashPassword(user.getPassword(), salt));
                userDao.saveUser(user);
                session.setAttribute("user", user);
                return "redirect:projects";
            } else {
                String status = "error";
                model.addAttribute("status", status);
                return "register";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

}