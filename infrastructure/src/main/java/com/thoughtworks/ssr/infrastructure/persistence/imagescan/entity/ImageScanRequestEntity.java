package com.thoughtworks.ssr.infrastructure.persistence.imagescan.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageScanRequestEntity {
    private String projectName;
    private int typeOption;
}
