package com.itlize.korera.ErrorHandler;

public class InvalidInputException extends RuntimeException{
    public InvalidInputException(String str){
        super(str);
    }

}
