package com.taskmanagement.controller;

import com.taskmanagement.dao.ProjectDao;
import com.taskmanagement.dao.UserDao;
import com.taskmanagement.model.Project;
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
public class ProjectController {

    @Autowired
    ProjectDao projectDao;

    @Autowired
    UserDao userDao;

    @RequestMapping(method = RequestMethod.GET, value = "/projects")
    public String getProjects(Model model, HttpSession session) {
        if (session.getAttribute("user") != null) {
            model.addAttribute("user", session.getAttribute("user"));
        }
        return "index";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/create-project")
    public String getCreateProject(Model model, HttpSession session) {
        if (session.getAttribute("user") != null) {
            model.addAttribute("user", session.getAttribute("user"));
            model.addAttribute("project", new Project());
            return "create_project";
        }
        return "redirect:login";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create-project")
    public String getCreateProject(@ModelAttribute Project project, HttpSession session, Model model) {
        if (session.getAttribute("user") != null) {
            if (!project.getName().equals("")) {
                User currentUser = (User) session.getAttribute("user");
                project.setTasks(new HashSet<>());
                project.setUsersId(new HashSet<>());
                project.getUsersId().add(currentUser.getId());
                projectDao.insertProject(project);
                currentUser.getProjectsId().add(project.getId());
                userDao.saveUser(currentUser);
                session.setAttribute("user", currentUser);
                model.addAttribute("user", currentUser);
                return "redirect:projects";
            } else {
                model.addAttribute("status", "error");
                return "create_project";
            }

        }
        return "redirect:login";
    }

}
