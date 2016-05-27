package com.github.yukinami.webapp.web

import com.github.yukinami.webapp.SpringBootWebAppBoilerplateApplication
import com.github.yukinami.webapp.web.integration.page.HomePage
import com.github.yukinami.webapp.web.integration.page.UserIndexPage
import geb.spock.GebReportingSpec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootContextLoader
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.htmlunit.webdriver.MockMvcHtmlUnitDriverBuilder
import org.springframework.web.context.WebApplicationContext

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity

/**
 * @author snowblink on 16/5/25.
 */
@ActiveProfiles("test")
@ContextConfiguration(loader = SpringBootContextLoader.class, classes = SpringBootWebAppBoilerplateApplication)
@WebAppConfiguration
@WithMockUser
class UserIndexIntegrationTests extends GebReportingSpec {

    @Autowired
    WebApplicationContext context

    def setup() {
        browser.driver = MockMvcHtmlUnitDriverBuilder
                .webAppContextSetup(context, springSecurity()).build()
    }

    def "go to users"() {
        when:
        to UserIndexPage

        then:
        at UserIndexPage

        expect:
        menu.hasClass("active")
        usersTable.find("tbody tr").size() == 3 //check record count

    }

    def "click home menu"() {
        when:
        to UserIndexPage

        then:
        homeMenuButton.click(HomePage)
    }
}
