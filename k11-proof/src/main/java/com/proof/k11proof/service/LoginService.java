package com.proof.k11proof.service;

import com.proof.k11proof.model.Login;

public interface LoginService {

    Login findByClientAndClientSecret( String clientId, String clientSecret);

}
