package com.thoughtworks.security.scpapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "security_usecase")
@Builder
public class SecurityUseCase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "description")
    private String description;
    @Column(name = "url")
    private String url;
    @Column(name = "name")
    private String name;
    @Column(name = "create_by")
    private String createBy;
    @Column(name = "create_time")
    private Timestamp createTime;
    @Column(name = "update_by")
    private String updateBy;
    @Column(name = "update_time")
    private Timestamp updateTime;
}
