package com.cihanpacal.dininghall.model.entity;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="t_measurement_unit")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SQLDelete(sql = "UPDATE t_measurement_unit SET active = false WHERE id=?")
@Where(clause = "active=true")
public class MeasurementUnit extends BaseEntity {

    @Column(name="name",nullable = false)
    private String name;

    @Column(name="short_name",nullable = false)
    private String shortName;

    @Column(name ="description")
    private String description;

    @OneToMany(mappedBy ="measurementUnit",fetch = FetchType.LAZY)
    private Set<Product> products;
}
