package com.cognizant.springlearn;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SpringLearnApplication.class)
class SpringLearnApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("Spring context loads correctly.");
    }
}
