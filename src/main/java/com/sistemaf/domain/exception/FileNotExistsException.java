package com.sistemaf.domain.exception;

public class FileNotExistsException extends BusinessException {

    private static final long serialVersionUID = 1L;
    public FileNotExistsException(String msg) {
        super(msg);
    }
    public FileNotExistsException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
