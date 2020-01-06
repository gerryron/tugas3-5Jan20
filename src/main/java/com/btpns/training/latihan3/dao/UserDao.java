package com.btpns.training.latihan3.dao;

import com.btpns.training.latihan3.entity.UserEntity;

import java.util.List;

public interface UserDao {

    UserEntity findById (int userId);
    UserEntity findByName (String userName);
    List<UserEntity> listByRoleId (int listRoleId);

    void insertUser(UserEntity userEntity);
}
