package com.thoughtworks.ssr.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.ssr.JUnitWebAppTest;
import com.thoughtworks.ssr.business.securitytool.usecases.CreateSecurityToolCase;
import com.thoughtworks.ssr.domain.securitytool.model.SecurityTool;
import com.thoughtworks.ssr.domain.securitytool.model.SecurityToolCategory;
import com.thoughtworks.ssr.domain.securitytool.service.SecurityToolService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.OffsetDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@JUnitWebAppTest
public class SecurityToolControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    protected SecurityToolService securityToolService;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final OffsetDateTime offsetDateTime = OffsetDateTime.now();


    private SecurityTool getSecurityTool() {
        return SecurityTool.builder()
                .id(1L)
                .category(SecurityToolCategory.SCA)
                .description("SCA")
                .name("SCA-Test")
                .metadata("data")
                .createdAt(offsetDateTime)
                .updatedAt(offsetDateTime)
                .build();
    }

    private List<SecurityTool> getSecurityTools() {
        return List.of(getSecurityTool());
    }

    private CreateSecurityToolCase.Request getCreateSecurityToolRequest() {
        return CreateSecurityToolCase.Request
                .builder()
                .category(SecurityToolCategory.SCA)
                .description("SCA")
                .name("SCA-Test")
                .metadata("data")
                .build();
    }


    @Tag("Test by get Security Tool")
    @Nested
    public class GetSecurityTool {
        @Test
        public void should_get_one_security_tool_when_given_exist_id() throws Exception {

            // given
            SecurityTool securityTool = getSecurityTool();

            // when
            when(securityToolService.findById(anyLong())).thenReturn(securityTool);

            // then
            mockMvc.perform(get("/tools/1")
                    .characterEncoding("UTF-8"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(1))
                    .andExpect(jsonPath("$.name").value(securityTool.getName()))
                    .andExpect(jsonPath("$.category").value(securityTool.getCategory().name()))
                    .andExpect(jsonPath("$.description").value(securityTool.getDescription()));
        }


        @Test
        public void should_get_security_tool_list() throws Exception {

            // given
            List<SecurityTool> securityTools = getSecurityTools();

            // when
            when(securityToolService.findAll()).thenReturn(securityTools);

            // then
            mockMvc.perform(get("/tools")
                    .characterEncoding("UTF-8"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].id").value(1))
                    .andExpect(jsonPath("$[0].name").value(securityTools.get(0).getName()))
                    .andExpect(jsonPath("$[0].category").value(securityTools.get(0).getCategory().name()))
                    .andExpect(jsonPath("$[0].description").value(securityTools.get(0).getDescription()));
        }
    }


    @Tag("Test by get Security Tool")
    @Nested
    public class CreateSecurityTool {

        @Test
        public void should_create_SecurityTool_when_given_one_CreateSecurityToolRequest() throws Exception {

            // given
            var request = getCreateSecurityToolRequest();

            SecurityTool securityTool = getSecurityTool();

            when(securityToolService.create(any())).thenReturn(securityTool);

            mockMvc.perform(post("/tools")
                    .content(objectMapper.writeValueAsString(request))
                    .contentType("application/json;charset=UTF-8")
                    .characterEncoding("UTF-8"))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id").value(1))
                    .andExpect(jsonPath("$.name").value(securityTool.getName()))
                    .andExpect(jsonPath("$.category").value(securityTool.getCategory().name()))
                    .andExpect(jsonPath("$.description").value(securityTool.getDescription()));
        }


        @Test
        public void should_create_SecurityTool_is_BadRequest_when_given_name_is_null() throws Exception {

            var request = getCreateSecurityToolRequest();
            request.setName(null);
            createIsBadRequest(request);
        }


        @Test
        public void should_create_SecurityTool_is_BadRequest_when_given_sategory_is_null() throws Exception {

            var request = getCreateSecurityToolRequest();
            request.setCategory(null);
            createIsBadRequest(request);
        }

        @Test
        public void should_create_SecurityTool_is_BadRequest_when_given_description_is_null() throws Exception {

            var request = getCreateSecurityToolRequest();
            request.setDescription(null);
            createIsBadRequest(request);
        }

        @Test
        public void should_create_SecurityTool_is_BadRequest_when_given_metadata_is_null() throws Exception {

            var request = getCreateSecurityToolRequest();
            request.setMetadata(null);
            createIsBadRequest(request);
        }

        private void createIsBadRequest(CreateSecurityToolCase.Request request) throws Exception {

            mockMvc.perform(post("/tools")
                    .content(objectMapper.writeValueAsString(request))
                    .contentType("application/json;charset=UTF-8")
                    .characterEncoding("UTF-8"))
                    .andExpect(status().isBadRequest());
        }
    }

}
