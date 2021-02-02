package com.thoughtworks.security.scpapi.entity;

import javax.persistence.Column;
import java.sql.Timestamp;

//@Builder
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity(name = "scan_result")
@Deprecated
public class ComplianceResultEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
    private Long id;
    @Column(name = "scan_task_id")
    private Long scanTaskId;
    @Column(name = "result_path")
    private String resultPath;
    @Column(name = "created_at")
    private Timestamp createAt;
    @Column(name = "updated_at")
    private Timestamp updateAt;
    @Column(name = "result")
    private Integer result;
    @Column(name = "use_case_id")
    private Long useCaseId;
}
