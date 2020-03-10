package com.mirai.indidea.dao;

import com.mirai.indidea.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findAdminByAdminnameAndPassword(String adminname, String password);
    Admin findAdminById(int id);
}
