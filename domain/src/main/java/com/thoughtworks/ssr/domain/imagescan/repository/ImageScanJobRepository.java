package com.thoughtworks.ssr.domain.imagescan.repository;

import com.thoughtworks.ssr.domain.imagescan.model.ImageScanCommand;

import java.util.List;

public interface ImageScanJobRepository {

    ImageScanCommand save(ImageScanCommand imageScanCommand);

    List<ImageScanCommand> findAll();
}
