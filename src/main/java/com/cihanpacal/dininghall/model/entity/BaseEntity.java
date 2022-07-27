package com.cihanpacal.dininghall.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@MappedSuperclass
@SequenceGenerator(name = "diningHallSeq",sequenceName = "dining_hall_seq")
@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator ="diningHallSeq")
    private Long id;

    @Column(name="active",nullable = false)
    private Boolean active=true;
}
