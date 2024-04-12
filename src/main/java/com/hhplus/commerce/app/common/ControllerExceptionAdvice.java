package com.hhplus.commerce.app.common;

import com.hhplus.commerce.app.common.exception.InvalidRequestException;
import com.hhplus.commerce.app.common.exception.NotFoundException;
import com.hhplus.commerce.app.common.exception.OutOfStockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * create on 4/12/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@RestControllerAdvice
public class ControllerExceptionAdvice {

  @ExceptionHandler(InvalidRequestException.class)
  public ResponseEntity<ErrorResponse> handleInvalidRequestException(Exception e) {
    log.error("error: ", e);
    ErrorResponse errorResponse = new ErrorResponse("400", e.getMessage());
    return ResponseEntity.badRequest().body(errorResponse);
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorResponse> handleNotFoundException(Exception e) {
    log.error("error: ", e);
    ErrorResponse errorResponse = new ErrorResponse("400", e.getMessage());
    return ResponseEntity.badRequest().body(errorResponse);
  }

  @ExceptionHandler(OutOfStockException.class)
  public ResponseEntity<ErrorResponse> handleOutOfStockException(Exception e) {
    log.error("error: ", e);
    ErrorResponse errorResponse = new ErrorResponse("400", e.getMessage());
    return ResponseEntity.badRequest().body(errorResponse);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ErrorResponse> handleIllegalArgumentException(Exception e) {
    log.error("error: ", e);
    ErrorResponse errorResponse = new ErrorResponse("400", e.getMessage());
    return ResponseEntity.badRequest().body(errorResponse);
  }

}
