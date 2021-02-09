package com.thoughtworks.security.scpapi.controller

import com.github.hippoom.wiremock.contract.verifier.anntation.Contract
import com.thoughtworks.security.scpapi.AbstractWebMvcTest
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@WebMvcTest(controllers = ScanTaskController.class)
class ScanTaskControllerTest extends AbstractWebMvcTest {

    @Test
    @Contract("scanTasks/get_scanTask_list_with_all_is_ok.json")
    void "should get scanTask list with all"() {




        mockMvc.perform(contractVerifier.requestPattern())
                .andExpect(contractVerifier.responseDefinition())
    }

}