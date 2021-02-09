package com.thoughtworks.security.scpapi

import com.github.hippoom.wiremock.contract.verifier.junit.MockMvcContractVerifier
import org.junit.Rule
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@ActiveProfiles("test")
abstract class AbstractWebMvcTest {

    @Autowired
    protected MockMvc mockMvc

    @Rule
    public final MockMvcContractVerifier contractVerifier = new MockMvcContractVerifier()
}
