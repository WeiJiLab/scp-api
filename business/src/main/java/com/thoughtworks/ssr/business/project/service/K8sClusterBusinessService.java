package com.thoughtworks.ssr.business.project.service;

import com.thoughtworks.ssr.business.project.usecases.CreateK8sClusterCase;
import com.thoughtworks.ssr.business.project.usecases.UpdateK8sClusterCase;
import com.thoughtworks.ssr.domain.project.model.K8sCluster;
import com.thoughtworks.ssr.domain.project.query.K8sClusterQuery;
import com.thoughtworks.ssr.domain.project.service.K8sClusterService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class K8sClusterBusinessService {
    private final K8sClusterService k8sClusterService;

    public K8sCluster create(long projectId, CreateK8sClusterCase.Request request) {
        var kCls = K8sCluster.builder()
                .projectId(projectId)
                .name(request.getName())
                .kubeconfigContent(request.getKubeconfigContent())
                .build();
        return k8sClusterService.create(kCls);
    }

    public K8sCluster findById(Long id) {
        return k8sClusterService.findById(id);
    }

    public Page<K8sCluster> pageK8sClusters(K8sClusterQuery query, Pageable pageable) {
        return k8sClusterService.pageK8sClusters(query, pageable);
    }

    public void deleteById(Long id) {
        k8sClusterService.deleteById(id);
    }

    public K8sCluster updateK8sCluster(Long id, UpdateK8sClusterCase.Request request) {
        var kCls = k8sClusterService.findById(id);

        if (!ObjectUtils.isEmpty(request.getName())) {
            kCls.setName(request.getName());
        }

        if (!ObjectUtils.isEmpty(request.getKubeconfigContent())) {
            kCls.setKubeconfigContent(request.getKubeconfigContent());
        }

        return k8sClusterService.update(kCls);
    }
}
