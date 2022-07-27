package com.cihanpacal.dininghall.model.entity;


import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="t_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SQLDelete(sql = "UPDATE t_user SET active = false WHERE id=?")
@Where(clause = "active=true")
public class User extends BaseEntity {

    @Column(name = "first_name",nullable = false)
    private String firstName;

    @Column(name = "last_name",nullable = false)
    private String lastName;

    @Column(name="password",nullable = false)
    private String password;

    @Column(name = "email",nullable = false,unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "role",nullable = false)
    private Role role;

    @Column(name = "enabled",nullable = false)
    private Boolean enabled;

    @Column(name="non_locked",nullable = false)
    private Boolean nonLocked;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    private Set<VerificationToken> verificationTokens;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    private Set<PasswordResetToken> passwordResetTokens;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    private Set<AuthenticationToken> authenticationTokens;


}
