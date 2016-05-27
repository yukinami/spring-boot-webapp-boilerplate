package com.github.yukinami.webapp.web.integration.page

import geb.Page

/**
 * @author snowblink on 16/5/25.
 */
class UserIndexPage extends Page {

    static url = "/users"
    static at = { title == "My website - Users" }
    static content = {
        menu { $("ul.nav.navbar-nav li", 2) }
        homeMenuButton { $("ul.nav.navbar-nav li:first-child a") }
        usersTable { $("table", 0) }
    }

}
