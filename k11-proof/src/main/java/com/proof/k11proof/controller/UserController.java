package com.proof.k11proof.controller;

import com.proof.k11proof.dto.AddUserDTO;
import com.proof.k11proof.dto.ModifyUserDTO;
import com.proof.k11proof.model.Login;
import com.proof.k11proof.model.User;
import com.proof.k11proof.service.LoginService;
import com.proof.k11proof.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private LoginService loginService;

    @GetMapping(value = "k11/users/fill")
    public @ResponseBody ResponseEntity<Void> fillTable(){
        userService.fillTable();
        return null;
    }

    @GetMapping(value = "k11/api/users")
    public @ResponseBody ResponseEntity<List<Page<User>>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping(value = "k11/api/users/{id}")
    public @ResponseBody ResponseEntity<User> finOneUser(@RequestHeader("client_id") String clientId, @RequestHeader("client_secret") String clientSecret,
                                                         @PathVariable("id") Integer id){
        Login login = loginService.findByClientAndClientSecret(clientId, clientSecret);
        if(login == null) return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        else return ResponseEntity.ok(userService.findOneUser(id));
    }


    @PostMapping(value = "k11/api/users")
    public @ResponseBody ResponseEntity<User> createUser(@RequestBody AddUserDTO addUserDTO){
        User user = userService.addUser(addUserDTO);
        if(user == null) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        else return ResponseEntity.ok(user);
    }

    @PutMapping(value = "k11/api/users/{id}")
    public @ResponseBody ResponseEntity<User> updateUser(@RequestBody ModifyUserDTO modifyUserDTO, @PathVariable("id") Integer id){
        User user = userService.modifyUser(modifyUserDTO, id);
        if(user == null) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        else return ResponseEntity.ok(user);
    }

}
