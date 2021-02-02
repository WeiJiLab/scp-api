package com.thoughtworks.security.scpapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "security_report")
public class SecurityReportEntity {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "path")
    private String s3KeyName;

    @Column(name = "createTime")
    private Timestamp createTime;

    @Column(name = "createBy")
    private String createBy;
}
