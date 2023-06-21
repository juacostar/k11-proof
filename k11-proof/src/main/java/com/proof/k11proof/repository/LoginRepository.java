package com.proof.k11proof.repository;

import com.proof.k11proof.model.Login;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends CrudRepository<Login, Integer> {

    Login findAllByClientIdAndAndClientSecret(String clientId, String clientSecret);
}
