package com.cihanpacal.dininghall.model.entity;


import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="t_password_reset_token")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SQLDelete(sql = "UPDATE t_password_reset_token SET active = false WHERE id=?")
@Where(clause = "active=true")
public class PasswordResetToken extends BaseEntity {

    @Column(name="used_token",nullable = false)
    private boolean usedToken;

    @Column(name="token",nullable = false,unique = true)
    private String token;

    @Column(name="expiry_date")
    private LocalDateTime expiryDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id",nullable = false)
    private User user;
}
