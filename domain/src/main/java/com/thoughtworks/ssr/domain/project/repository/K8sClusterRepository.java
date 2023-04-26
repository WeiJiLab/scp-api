package com.thoughtworks.ssr.domain.project.repository;

import com.thoughtworks.ssr.domain.project.model.K8sCluster;
import com.thoughtworks.ssr.domain.project.model.Project;
import com.thoughtworks.ssr.domain.project.query.K8sClusterQuery;
import com.thoughtworks.ssr.domain.project.query.ProjectQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface K8sClusterRepository {
    K8sCluster create(K8sCluster kCls);

    K8sCluster update(K8sCluster kCls);

    Optional<K8sCluster> findById(Long id);

    Page<K8sCluster> pageClusters(K8sClusterQuery kClsQuery, Pageable pageable);

    void deleteById(Long id);
}
