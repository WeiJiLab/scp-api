package com.thoughtworks.ssr.infrastructure.persistence.imagescan.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "image_scan_job")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class ImageScanRequestEntity {

    @Id
    private String pj_id;
    private String pj_name;
    private int type_option;

    @Override
    public String toString() {
        return "ImageScanRequestEntity{" +
                "pj_id='" + pj_id + '\'' +
                ", pj_name='" + pj_name + '\'' +
                ", type_option=" + type_option +
                '}';
    }
}


