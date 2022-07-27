package com.cihanpacal.dininghall.model.entity;


import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Table(name="t_bill")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SQLDelete(sql = "UPDATE t_bill SET active = false WHERE id=?")
@Where(clause = "active=true")
public class Bill extends BaseEntity{

    @Column(name = "bill_time",nullable = false)
    private LocalDateTime time;


    @Column(name ="description")
    private String description;

    @OneToMany(mappedBy = "bill",fetch = FetchType.LAZY)
    private Set<BillProduct> billProducts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="warehouse_id",nullable = false)
    private Warehouse warehouse;
}
