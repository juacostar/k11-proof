package com.proof.k11proof.repository;

import com.proof.k11proof.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRespository<T, Integer> extends CrudRepository<User, Integer> {

    Page<User> findAll(Pageable pageable);
}
