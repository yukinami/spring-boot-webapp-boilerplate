package com.github.yukinami.webapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class SpringBootWebAppBoilerplateApplicationTests {

    @Test
    public void contextLoads() {
    }

}
