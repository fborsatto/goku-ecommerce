package br.com.abasteceai.common.exception.config;

import br.com.abasteceai.common.exception.model.BadRequestException;
import br.com.abasteceai.common.exception.model.ConflictException;
import br.com.abasteceai.common.exception.model.NotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.sql.SQLException;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
@ResponseBody
@Log4j2(topic = "CONSOLE_JSON_APPENDER")
public class GlobalExceptionHandler {

    @ExceptionHandler({BadRequestException.class})
    @ResponseStatus(BAD_REQUEST)
    public ExceptionWrapper handleBadRequestException(Exception ex) {
        return new ExceptionWrapper(BAD_REQUEST, ex.getLocalizedMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(BAD_REQUEST)
    public ExceptionWrapper handleBadRequestException(MethodArgumentNotValidException ex) {
        return new ExceptionWrapper(BAD_REQUEST, ex.getBindingResult().getAllErrors());
    }

    @ExceptionHandler
    @ResponseStatus(NOT_FOUND)
    public ExceptionWrapper handleException(NotFoundException ex) {
        return new ExceptionWrapper(NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(CONFLICT)
    public ExceptionWrapper handleException(ConflictException ex) {
        return new ExceptionWrapper(CONFLICT, ex.getMessage());
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(UNPROCESSABLE_ENTITY)
    public ExceptionWrapper handleBadRequestException(HttpMessageNotReadableException ex) {
        return new ExceptionWrapper(UNPROCESSABLE_ENTITY, ex.getLocalizedMessage());
    }

    @ExceptionHandler
    @ResponseStatus(UNPROCESSABLE_ENTITY)
    public ExceptionWrapper handleException(ValidationException ex) {
        return new ExceptionWrapper(UNPROCESSABLE_ENTITY, ex.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(UNPROCESSABLE_ENTITY)
    public ExceptionWrapper handleException(DataIntegrityViolationException ex) {
        return new ExceptionWrapper(UNPROCESSABLE_ENTITY, ex.getCause().getCause().getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(UNPROCESSABLE_ENTITY)
    public ExceptionWrapper handleException(EmptyResultDataAccessException ex) {
        return new ExceptionWrapper(UNPROCESSABLE_ENTITY, "No client found");
    }

    @ExceptionHandler
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ExceptionWrapper handleException(SQLException ex) {
        log.error("Sql exception: ", ex);

        return new ExceptionWrapper(INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ExceptionWrapper handleException(Exception ex) {
        log.error("Unexpected error: ", ex);

        return new ExceptionWrapper(INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(BAD_REQUEST)
    public ExceptionWrapper handleException(MissingServletRequestParameterException ex) {
        return new ExceptionWrapper(BAD_REQUEST, ex.getMessage());
    }
}
