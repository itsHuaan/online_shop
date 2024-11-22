package org.example.online_shop.repositories;

import org.example.online_shop.entities.OtpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IOtpRepository extends JpaRepository<OtpEntity, Long>, JpaSpecificationExecutor<OtpEntity> {
    List<OtpEntity> findAllByCreatedDateBeforeAndStatusTrue(LocalDateTime createdDate);
    Optional<OtpEntity> findByEmail(String email);
}
