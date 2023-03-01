package com.thoughtworks.ssr.infrastructure.persistence.imagescan.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Table(name = "image_scan_job")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@FieldNameConstants
public class ImageScanCommandEntity {

    @Id
    @Column(name = "pj_id")
    private Long pjId;

    @Column(name = "pj_name")
    private String pjName;

    @Column(name = "type_option")
    private int typeOption;

    @Column(name = "create_time")
    private String createTime;

    public Long getPjId() {
        return pjId;
    }

    public void setPjId(Long pjId) {
        this.pjId = pjId;
    }

    public String getPjName() {
        return pjName;
    }

    public void setPjName(String pjName) {
        this.pjName = pjName;
    }

    public int getTypeOption() {
        return typeOption;
    }

    public void setTypeOption(int typeOption) {
        this.typeOption = typeOption;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}


