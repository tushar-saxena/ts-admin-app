package com.ts.tsadmin.service;

import com.ts.tsadmin.domain.User;

public interface UserService {
    public User findUserByEmail(String email);

    public void saveUser(User user);
}