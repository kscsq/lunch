package ru.kscsq.lunch.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ru.kscsq.lunch.util.ValidationUtil;
import ru.kscsq.lunch.util.exception.ApplicationException;
import ru.kscsq.lunch.util.exception.ErrorInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@ControllerAdvice(annotations = RestController.class)
@Order(Ordered.HIGHEST_PRECEDENCE + 5)
public class ExceptionInfoHandler {
    private static Logger log = LoggerFactory.getLogger(ExceptionInfoHandler.class);

    @Autowired
    private MessageUtil messageUtil;

    @ResponseStatus(value = HttpStatus.CONFLICT)  // 409
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public ErrorInfo conflict(HttpServletRequest req, DataIntegrityViolationException e) {
        return logAndGetErrorInfo(req, e, true);
    }

    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)  // 422
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ErrorInfo bindValidationError(HttpServletRequest req, BindingResult result) {
        return logAndGetValidationErrorInfo(req, result);
    }

    @ExceptionHandler(ApplicationException.class)
    @ResponseBody
    public ResponseEntity<ErrorInfo> applicationError(HttpServletRequest req, ApplicationException appEx) {
        return getErrorInfoResponseEntity(req, appEx, appEx.getMsgCode(), appEx.getHttpStatus(), appEx.getArgs());
    }

    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)  // 422
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ErrorInfo restValidationError(HttpServletRequest req, MethodArgumentNotValidException e) {
        return logAndGetValidationErrorInfo(req, e.getBindingResult());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ErrorInfo handleError(HttpServletRequest req, Exception e) {
        return logAndGetErrorInfo(req, e, true);
    }

    private ErrorInfo logAndGetValidationErrorInfo(HttpServletRequest req, BindingResult result) {
        String[] details = result.getAllErrors().stream()
                .map(fe -> messageUtil.getMessage(fe))
                .toArray(String[]::new);

        return logAndGetErrorInfo(req, "ValidationException", details);
    }

    private static ErrorInfo logAndGetErrorInfo(HttpServletRequest req, Exception e, boolean logException) {
        Throwable rootCause = ValidationUtil.getRootCause(e);
        if (logException) {
            log.error("Exception at request {}" + req.getRequestURL(), rootCause);
        } else {
            log.warn("Exception at request {}: {}", req.getRequestURL() + ": " + rootCause.toString());
        }
        return new ErrorInfo(req.getRequestURL(), rootCause);
    }

    private static ErrorInfo logAndGetErrorInfo(HttpServletRequest req, String cause, String... details) {
        log.warn("{} exception at request {}: {}", cause, req.getRequestURL(), Arrays.toString(details));
        return new ErrorInfo(req.getRequestURL(), cause, details);
    }

    public ResponseEntity<ErrorInfo> getErrorInfoResponseEntity(HttpServletRequest req, Exception e, String msgCode, HttpStatus httpStatus, String... args) {
        log.warn("Application error: {}", ValidationUtil.getRootCause(e).toString());
        ErrorInfo errorInfo = logAndGetErrorInfo(req, ValidationUtil.getRootCause(e).getClass().getSimpleName(), messageUtil.getMessage(msgCode, args));
        return new ResponseEntity<>(errorInfo, httpStatus);
    }
}