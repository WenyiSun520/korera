package com.itlize.korera.ErrorHandler;

public class PathVariableNotFound extends RuntimeException{
    public PathVariableNotFound(String pathVariable){
        super("Can't find the pathVariable: "+pathVariable+". Please double check your URL");

    }
}
