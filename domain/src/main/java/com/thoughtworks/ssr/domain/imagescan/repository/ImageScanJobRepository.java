package com.thoughtworks.ssr.domain.imagescan.repository;

import com.thoughtworks.ssr.domain.imagescan.model.ImageScanCommand;

public interface ImageScanJobRepository {

    ImageScanCommand save(ImageScanCommand imageScanCommand);

}
