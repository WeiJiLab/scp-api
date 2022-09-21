package com.thoughtworks.ssr.service.impl;

import com.thoughtworks.ssr.domain.securitytool.model.SecurityTool;
import com.thoughtworks.ssr.domain.securitytool.model.SecurityToolCategory;
import com.thoughtworks.ssr.domain.securitytool.service.SecurityToolService;
import com.thoughtworks.ssr.infrastructure.persistence.securitytool.entity.SecurityToolEntity;
import com.thoughtworks.ssr.infrastructure.persistence.securitytool.repository.SecurityToolJpaRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SecurityToolServiceImplTest {

    @Autowired
    private SecurityToolService securityToolService;

    @MockBean
    private SecurityToolJpaRepository securityToolRepository;

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

    private SecurityToolEntity getSecurityToolEntity() {
        var securityToolEntity = SecurityToolEntity.builder()
                .id(1L)
                .category(SecurityToolCategory.SCA)
                .description("SCA")
                .name("SCA-Test")
                .metadata("data")
                .build();
        securityToolEntity.setCreatedAt(offsetDateTime);
        securityToolEntity.setUpdatedAt(offsetDateTime);

        return securityToolEntity;
    }

    private List<SecurityToolEntity> getSecurityToolEntities() {
        return List.of(getSecurityToolEntity());
    }

    @Nested
    public class CreateSecurityToolTest {
        @Test
        public void should_create_SecurityTool_when_given_SecurityTool() {

            // given
            var createSecurityTool = getSecurityTool();
            createSecurityTool.setId(null);
            var securityToolEntity = getSecurityToolEntity();

            // when
            when(securityToolRepository.existsByName(anyString())).thenReturn(false);
            when(securityToolRepository.saveAndFlush(any())).thenReturn(securityToolEntity);

            // then
            var result = securityToolService.create(createSecurityTool);
            assertEquals(1L, result.getId());
            assertEquals(securityToolEntity.getCategory(), result.getCategory());
            assertEquals(securityToolEntity.getName(), result.getName());
            assertEquals(securityToolEntity.getMetadata(), result.getMetadata());
            assertEquals(securityToolEntity.getDescription(), result.getDescription());
        }

        @Test
        public void should_throw_exception_when_given_SecurityTool_name_is_exist() {

            // given
            var createSecurityTool = getSecurityTool();
            createSecurityTool.setId(null);

            // when
            when(securityToolRepository.existsByName(anyString())).thenReturn(true);

            // then
            assertThrows(RuntimeException.class,
                    () -> securityToolService.create(createSecurityTool));
        }
    }


    @Nested
    public class FindByIdTest {
        @Test
        public void should_create_SecurityTool_when_given_SecurityTool() {

            // given
            var createSecurityTool = getSecurityTool();
            createSecurityTool.setId(null);
            var securityToolEntity = getSecurityToolEntity();

            // when
            when(securityToolRepository.findById(anyLong())).thenReturn(Optional.of(securityToolEntity));

            // then
            var result = securityToolService.findById(1L);
            assertEquals(1L, result.getId());
            assertEquals(securityToolEntity.getCategory(), result.getCategory());
            assertEquals(securityToolEntity.getName(), result.getName());
            assertEquals(securityToolEntity.getMetadata(), result.getMetadata());
            assertEquals(securityToolEntity.getDescription(), result.getDescription());
        }

        @Test
        public void should_throw_exception_when_given_SecurityTool_name_is_exist() {

            // when
            when(securityToolRepository.findById(anyLong())).thenReturn(Optional.empty());

            // then
            assertThrows(RuntimeException.class,
                    () -> securityToolService.findById(1L));
        }
    }

    @Nested
    public class FindAllTest {
        @Test
        public void should_create_SecurityTool_when_given_SecurityTool() {

            // given
            var securityToolEntities = getSecurityToolEntities();

            // when
            when(securityToolRepository.findAll()).thenReturn(securityToolEntities);

            // then
            var result = securityToolService.findAll();

            assertEquals(1, result.size());
            assertEquals(1L, result.get(0).getId());
            assertEquals(securityToolEntities.get(0).getCategory(), result.get(0).getCategory());
            assertEquals(securityToolEntities.get(0).getName(), result.get(0).getName());
            assertEquals(securityToolEntities.get(0).getMetadata(), result.get(0).getMetadata());
            assertEquals(securityToolEntities.get(0).getDescription(), result.get(0).getDescription());

        }
    }

}
