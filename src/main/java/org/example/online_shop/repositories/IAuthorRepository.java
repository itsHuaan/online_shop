package org.example.online_shop.repositories;

import org.example.online_shop.entities.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IAuthorRepository extends JpaRepository<AuthorEntity, Long>, JpaSpecificationExecutor<AuthorEntity> {
    AuthorEntity findByAuthorId(Long id);
}
