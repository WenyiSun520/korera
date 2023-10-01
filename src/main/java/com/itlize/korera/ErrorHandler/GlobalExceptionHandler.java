package com.itlize.korera.ErrorHandler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<ErrorObject> handleInvalidInputException(InvalidInputException e){
            ErrorObject obj = new ErrorObject();
            obj.setTimestamp(new Date());
            obj.setStatusCode(400);
            obj.setErrorMsg(e.getMessage());
            return ResponseEntity.status(400).body(obj);
    }
    @ExceptionHandler(PathVariableNotFound.class)
    public ResponseEntity<ErrorObject> handldPathVariableNotFoundException(PathVariableNotFound e){
        ErrorObject obj = new ErrorObject();
        obj.setTimestamp(new Date());
        obj.setStatusCode(400);
        obj.setErrorMsg(e.getMessage());
        return ResponseEntity.status(400).body(obj);
    }

}
