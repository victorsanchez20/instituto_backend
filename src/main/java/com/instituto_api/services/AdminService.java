package com.instituto_api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.instituto_api.models.Admin;
import com.instituto_api.repositories.AdminRepository;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminService(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Admin saveAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepository.save(admin);
    }

    public void deleteById(long id) {
        adminRepository.deleteById(id);
    }

    public Optional<Admin> login(String usuario, String password) {
        return adminRepository.findByUsuario(usuario)
                .filter(admin -> passwordEncoder.matches(password, admin.getPassword()));
    }
}
