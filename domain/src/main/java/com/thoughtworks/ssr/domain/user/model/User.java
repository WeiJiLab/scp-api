package com.thoughtworks.ssr.domain.user.model;

import com.thoughtworks.ssr.domain.role.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像
     */
    private String avatar;

    private Boolean isEmailVerified;

    private Boolean active;

    private Set<Role> roles;

    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    public User(String username, String phoneNumber, String password) {
        this.username = username;
        this.email = phoneNumber;
        this.password = password;
    }

    public User(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.avatar = user.getAvatar();
        this.email = user.getEmail();
        this.active = user.getActive();
        this.roles = user.getRoles();
        this.isEmailVerified = user.getEmailVerified();

        setCreatedAt(user.getCreatedAt() == null ? this.getCreatedAt() : user.getCreatedAt());
        setUpdatedAt(user.getUpdatedAt() == null ? this.getUpdatedAt() : user.getUpdatedAt());
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> authorities) {
        roles = authorities;
    }

    public Boolean getEmailVerified() {
        return isEmailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        isEmailVerified = emailVerified;
    }

    public void removeRole(Role role) {
        roles.remove(role);
    }

    public void markVerificationConfirmed() {
        setEmailVerified(true);
    }
}
