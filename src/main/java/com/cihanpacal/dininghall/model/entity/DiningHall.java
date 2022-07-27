package com.cihanpacal.dininghall.model.entity;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="t_dining_hall")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SQLDelete(sql = "UPDATE t_dining_hall SET active = false WHERE id=?")
@Where(clause = "active=true")
public class DiningHall extends BaseEntity {

    @Column(name="name",nullable = false)
    private String name;

    @Column(name ="description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="warehouse_id",nullable = false)
    private Warehouse warehouse;

    @OneToMany(mappedBy = "diningHall",fetch = FetchType.LAZY)
    private Set<Meal> meals;
}
