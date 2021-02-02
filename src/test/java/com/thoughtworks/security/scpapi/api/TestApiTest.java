package com.thoughtworks.security.scpapi.api;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TestApiTest {

    private TestApi testApi = new TestApi();

    @Test
    void shouldReturnTest() {
        String result = testApi.test();
        assertThat(result).isEqualTo("test");
    }

}