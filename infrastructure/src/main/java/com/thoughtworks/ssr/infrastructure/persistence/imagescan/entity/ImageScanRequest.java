package com.thoughtworks.ssr.infrastructure.persistence.imagescan.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageScanRequest {
    private String pj_name;
    private int type_option;
}
