package com.example.week7ecommerceapp.service.implementation;

import com.example.week7ecommerceapp.dto.AdminDTO;
import com.example.week7ecommerceapp.model.Admin;
import com.example.week7ecommerceapp.repository.AdminRepository;
import com.example.week7ecommerceapp.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    public AdminRepository adminRepo;
    @Override
    public Admin findByEmail(String email) {
        Admin admin = adminRepo.findAdminByEmail(email).orElse(null);
        return admin;
    }

    @Override
    public void addAdmin(AdminDTO adminDTO) {
        Admin admin = new Admin(adminDTO);
        adminRepo.save(admin);
    }
}
