package com.cihanpacal.dininghall.model.entity;


import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="t_product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SQLDelete(sql = "UPDATE t_product SET active = false WHERE id=?")
@Where(clause = "active=true")
public class Product extends BaseEntity{

    @Column(name="name",nullable = false)
    private String name;

    @Column(name ="description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_group_id",nullable = false)
    private ProductGroup productGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="measurement_unit_id",nullable = false)
    private MeasurementUnit measurementUnit;

    @OneToMany(mappedBy = "product",fetch = FetchType.LAZY)
    private Set<Stock> stocks;

    @OneToMany(mappedBy = "product",fetch = FetchType.LAZY)
    private Set<FoodProduct> foodProducts;

    @OneToMany(mappedBy = "product",fetch = FetchType.LAZY)
    private Set<MenuProduct> menuProducts;

    @OneToMany(mappedBy = "product",fetch = FetchType.LAZY)
    private Set<BillProduct> billProducts;

}
