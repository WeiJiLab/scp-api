package com.thoughtworks.security.scpapi.entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "security_scan_result")
@ApiModel(value = "合规检测结果信息")
@Builder
public class SecurityResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "usecase_id")
    private Long useCaseId;

    @Column(name = "result")
    private Integer result;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "create_time")
    private Timestamp createTime;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "update_time")
    private Timestamp updateTime;
}
