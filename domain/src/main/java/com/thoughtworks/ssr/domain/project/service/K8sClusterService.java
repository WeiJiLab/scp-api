package com.thoughtworks.ssr.domain.project.service;

import com.thoughtworks.ssr.domain.project.exception.K8sClusterException;
import com.thoughtworks.ssr.domain.project.exception.ProjectException;
import com.thoughtworks.ssr.domain.project.model.K8sCluster;
import com.thoughtworks.ssr.domain.project.query.K8sClusterQuery;
import com.thoughtworks.ssr.domain.project.repository.K8sClusterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class K8sClusterService {
    private final K8sClusterRepository kClsRepo;

    public K8sCluster create(K8sCluster kCls) {
        return kClsRepo.create(kCls);
    }

    public K8sCluster update(K8sCluster kCls) {
        return kClsRepo.update(kCls);
    }

    public K8sCluster findById(Long id) {
        return kClsRepo.findById(id).orElseThrow(K8sClusterException::notFound);
    }

    public Page<K8sCluster> pageK8sClusters(K8sClusterQuery kClsQuery, Pageable pageable) {
        return kClsRepo.pageClusters(kClsQuery, pageable);
    }

    public void deleteById(Long id) {
        try {
            kClsRepo.deleteById(id);
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            throw K8sClusterException.notFound();
        }
    }
}
