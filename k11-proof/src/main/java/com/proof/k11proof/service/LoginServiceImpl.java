package com.proof.k11proof.service;

import com.proof.k11proof.model.Login;
import com.proof.k11proof.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    private LoginRepository loginRepository;


    @Override
    public Login findByClientAndClientSecret(String clientId, String clientSecret) {
        return loginRepository.findAllByClientIdAndAndClientSecret(clientId, clientSecret);
    }
}
