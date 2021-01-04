package com.taskmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProjectController {

    @RequestMapping(method = RequestMethod.GET, value = "/create-project")
    public String getCreateProject() {
        return "create_project";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create-project")
    public String getCreateProject(@RequestParam String name, @RequestParam String description, Model model) {
        return "create_project";
    }

}
