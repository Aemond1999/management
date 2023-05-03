package com.hya.management.controller;

import com.hya.management.common.dto.UserDTO;
import com.hya.management.common.dto.UserLoginDTO;
import com.hya.management.service.PicUploadService;
import com.hya.management.service.UserService;
import com.hya.management.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    PicUploadService picUploadService;

    @GetMapping("/list")
    Result list(){
        return userService.userList();
    }
    @PostMapping("/add")
    Result add(@RequestBody UserDTO userDTO){
        return userService.addUser(userDTO);
    }
    @PutMapping("/update")
    Result update(@RequestBody UserDTO userDTO){
        return userService.updateUser(userDTO);
    }
    @DeleteMapping("/delete/{id}")
    Result delete(@PathVariable Long id){
        return userService.deleteUser(id);
    }










    @PostMapping("/login")
    Result login(@RequestBody UserLoginDTO userLoginDTO ) {
        return userService.login(userLoginDTO);
    }


    @PostMapping("/upload")
    Result upload(@RequestPart(value = "file") MultipartFile img) {

        return picUploadService.upload(img);
    }
    @GetMapping("/userInfo/{id}")
    Result showUserInfo(@PathVariable Long id){
        return userService.getUserInfoById(id);
    }
    @GetMapping("/logout")
    Result logout(){
        return  userService.logout();
    }


}