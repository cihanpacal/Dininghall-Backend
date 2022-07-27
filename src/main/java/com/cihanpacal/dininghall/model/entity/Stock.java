package com.cihanpacal.dininghall.model.entity;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="t_stock",uniqueConstraints=@UniqueConstraint(columnNames={"product_id", "warehouse_id","status_time"}))
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SQLDelete(sql = "UPDATE t_stock SET active = false,status_time=CURRENT_TIMESTAMP() WHERE id=?")
@Where(clause = "active=true")
public class Stock extends BaseEntity{

    @Column(name="quantity",nullable = false,columnDefinition = "Decimal(15,5)")
    private Double quantity;

    @Column(name="unit_price",nullable = false,columnDefinition = "Decimal(15,5)")
    private Double unitPrice;


    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name="status_time",nullable = false)
    private Date statusTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id",nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="warehouse_id",nullable = false)
    private Warehouse warehouse;

    @OneToMany(mappedBy = "stock",fetch = FetchType.LAZY)
    private Set<StockTransaction> stockTransactions;
}
