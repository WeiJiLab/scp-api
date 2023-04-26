package com.thoughtworks.ssr.domain.project.exception;

import com.thoughtworks.ssr.common.exception.BaseException;

public class K8sClusterException extends BaseException {
    public K8sClusterException(Type type, String message) {
        super(type, message);
    }

    public static K8sClusterException notFound() {
        return new K8sClusterException(Type.NOT_FOUND, "k8s_cluster_not_found");
    }
}
