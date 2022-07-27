package com.cihanpacal.dininghall.model.entity;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="t_menu")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SQLDelete(sql = "UPDATE t_menu SET active = false WHERE id=?")
@Where(clause = "active=true")
public class Menu extends BaseEntity {

    @Column(name="name",nullable = false)
    private String name;

    @Column(name ="description")
    private String description;

    @OneToMany(mappedBy = "menu",fetch = FetchType.LAZY)
    private Set<MenuFood> menuFoods;

    @OneToMany(mappedBy = "menu",fetch = FetchType.LAZY)
    private Set<MenuProduct> menuProducts;

    @OneToMany(mappedBy = "menu",fetch = FetchType.LAZY)
    private Set<Meal> meals;
}
