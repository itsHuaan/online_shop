package org.example.online_shop.utils.specifications;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.online_shop.entities.InvoiceEntity;
import org.example.online_shop.entities.PostEntity;
import org.springframework.data.jpa.domain.Specification;

public class InvoiceSpecification {
    public static Specification<InvoiceEntity> isActive() {
        return (Root<InvoiceEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) ->
                criteriaBuilder.equal(root.get("status"), true);
    }
}
