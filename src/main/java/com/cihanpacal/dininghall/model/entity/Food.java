package com.cihanpacal.dininghall.model.entity;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="t_food")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SQLDelete(sql = "UPDATE t_food SET active = false WHERE id=?")
@Where(clause = "active=true")
public class Food extends BaseEntity {

    @Column(name="name",nullable = false)
    private String name;

    @Column(name ="description")
    private String description;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="food_group_id",nullable = false)
    private FoodGroup foodGroup;

    @OneToMany(mappedBy = "food",fetch = FetchType.LAZY)
    private Set<FoodProduct> foodProducts;

    @OneToMany(mappedBy = "food",fetch = FetchType.LAZY)
    private Set<MenuFood> menuFoods;



}
