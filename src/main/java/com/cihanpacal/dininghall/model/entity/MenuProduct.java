package com.cihanpacal.dininghall.model.entity;


import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="t_menu_product",uniqueConstraints=@UniqueConstraint(columnNames={"menu_id", "product_id","status_time"}))
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SQLDelete(sql = "UPDATE t_menu_product SET active = false,status_time=CURRENT_TIMESTAMP() WHERE id=?")
@Where(clause = "active=true")
public class MenuProduct extends BaseEntity {

    @Column(name="quantity",nullable = false,columnDefinition = "Decimal(10,3)")
    private Double quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="menu_id",nullable = false)
    private Menu menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id",nullable = false)
    private Product product;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name="status_time",nullable = false)
    private Date statusTime;

}
