package com.thoughtworks.ssr.domain.scan.exception;

import com.thoughtworks.ssr.common.exception.BaseException;

public class ScanResultException extends BaseException {
    public ScanResultException(Type type, String message) {
        super(type, message);
    }

    public static ScanResultException notFound() {
        return new ScanResultException(Type.NOT_FOUND, "scan_result_not_found");
    }
}
