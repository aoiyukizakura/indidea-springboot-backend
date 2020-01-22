package com.mirai.indidea.service.serviceImpl;

import com.mirai.indidea.entity.User;

public interface UserServiceImpl {

    public User find(int id);
    public User save(User user);
    public boolean delete(int id);

}
