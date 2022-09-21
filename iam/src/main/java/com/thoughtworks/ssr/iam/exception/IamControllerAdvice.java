package com.thoughtworks.ssr.iam.exception;

import com.thoughtworks.ssr.common.payload.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.FORBIDDEN;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class IamControllerAdvice {

    private final MessageSource messageSource;

    @ExceptionHandler(BaseAuthException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResponse handleBaseException(BaseAuthException ex, WebRequest request) {
        return buildError(ex, request);
    }

    @ExceptionHandler({MissingServletRequestParameterException.class,
            HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResponse handleHttpClientErrorException(Exception e, WebRequest request) {
        return buildError(e, request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResponse processValidationError(MethodArgumentNotValidException ex, WebRequest request) {
        var result = ex.getBindingResult();
        var allErrors = result.getAllErrors();
        return ApiResponse.failed(processAllErrors(allErrors), ex.getClass().getName(), resolvePathFromWebRequest(request));
    }

    @ExceptionHandler({
            AccessDeniedException.class,
            InvalidTokenRequestException.class,
            AuthenticationException.class,
            BadCredentialsException.class
    })
    @ResponseBody
    @ResponseStatus(FORBIDDEN)
    public ApiResponse handleAccessDeniedException(RuntimeException ex) {
        return ApiResponse.failed(ex.getMessage());
    }

    private ApiResponse buildError(Exception exception, WebRequest request) {
        return ApiResponse.failed(
                exception.getMessage(),
                exception.getClass().getName(),
                resolvePathFromWebRequest(request)
        );
    }

    private List<String> processAllErrors(List<ObjectError> allErrors) {
        return allErrors.stream()
                .map(this::resolveLocalizedErrorMessage)
                .collect(Collectors.toList());
    }

    private String resolveLocalizedErrorMessage(ObjectError objectError) {
        var currentLocale = LocaleContextHolder.getLocale();
        var localizedErrorMessage = messageSource.getMessage(objectError, currentLocale);
        log.info(localizedErrorMessage);
        return localizedErrorMessage;
    }

    private String resolvePathFromWebRequest(WebRequest request) {
        try {
            return ((ServletWebRequest) request).getRequest().getAttribute("javax.servlet.forward.request_uri").toString();
        } catch (Exception ex) {
            return null;
        }
    }
}
