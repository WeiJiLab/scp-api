package com.thoughtworks.ssr.domain.imagescan.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageScanResult {
    private Long id;
    private Long pjId;
    private int typeOption;
    private String sdlc;
    private int resCount;
    private int detailStatus;
    private String result;
    private String timeStamp;
}
