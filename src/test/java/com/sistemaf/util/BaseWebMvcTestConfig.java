package com.sistemaf.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sistemaf.core.SistemFApiProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.TestPropertySource;

@EnableConfigurationProperties(value = SistemFApiProperty.class)
@TestPropertySource("classpath:tests.properties")
public abstract class BaseWebMvcTestConfig {

    @Autowired
    protected ObjectMapper objectMapper;
}
