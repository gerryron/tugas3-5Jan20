package com.btpns.training.latihan3.service;

import com.btpns.training.latihan3.dao.UserDao;
import com.btpns.training.latihan3.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSeviceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public UserEntity findById(int userId) {
        return userDao.findById(userId);
    }

    @Override
    public UserEntity findByName(String userName) {
        return userDao.findByName(userName);
    }

    @Override
    public List<UserEntity> listByRoleId(int listRoleId) {
        return userDao.listByRoleId(listRoleId);
    }

    @Override
    public void insertUser(UserEntity userEntity) {
        userDao.insertUser(userEntity);
    }
}
