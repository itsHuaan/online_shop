package org.example.online_shop.utils.specifications;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.online_shop.entities.AuthorEntity;
import org.springframework.data.jpa.domain.Specification;

public class AuthorSpecifications {
    public static Specification<AuthorEntity> isActive() {
        return (Root<AuthorEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) ->
                criteriaBuilder.equal(root.get("status"), true);
    }
}
