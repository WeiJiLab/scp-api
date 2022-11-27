package com.thoughtworks.ssr.domain.admin.model;

import com.thoughtworks.ssr.domain.role.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Admin implements Serializable {

    private static final long serialVersionUID = 3270338571911098940L;

    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 手机号
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

    private Boolean active;

    private Set<Role> roles;

    private OffsetDateTime createdTime;
    private OffsetDateTime updatedTime;

    public Admin(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Admin(Admin user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.avatar = user.getAvatar();
        this.active = user.getActive();
        this.roles = user.getRoles();

        setCreatedTime(user.getCreatedTime() == null ? this.getCreatedTime() : user.getCreatedTime());
        setUpdatedTime(user.getUpdatedTime() == null ? this.getUpdatedTime() : user.getUpdatedTime());
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

    public void addRoles(Role role) {
        roles.add(role);
    }

    public void addRoles(Set<Role> roles) {
        roles.forEach(this::addRoles);
    }

    public void removeRole(Role role) {
        roles.remove(role);
    }

}
