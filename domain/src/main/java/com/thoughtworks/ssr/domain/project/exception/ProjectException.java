package com.thoughtworks.ssr.domain.project.exception;

import com.thoughtworks.ssr.common.exception.BaseException;

public class ProjectException extends BaseException {
    public ProjectException(Type type, String message) {
        super(type, message);
    }

    public static ProjectException notFound() {
        return new ProjectException(Type.NOT_FOUND, "project_not_found");
    }
}
