package com.thoughtworks.ssr.business.usecase.rest;

import com.thoughtworks.ssr.business.common.constants.QueryConstants;
import com.thoughtworks.ssr.business.usecase.service.UseCaseGroupBusinessService;
import com.thoughtworks.ssr.business.usecase.usecases.CreateUseCaseGroupCase;
import com.thoughtworks.ssr.business.usecase.usecases.GetUseCaseGroupCase;
import com.thoughtworks.ssr.business.usecase.usecases.UpdateUseCaseGroupCase;
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
@RequestMapping("/useCaseGroups")
@RequiredArgsConstructor
public class UseCaseGroupBusinessController {
    private final UseCaseGroupBusinessService useCaseGroupBusinessService;

    @PostMapping
    @ResponseStatus(CREATED)
    public CreateUseCaseGroupCase.Response create(@RequestBody @Valid CreateUseCaseGroupCase.Request request) {
        var useCaseGroup = useCaseGroupBusinessService.create(request);
        return CreateUseCaseGroupCase.Response.from(useCaseGroup);
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public GetUseCaseGroupCase.Response findById(@PathVariable Long id) {
        var useCaseGroup = useCaseGroupBusinessService.findById(id);
        return GetUseCaseGroupCase.Response.from(useCaseGroup);
    }

    @GetMapping
    @ResponseStatus(OK)
    public Page<GetUseCaseGroupCase.Response> pageUseCaseGroup(
            @PageableDefault(size = QueryConstants.DEFAULT_PAGE_SIZE) Pageable pageable
    ) {
        return useCaseGroupBusinessService.pageUseCaseGroup(pageable)
                .map(GetUseCaseGroupCase.Response::from);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(OK)
    public void deleteById(@PathVariable Long id) {
        useCaseGroupBusinessService.deleteById(id);
    }

    @PatchMapping("{id}")
    @ResponseStatus(OK)
    public UpdateUseCaseGroupCase.Response update(@PathVariable Long id,
                                                  @Valid @RequestBody UpdateUseCaseGroupCase.Request request
    ) {
        var useCaseGroup = useCaseGroupBusinessService.update(id, request);
        return UpdateUseCaseGroupCase.Response.from(useCaseGroup);
    }
}
