package com.proof.k11proof.service;

import com.proof.k11proof.dto.AddUserDTO;
import com.proof.k11proof.dto.FillTableDTO;
import com.proof.k11proof.dto.ModifyUserDTO;
import com.proof.k11proof.model.User;
import com.proof.k11proof.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRespository userRespository;


    @Override
    public void fillTable() {
        int idPage = (int)( 1 + Math.random()*2);
        String uri = "https://reqres.in/api/users?page=" + idPage;

        RestTemplate restTemplate = new RestTemplate();
        FillTableDTO result = restTemplate.getForObject(uri, FillTableDTO.class);

        userRespository.saveAll(result.getData());
    }

    @Override
    public User findOneUser(Integer id) {
        return (User) userRespository.findById(id).get();
    }

    @Override
    public User addUser(AddUserDTO addUserDTO) {
        if(addUserDTO.getEmail() == null || addUserDTO.getFirst_name() == null) return null;
        else{
            User user = new User();
            user.setId((int) (100 + Math.random() * 1000));
            user.setEmail(addUserDTO.getEmail());
            user.setFirst_name(addUserDTO.getFirst_name());
            if ((addUserDTO.getLast_name() != null)) user.setLast_name(addUserDTO.getLast_name());
            if(addUserDTO.getAvatar() != null) user.setAvatar(addUserDTO.getAvatar());

            User user1 = (User) userRespository.save(user);

            return user1;
        }
    }

    @Override
    public List<Page<User>> findAll() {
        Pageable pageable = PageRequest.of(0,5);
        Page<User> userPage = userRespository.findAll(pageable);
        List<Page<User>> pages = new ArrayList<>();
        pages.add(userPage);

        for (int i = 1; i< userPage.getTotalPages(); i++){
            pageable = PageRequest.of(i, 5);
            pages.add(userRespository.findAll(pageable));
        }

        return pages;
    }

    @Override
    public User modifyUser(ModifyUserDTO modifyUserDTO, Integer id) {

        User user = (User) userRespository.findById(id).get();

        if(user == null) return null;
        else{
           if(modifyUserDTO.getEmail() != null) user.setEmail(modifyUserDTO.getEmail());
           if(modifyUserDTO.getFirst_name() != null) user.setFirst_name(modifyUserDTO.getFirst_name());
           if(modifyUserDTO.getLast_name() != null) user.setLast_name(modifyUserDTO.getLast_name());
           if(modifyUserDTO.getAvatar() != null) user.setAvatar(modifyUserDTO.getAvatar());

           User userSaved = (User)userRespository.save(user);

           return userSaved;
        }

    }
}
