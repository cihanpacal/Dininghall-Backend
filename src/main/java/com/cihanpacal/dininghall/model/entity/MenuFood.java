package com.cihanpacal.dininghall.model.entity;


import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="t_menu_food",uniqueConstraints=@UniqueConstraint(columnNames={"menu_id", "food_id","status_time"}))
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SQLDelete(sql = "UPDATE t_menu_food SET active = false,status_time=CURRENT_TIMESTAMP() WHERE id=?")
@Where(clause = "active=true")
public class MenuFood extends BaseEntity {

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name="status_time",nullable = false)
    private Date statusTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="menu_id",nullable = false)
    private Menu menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="food_id",nullable = false)
    private Food food;
}
