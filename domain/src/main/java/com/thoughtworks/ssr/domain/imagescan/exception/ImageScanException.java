package com.thoughtworks.ssr.domain.imagescan.exception;

import com.thoughtworks.ssr.common.exception.BaseException;

public class ImageScanException extends BaseException {
    public ImageScanException(Type type, String message) {
        super(type, message);
    }

    public static ImageScanException notFound() {
        return new ImageScanException(Type.NOT_FOUND, "image_job_not_found");
    }
}

