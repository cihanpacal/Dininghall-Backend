package com.cihanpacal.dininghall.model.entity;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="t_stock_transaction")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SQLDelete(sql = "UPDATE t_stock_transaction SET active = false WHERE id=?")
@Where(clause = "active=true")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="transaction_type",discriminatorType = DiscriminatorType.INTEGER)
public abstract class  StockTransaction extends BaseEntity {

    @Column(name="transaction_type", nullable=false, updatable=false, insertable=false)
    private Boolean transactionType;

    @Column(name="quantity",nullable = false,columnDefinition = "Decimal(15,5)")
    private Double quantity;

    @Column(name="unit_price",nullable = false,columnDefinition = "Decimal(15,5)")
    private Double unitPrice;

    @Column(name="description")
    private String description;

    @Column(name="transaction_time",nullable = false)
    private LocalDateTime transactionTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id",nullable = false)
    private  Stock stock;
}
