package com.fiuza.fiap.order.application.handler;

import com.fiuza.fiap.order.core.dto.errors.AlreadyExistExceptionDto;
import com.fiuza.fiap.order.core.dto.errors.BadRequestDto;
import com.fiuza.fiap.order.core.dto.errors.InternalServerErrorDto;
import com.fiuza.fiap.order.core.dto.errors.NotFoundExceptionDto;
import com.fiuza.fiap.order.core.exceptions.AlreadyExistException;
import com.fiuza.fiap.order.core.exceptions.BadRequest;
import com.fiuza.fiap.order.core.exceptions.InternalServerError;
import com.fiuza.fiap.order.core.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<AlreadyExistExceptionDto> handlerAlreadyExistException(
            AlreadyExistException exception) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status.value())
                .body(new AlreadyExistExceptionDto(exception.getMessage(), status.value()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<NotFoundExceptionDto> handlerNotFoundException(
            NotFoundException exception) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status.value())
                .body(new NotFoundExceptionDto(exception.getMessage(), status.value()));
    }

    @ExceptionHandler(InternalServerError.class)
    public ResponseEntity<InternalServerErrorDto> handlerInternalServerErrorException(
            InternalServerError exception) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(status.value())
                .body(new InternalServerErrorDto(exception.getMessage(), status.value()));
    }

    @ExceptionHandler(BadRequest.class)
    public ResponseEntity<BadRequestDto> handlerBadRequestException(
            BadRequest exception) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status.value())
                .body(new BadRequestDto(exception.getMessage(), status.value()));
    }
}
