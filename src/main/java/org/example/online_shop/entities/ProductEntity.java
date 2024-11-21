package org.example.online_shop.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String name;
    @Size(max = 10000)
    private String description;
    private double importPrice;
    private double salePrice;
    private Integer stock;
    private Integer sold;
    private Integer totalPage;
    private Integer publishDate;
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private AuthorEntity author;

    @ManyToOne
    @JoinColumn(name = "discount_id")
    private DiscountEntity discount;

    @OneToMany(mappedBy = "product")
    private List<ReviewEntity> reviews;

    @OneToMany(mappedBy = "product")
    private List<CartDetailEntity> cartDetails;

    @OneToMany(mappedBy = "product")
    private List<InvoiceDetailEntity> invoiceDetails;

    private Boolean status = true;

    private LocalDateTime createdDate;
}

