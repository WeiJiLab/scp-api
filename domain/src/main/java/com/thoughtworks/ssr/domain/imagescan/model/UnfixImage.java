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
public class UnfixImage {
    private Long projectId;

    private String imageURL;

    private String mapURL;

    private String createAt;
}
