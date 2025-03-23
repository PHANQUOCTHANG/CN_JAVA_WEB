package com.javaweb.Java_web.controller.admin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

@RequestMapping("/admin/dashboard")
public class DashboardController {
    @GetMapping
    public String show() {
        return "views/admin/pages/dashboard/index" ;
    }
}
