package com.cihanpacal.dininghall.model.entity;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="t_product_group")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SQLDelete(sql = "UPDATE t_product_group SET active = false WHERE id=?")
@Where(clause = "active=true")
public class ProductGroup extends BaseEntity {

    @Column(name="name",nullable = false)
    private String name;

    @Column(name ="description")
    private String description;

    @OneToMany(mappedBy = "productGroup",fetch = FetchType.LAZY)
    private Set<Product> products;
}
