package com.thoughtworks.ssr.domain.iam.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    private Long id;

    private ERole role;

    public boolean isAdmin() {
        return this.role.equals(ERole.ROLE_ADMIN);
    }

    public static boolean isAdminRole(Role role) {
        return role.getRole().equals(ERole.ROLE_ADMIN);
    }
}