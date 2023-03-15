package com.thoughtworks.ssr.domain.imagescan.repository;

import com.thoughtworks.ssr.domain.imagescan.model.UnfixImage;

public interface UnfixImageRepository {
    UnfixImage save(UnfixImage unfixImage);

    UnfixImage findByProjectId(Long projectId);
}
