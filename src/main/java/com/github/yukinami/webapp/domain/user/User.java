package com.github.yukinami.webapp.domain.user;

import com.github.yukinami.webapp.domain.shared.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * @author snowblink on 16/5/24.
 */
@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(length = 50, nullable = false, unique = true)
    private String username;

    @Column(length = 500, nullable = false)
    private String password;

    private String firstName;

    private String lastName;

    private boolean enabled;

}
