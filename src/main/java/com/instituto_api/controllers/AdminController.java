package com.instituto_api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.instituto_api.models.Admin;
import com.instituto_api.services.AdminService;

@RestController
@RequestMapping("/api/instituto/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public List<Admin> listarAdmins() {
        return adminService.getAllAdmins();
    }

    @PostMapping
    public Admin crearAdmin(@RequestBody Admin admin) {
        return adminService.saveAdmin(admin);
    }
}
