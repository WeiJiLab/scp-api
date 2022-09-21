package com.thoughtworks.ssr.business.usecase.service;

import com.thoughtworks.ssr.business.usecase.usecases.CreateUseCaseGroupCase;
import com.thoughtworks.ssr.business.usecase.usecases.UpdateUseCaseGroupCase;
import com.thoughtworks.ssr.domain.usecase.model.UseCaseGroup;
import com.thoughtworks.ssr.domain.usecase.service.UseCaseGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class UseCaseGroupBusinessService {
    private final UseCaseGroupService useCaseGroupService;
    public UseCaseGroup create(CreateUseCaseGroupCase.Request request) {

        var newUseCaseGroup = UseCaseGroup.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();

        return useCaseGroupService.create(newUseCaseGroup);
    }

    public UseCaseGroup findById(Long id) {
        return useCaseGroupService.findById(id);
    }

    public Page<UseCaseGroup> pageUseCaseGroup(Pageable pageable) {
        return useCaseGroupService.pageUseCaseGroup(pageable);
    }

    public void deleteById(Long id) {
        useCaseGroupService.deleteById(id);
    }

    public UseCaseGroup update(Long id, UpdateUseCaseGroupCase.Request request) {

        var useCaseGroup = useCaseGroupService.findById(id);

        if (!ObjectUtils.isEmpty(request.getName())) {
            useCaseGroup.setName(request.getName());
        }

        if (!ObjectUtils.isEmpty(request.getDescription())) {
            useCaseGroup.setName(request.getDescription());
        }

        return useCaseGroupService.update(useCaseGroup);
    }
}
