package com.cihanpacal.dininghall.model.entity;


import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="t_meal",uniqueConstraints=@UniqueConstraint(columnNames={"dining_hall_id", "meal_date","meal_time","status_time"}))
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SQLDelete(sql = "UPDATE t_meal SET active = false,status_time=CURRENT_TIMESTAMP() WHERE id=?")
@Where(clause = "active=true")
public class Meal extends BaseEntity {

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name="status_time",nullable = false)
    private Date statusTime;

    @Column(name="number_of_people",nullable = false)
    private Integer numberOfPeople;

    @Column(name = "meal_date",nullable = false)
    private LocalDate date;


    @Column(name = "meal_time",nullable = false)
    private LocalTime time;

    @Column(name ="description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dining_hall_id",nullable = false)
    private DiningHall diningHall;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="menu_id",nullable = false)
    private Menu menu;



}
