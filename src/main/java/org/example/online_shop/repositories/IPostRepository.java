package org.example.online_shop.repositories;

import org.example.online_shop.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IPostRepository extends JpaRepository<PostEntity , Long> , JpaSpecificationExecutor<PostEntity> {
}
