package com.thoughtworks.ssr.domain.project.repository;

import com.thoughtworks.ssr.domain.project.model.Project;
import com.thoughtworks.ssr.domain.project.query.ProjectQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProjectRepository {
    Project create(Project project);

    Project update(Project project);

    Optional<Project> findById(Long id);

    Page<Project> pageProjects(ProjectQuery projectQuery,Pageable pageable);

    void deleteById(Long id);
}
