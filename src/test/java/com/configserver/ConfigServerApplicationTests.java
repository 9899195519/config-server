package com.configserver;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
        "spring.cloud.config.server.git.uri=file:///${user.dir}/src/test/resources/dummy-repo",
        "spring.cloud.config.server.git.clone-on-start=false",
        "server.port=0"
})
class ConfigServerApplicationTests {

    @Test
    void contextLoads() {
        // Just check if the application context starts successfully
    }
}
