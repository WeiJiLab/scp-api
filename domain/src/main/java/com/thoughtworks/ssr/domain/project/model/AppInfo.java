package com.thoughtworks.ssr.domain.project.model;

import com.thoughtworks.ssr.common.serviceity.AESCrypt;
import com.thoughtworks.ssr.domain.core.enums.RepoType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.ObjectUtils;

import java.time.OffsetDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppInfo {
    private Long id;
    private Long projectId;
    private String name;
    private String description;
    private String repo;
    private String branch;
    private String username;
    private String password;
    private RepoType repoType;
    private String codePath;
    private String workdir;

    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    public String getDeCryptPassword() {
        return AESCrypt.decrypt(password);
    }

    public Boolean hasAuthInfo() {
        return !ObjectUtils.isEmpty(username) && !ObjectUtils.isEmpty(password);
    }
}
