package device.manager.exceptions;

import lombok.extern.slf4j.Slf4j;
import device.manager.dtos.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class ExceptionsHandler {

    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleRecordNotFoundException(RecordNotFoundException e) {
        return new ErrorDTO(e.getMessage(), LocalDateTime.now());
    }


    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleBadRequestException(BadRequestException e) {
        if (e.getErrors() != null) {
            return new ErrorDTO(e.getMessage() + ": " + e.getErrors().stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.joining(". ")), LocalDateTime.now());
        }
        return new ErrorDTO(e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return new ErrorDTO("The server did not understand the request. Check the correct format of the JSON passed, their keys and values.", LocalDateTime.now());
    }

    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleIOException(IOException e) {
        return new ErrorDTO("There is error with the file provided", LocalDateTime.now());
    }

    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleNoResourceFoundException(NoResourceFoundException e) {
        return new ErrorDTO("The endpoint " + e.getResourcePath() + " not found", LocalDateTime.now());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO handleRuntimeException(RuntimeException e) {
        log.error("An error occurred", e);
        return new ErrorDTO("The server encountered an error. The error is reported to the developer.", LocalDateTime.now());
    }
}
