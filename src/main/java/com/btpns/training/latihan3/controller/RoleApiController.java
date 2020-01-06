package com.btpns.training.latihan3.controller;

import com.btpns.training.latihan3.entity.RoleEntity;
import com.btpns.training.latihan3.entity.UserEntity;
import com.btpns.training.latihan3.service.RoleService;
import com.btpns.training.latihan3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@RestController
@RequestMapping(value = {"/role"})
public class RoleApiController {

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;


    @PostMapping(value = "/registrasi", headers = "Accept=application/json")
    public ResponseEntity<Void> createRole (RoleEntity roleEntity, UriComponentsBuilder uriComponentsBuilder){
        roleService.insertRole(roleEntity);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponentsBuilder.path("/role/{id}").buildAndExpand(roleEntity.getRoleId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @GetMapping(value = "findById", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoleEntity> findByRoleId (@RequestParam(value = "roleId",
            defaultValue = "")int id){
        RoleEntity roleEntity = roleService.findById(id);

        if(roleEntity.equals(null)){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(roleEntity);
        }else{
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(roleEntity);
        }
    }

    @GetMapping(value = "/listByRole", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserEntity>> listByRoleId (@RequestParam(value = "listId", defaultValue = "")int id){
        List<UserEntity> userEntityList = userService.listByRoleId(id);

        if (userEntityList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(userEntityList);
        }else return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(userEntityList);
    }

}
