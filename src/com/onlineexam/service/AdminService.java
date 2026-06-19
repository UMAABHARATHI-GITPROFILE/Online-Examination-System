package com.onlineexam.service;

import com.onlineexam.dao.AdminDAO;

public class AdminService {

    private AdminDAO adminDAO = new AdminDAO();

    public boolean login(String username, String password) {
        return adminDAO.validateAdmin(username, password);
    }
}
