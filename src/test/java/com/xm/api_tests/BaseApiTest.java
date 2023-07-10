package com.xm.api_tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xm.utils.Config;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeClass;

@Slf4j
public class BaseApiTest {

    private Config config;

    private ObjectMapper objectMapper;

    @BeforeClass
    void setupConfig() {
        config = new Config("app.properties");
    }

    protected String getUrl() {
        return config.get("apiUrl");
    }

    protected ObjectMapper getObjectMapper() {
        if (null == objectMapper) {
            objectMapper = new ObjectMapper();
        }
        return objectMapper;
    }

}
