package com.bookory.exception;

import com.bookory.utils.MessageBundleUtils;

public class AppException extends RuntimeException{
    public AppException(){
        super();
    }

    public AppException(String msg){
        super(MessageBundleUtils.getMessage(msg));
    }

    public AppException(String msg, Object... params){
        super(MessageBundleUtils.getMessage(msg, params));
    }


}
