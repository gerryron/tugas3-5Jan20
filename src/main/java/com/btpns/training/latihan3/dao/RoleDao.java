package com.btpns.training.latihan3.dao;

import com.btpns.training.latihan3.entity.RoleEntity;

public interface RoleDao {

    RoleEntity findById (int roleId);
    RoleEntity findByName (String roleName);

    void insertRole (RoleEntity roleEntity);
}
