package com.example.demospringsecurity.core.repositories;

import com.example.demospringsecurity.core.domain.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity,Long> {

    UserEntity findByEmail(String email);

//    List<UserEntity> findByLastName(@Param("lastName") String lastName);
//
//    Optional<UserEntity> findById(@Param("id") Long id);

}
