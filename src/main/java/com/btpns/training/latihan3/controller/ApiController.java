package com.btpns.training.latihan3.controller;

import com.btpns.training.latihan3.dao.ResponseDao;
import com.btpns.training.latihan3.entity.UserEntity;
import com.btpns.training.latihan3.service.PasswordConverterService;
import com.btpns.training.latihan3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping(value = {"/user"})
public class ApiController {
    @Autowired
    UserService userService;

    @Autowired
    PasswordConverterService passwordConverterService;

    @PostMapping(value = "/registrasi", headers = "Accept=application/json")
    public ResponseEntity<Void> createUser(UserEntity userEntity, UriComponentsBuilder uriComponentsBuilder){
        userEntity.setPassword(passwordConverterService.convertPassword(userEntity.getPassword()));
        userService.insertUser(userEntity);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponentsBuilder.path("/user/{id}").buildAndExpand(userEntity.getUserId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @GetMapping(value = "/findById",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDao> findByUserId(@RequestParam(value = "userId", defaultValue = "") int id){

        ResponseDao responseDao = new ResponseDao();
        UserEntity userEntity = userService.findById(id);

        responseDao.setData(userEntity);
        if(userEntity.equals(null)){
            responseDao.setCode(404);
            responseDao.setMessage("Error Not Found!");
            responseDao.setStatus("Failed");
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(responseDao);
        }else{
            responseDao.setCode(200);
            responseDao.setMessage("Sukses isi 2");
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(responseDao);
        }
    }
}