package org.example.online_shop.utils.specifications;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.online_shop.entities.CategoryEntity;
import org.springframework.data.jpa.domain.Specification;

public class CategorySpecifications {
    public static Specification<CategoryEntity> isActive() {
        return (Root<CategoryEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) ->
                criteriaBuilder.equal(root.get("status"), true);
    }
}
