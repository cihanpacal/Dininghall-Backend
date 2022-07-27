package com.cihanpacal.dininghall.model.entity;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name="t_stock_out_transaction")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SQLDelete(sql = "UPDATE t_stock_out_transaction SET active = false WHERE id=?")
@Where(clause = "active=true")
@DiscriminatorValue("0")
@PrimaryKeyJoinColumn
public class StockOutTransaction extends StockTransaction {

    @Column(name="active",nullable = false)
    private Boolean act=true;

}
