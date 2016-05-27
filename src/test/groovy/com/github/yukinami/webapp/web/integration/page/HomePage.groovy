package com.github.yukinami.webapp.web.integration.page

import geb.Page

/**
 * @author snowblink on 16/5/26.
 */
class HomePage extends Page {

    static url = "/"
    static at = { title == "My website - Home" }
    static content = {
        menu { $("ul.nav.navbar-nav li", 0) }
        sectionTitle { $("div.starter-template h1") }
    }
}
