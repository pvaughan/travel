package com.afkl.cases.df.common.exception;

/**
 * Created by pvaughan on 02/12/2016.
 */
public class HttpException extends Exception {
    private String content;

    public HttpException() {
    }

    public HttpException(String message) {
        super(message);
    }


    public HttpException(String message, String content) {
        super(message);
        this.content = content;
    }

    public HttpException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpException(Throwable cause) {
        super(cause);
    }

    public HttpException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public String getContent() {
        return content;
    }
}
