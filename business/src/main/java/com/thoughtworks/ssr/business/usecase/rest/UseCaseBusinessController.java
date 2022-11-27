package com.thoughtworks.ssr.business.usecase.rest;

import com.thoughtworks.ssr.business.common.constants.QueryConstants;
import com.thoughtworks.ssr.business.usecase.service.UseCaseBusinessService;
import com.thoughtworks.ssr.business.usecase.usecases.CreateUseCaseCase;
import com.thoughtworks.ssr.business.usecase.usecases.GetUseCaseCase;
import com.thoughtworks.ssr.business.usecase.usecases.UpdateUseCaseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/useCases")
@RequiredArgsConstructor
public class UseCaseBusinessController {
    private final UseCaseBusinessService useCaseBusinessService;

    @PostMapping
    @ResponseStatus(CREATED)
    public CreateUseCaseCase.Response create(
            @RequestBody @Valid CreateUseCaseCase.Request request
    ) {
        var useCase = useCaseBusinessService.create(request);
        return CreateUseCaseCase.Response.from(useCase);
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public GetUseCaseCase.Response findById(@PathVariable("id") Long id) {
        var useCase = useCaseBusinessService.findById(id);
        return GetUseCaseCase.Response.from(useCase);
    }

    @GetMapping
    @ResponseStatus(OK)
    public Page<GetUseCaseCase.Response> pageUseCase(
            @PageableDefault(size = QueryConstants.DEFAULT_PAGE_SIZE) Pageable pageable
    ) {
        return useCaseBusinessService.pageUseCase(pageable).map(GetUseCaseCase.Response::from);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(OK)
    public void deleteById(@PathVariable("id") Long id) {
        useCaseBusinessService.deleteById(id);
    }

    @PatchMapping("{id}")
    @ResponseStatus(OK)
    public UpdateUseCaseCase.Response update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUseCaseCase.Request request
    ) {
        var useCase = useCaseBusinessService.update(id, request);
        return UpdateUseCaseCase.Response.from(useCase);
    }
}
