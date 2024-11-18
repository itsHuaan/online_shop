package org.example.online_shop.repositories;

import org.apache.catalina.User;
import org.apache.poi.ss.formula.functions.T;
import org.example.online_shop.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {
    UserEntity findByUsername(String username);
    Optional<UserEntity> findByEmailOrUsername(String email, String username);
}
