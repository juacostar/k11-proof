package com.proof.k11proof.service;

import com.proof.k11proof.dto.AddUserDTO;
import com.proof.k11proof.dto.ModifyUserDTO;
import com.proof.k11proof.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    void fillTable();

    User findOneUser(Integer id);

    User addUser(AddUserDTO addUserDTO);

    List<Page<User>> findAll();

    User modifyUser(ModifyUserDTO modifyUserDTO, Integer id);


}
