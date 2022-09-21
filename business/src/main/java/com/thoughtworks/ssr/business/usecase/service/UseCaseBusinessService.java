package com.thoughtworks.ssr.business.usecase.service;

import com.thoughtworks.ssr.business.usecase.usecases.CreateUseCaseCase;
import com.thoughtworks.ssr.business.usecase.usecases.UpdateUseCaseCase;
import com.thoughtworks.ssr.domain.usecase.model.UseCase;
import com.thoughtworks.ssr.domain.usecase.service.UseCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class UseCaseBusinessService {
    private final UseCaseService useCaseService;

    public UseCase create(CreateUseCaseCase.Request request) {
        var useCase = UseCase.builder()
                .name(request.getName())
                .description(request.getName())
                .securityToolId(request.getSecurityToolId())
                .scriptPath(request.getScriptPath())
                .build();

       return useCaseService.create(useCase);
    }

    public UseCase findById(Long id) {
        return useCaseService.findById(id);
    }

    public Page<UseCase> pageUseCase(Pageable pageable) {
        return useCaseService.pageUseCase(pageable);
    }

    public void deleteById(Long id) {
        useCaseService.deleteById(id);
    }

    public UseCase update(Long id, UpdateUseCaseCase.Request request) {
        var useCase = useCaseService.findById(id);
        useCase.setName(request.getName());
        useCase.setScriptPath(request.getScriptPath());
        useCase.setSecurityToolId(request.getSecurityToolId());
        if (ObjectUtils.isEmpty(request.getDescription())) {
            useCase.setDescription(request.getDescription());
        }
       return useCaseService.update(useCase);
    }
}
