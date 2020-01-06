package com.btpns.training.latihan3.service;

import com.btpns.training.latihan3.dao.RoleDao;
import com.btpns.training.latihan3.entity.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public RoleEntity findById(int roleId) {
        return roleDao.findById(roleId);
    }

    @Override
    public RoleEntity findByName(String roleName) {
        return roleDao.findByName(roleName);
    }

    @Override
    public void insertRole(RoleEntity roleEntity) {
        roleDao.insertRole(roleEntity);
    }
}
