package com.bookory.exception;

public class EntityNotFoundException extends AppException{

    public EntityNotFoundException(){
        super();
    }

    public EntityNotFoundException(String msg){
        super(msg);
    }

    public EntityNotFoundException(String msg, Object... params){
        super(msg, params);
    }

}
