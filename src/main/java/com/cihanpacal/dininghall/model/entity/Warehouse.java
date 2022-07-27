package com.cihanpacal.dininghall.model.entity;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="t_warehouse")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SQLDelete(sql = "UPDATE t_warehouse SET active = false WHERE id=?")
@Where(clause = "active=true")
public class Warehouse extends BaseEntity {

    @Column(name="name",nullable = false)
    private String name;

    @Column(name ="description")
    private String description;

    @OneToMany(mappedBy = "warehouse",fetch = FetchType.LAZY)
    private Set<DiningHall> diningHalls;

    @OneToMany(mappedBy = "warehouse",fetch = FetchType.LAZY)
    private Set<Stock> stocks;

    @OneToMany(mappedBy = "warehouse",fetch = FetchType.LAZY)
    private Set<Bill> bills;
}
