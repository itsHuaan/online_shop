package org.example.online_shop.utils.specifications;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.online_shop.entities.ProductEntity;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecifications {
    public static Specification<ProductEntity> productSpecification(){
        return (Root<ProductEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) ->
                criteriaBuilder.isNotNull(root.get("id"));
    }

    public static Specification<ProductEntity> isActive() {
        return (Root<ProductEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) ->
                criteriaBuilder.equal(root.get("status"), true);
    }

    public static Specification<ProductEntity> inCategory(Long categoryId) {
        return (Root<ProductEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) ->
                criteriaBuilder.equal(root.get("category").get("categoryId"), categoryId);
    }

    public static Specification<ProductEntity> likeName(String name) {
        return (Root<ProductEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) ->
                criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<ProductEntity> hasAuthor(Long authorId) {
        return (Root<ProductEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) ->
                criteriaBuilder.equal(root.get("author").get("authorId"), authorId);
    }
}
