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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.HashSet;

@Controller
public class TaskController {

    @Autowired
    UserDao userDao;

    @Autowired
    ProjectDao projectDao;

    @Autowired
    TaskDao taskDao;

    @RequestMapping(method = RequestMethod.GET, value = "/create-task")
    public String getCreateProject(Model model, HttpSession session) {
        if (session.getAttribute("user") != null) {
            model.addAttribute("user", session.getAttribute("user"));
            model.addAttribute("task", new Task());
            return "create_task";
        }
        return "redirect:login";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create-task")
    public String getCreateProject(@ModelAttribute Task task, HttpSession session, Model model) {
        if (session.getAttribute("user") != null) {
            if(session.getAttribute("currentProject") == null) {
                model.addAttribute("status", "errorProject");
                return "create_task";
            } else {
                if (!task.getName().equals("")) {
                    User currentUser = (User) session.getAttribute("user");
                    task.setUsersId(new HashSet<>());
                    task.getUsersId().add(currentUser.getId());
                    Project currentProject = (Project) session.getAttribute("currentProject");
                    task.setProjectId(currentProject.getId());
                    taskDao.saveTask(task);
                    currentProject.getTasksId().add(task.getId());
                    projectDao.saveProject(currentProject);
                    currentUser.getTasksId().add(task.getId());
                    userDao.saveUser(currentUser);
                    session.setAttribute("user", currentUser);
                    model.addAttribute("user", currentUser);
                    return "redirect:projects";
                } else {
                    model.addAttribute("status", "errorForm");
                    return "create_task";
                }
            }
        }
        return "redirect:login";
    }
}
