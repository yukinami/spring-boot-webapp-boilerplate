package com.github.yukinami.webapp.domain

import com.github.yukinami.webapp.domain.user.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.jdbc.Sql
import spock.lang.Specification

/**
 * @author snowblink on 16/5/26.
 */
@ActiveProfiles("test")
@ContextConfiguration
@DataJpaTest
class UserRepositoryTests extends Specification {

    @Autowired
    private UserRepository repository;

    @Sql(scripts = "find-all-should-return-all-users-fixtures.sql")
    def "find all should return all users"() {
        when:
        def users = repository.findAll()

        then:
        users.size() == 3
    }

}
