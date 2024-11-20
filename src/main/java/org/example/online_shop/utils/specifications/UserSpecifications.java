package org.example.online_shop.utils.specifications;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.online_shop.entities.ProductEntity;
import org.example.online_shop.entities.UserEntity;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecifications {
    public static Specification<UserEntity> isActive() {
        return (Root<UserEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) ->
                criteriaBuilder.equal(root.get("status"), true);
    }
}
