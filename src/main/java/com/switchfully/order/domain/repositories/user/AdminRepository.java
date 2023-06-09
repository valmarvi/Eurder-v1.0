package com.switchfully.order.domain.repositories.user;

import com.switchfully.order.domain.models.user.Admin;
import com.switchfully.order.exception.exceptions.DuplicateEmailException;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class AdminRepository {
    private final List<Admin> adminDatabase;

    public AdminRepository() {
        adminDatabase = new ArrayList<>();
        initializeDummyData();
    }

    public void createAdmin(Admin admin) {
        adminDatabase.add(admin);
    }

    public String getByIndex(int index) {
        return adminDatabase.get(index).getId();
    }

    public Optional<Admin> getAdminById(String adminId) {
        return adminDatabase.stream()
                .filter(admin -> admin.getId().equals(adminId))
                .findFirst();
    }

    private void initializeDummyData() {
        Admin admin = new Admin("Louie", "Charles", "l.charles@gmail.com");
        adminDatabase.add(admin);
    }
}
