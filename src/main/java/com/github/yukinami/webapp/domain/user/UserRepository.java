package com.github.yukinami.webapp.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author snowblink on 16/5/25.
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
