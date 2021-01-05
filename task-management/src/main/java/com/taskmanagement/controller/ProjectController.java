package com.taskmanagement.controller;

import com.taskmanagement.model.Project;
import com.taskmanagement.model.User;
import com.taskmanagement.service.ProjectService;
import com.taskmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/projects")
    public String getProjects(Model model, HttpSession session) {
        if(session.getAttribute("user") != null) {
            model.addAttribute("user", session.getAttribute("user"));
        }
        return "index";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/create-project")
    public String getCreateProject(Model model, HttpSession session) {
        if(session.getAttribute("user") != null) {
            model.addAttribute("project", new Project());
            return "create_project";
        }
        return "redirect:login";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create-project")
    public String getCreateProject(@ModelAttribute Project project, HttpSession session, Model model) {
        if(session.getAttribute("user") != null) {
            if (!project.getName().equals("")) {
                projectService.saveProject(project);
                User currentUser = (User) session.getAttribute("user");
                currentUser.addProject(project);
                project.addUser(currentUser);
                String status = "success";
                model.addAttribute("status", status);
            } else {
                String status = "error";
                model.addAttribute("status", status);
            }
            return "create_project";
        }
        return "redirect:login";
    }

}
