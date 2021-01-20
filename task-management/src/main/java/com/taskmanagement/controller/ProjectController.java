package com.taskmanagement.controller;

import com.taskmanagement.dao.ProjectDao;
import com.taskmanagement.dao.TaskDao;
import com.taskmanagement.dao.UserDao;
import com.taskmanagement.model.Project;
import com.taskmanagement.model.Task;
import com.taskmanagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;

@Controller
public class ProjectController {

    @Autowired
    UserDao userDao;

    @Autowired
    ProjectDao projectDao;

    @Autowired
    TaskDao taskDao;

    @RequestMapping(method = RequestMethod.GET, value = "/projects")
    public String getProjects(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            List<Project> projects = projectDao.findProjectsByUser(user.getId());
            List<Task> tasks = taskDao.findTaskByUser(user.getId());
            model.addAttribute("user", user);
            model.addAttribute("projects", projects);
            model.addAttribute("tasks", tasks);
            return "index";
        }
        return "redirect:login";
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
                project.setTasksId(new HashSet<>());
                project.setUsersId(new HashSet<>());
                project.getUsersId().add(currentUser.getId());
                projectDao.saveProject(project);
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

    @RequestMapping(method = RequestMethod.GET, value = "/project/{id}")
    public String getProject(@PathVariable("id") String projectId, Model model, HttpSession session) {
        if (session.getAttribute("user") != null) {
            Project currentProject = projectDao.findProjectById(projectId);
            List<Task> currentTasks = taskDao.findTaskByProject(projectId);
            session.setAttribute("currentProject", currentProject);
            model.addAttribute("currentProject", currentProject);
            model.addAttribute("currentTasks", currentTasks);
            model.addAttribute("user", session.getAttribute("user"));
            return "project";
        }
        return "redirect:/login";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/delete-project/{id}")
    public String deleteProject(@PathVariable("id") String projectId, HttpSession session) {
        if (session.getAttribute("user") != null) {
            projectDao.deleteProject(projectId);
            taskDao.deleteTaskByProject(projectId);
            return "redirect:/projects";
        }
        return "redirect:/login";
    }

}
