package com.thoughtworks.ssr.domain.scan.exception;

import com.thoughtworks.ssr.common.exception.BaseException;

public class ScanTaskException extends BaseException {
    public ScanTaskException(Type type, String message) {
        super(type, message);
    }

    public static ScanTaskException notFound() {
        return new ScanTaskException(Type.NOT_FOUND, "scan_task_not_found");
    }
}
