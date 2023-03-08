package com.thoughtworks.ssr.domain.imagescan.model;

import com.thoughtworks.ssr.domain.core.enums.ScanType;
import jakarta.validation.constraints.NotNull;
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
public class ImageScanCommand {
    private Long projectId;

//    @NotNull(message = "project name cannot be null")
    private String projectName;

    @NotNull(message = "type option must be provided")
    private ScanType typeOption;

    private String createTime;
}
