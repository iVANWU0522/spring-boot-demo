package com.ivan.exception.handler.handler;

import com.ivan.exception.handler.exception.JsonException;
import com.ivan.exception.handler.exception.PageException;
import com.ivan.exception.handler.model.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
@Slf4j
public class DemoExceptionHandler {
    private static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(value = JsonException.class)
    @ResponseBody
    public ApiResponse jsonErrorHandler(JsonException exception) {
        log.error("[JsonException]: {}", exception.getMessage());
        return ApiResponse.ofException(exception);
    }

    @ExceptionHandler(value = PageException.class)
    public ModelAndView pageErrorHandler(PageException exception) {
        log.error("[PageException]: {}", exception.getMessage());
        ModelAndView view = new ModelAndView();
        view.addObject("message", exception.getMessage());
        view.setViewName(DEFAULT_ERROR_VIEW);
        return view;
    }
}
