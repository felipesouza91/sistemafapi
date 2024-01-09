package com.sistemaf.domain.exception;

public class FileServiceException extends BusinessException {

    private static final long serialVersionUID = 1L;
    public FileServiceException(String msg) {
        super(msg);
    }
    public FileServiceException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
