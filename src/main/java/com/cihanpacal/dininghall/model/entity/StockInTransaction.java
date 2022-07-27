package com.cihanpacal.dininghall.model.entity;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name="t_stock_in_transaction")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SQLDelete(sql = "UPDATE t_stock_in_transaction SET active = false WHERE id=?")
@Where(clause = "active=true")
@DiscriminatorValue("1")
@PrimaryKeyJoinColumn
public class StockInTransaction extends StockTransaction {

    @Column(name="active",nullable = false)
    private Boolean act=true;

}
