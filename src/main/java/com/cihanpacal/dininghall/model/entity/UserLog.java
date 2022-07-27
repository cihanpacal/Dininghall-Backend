package com.cihanpacal.dininghall.model.entity;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="t_user_log")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SQLDelete(sql = "UPDATE t_user_log SET active = false WHERE id=?")
@Where(clause = "active=true")
public class UserLog extends BaseEntity {


    @Column(name = "username",nullable = false)
    private String username;

    @Column(name="ip_address",nullable = false)
    private String ipAddress;

    @Column(name = "http_method_name",nullable = false)
    private String httpMethodName;

    @Column(name="url",nullable = false)
    private String url;

    @Column(name="time",nullable = false)
    private LocalDateTime time;

    @Column(name="status_code",nullable = false)
    private Integer statusCode;

}
