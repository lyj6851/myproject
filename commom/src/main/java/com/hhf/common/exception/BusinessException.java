package com.hhf.common.exception;

import com.hhf.common.exception.model.ResponseInfo;

public class BusinessException extends RuntimeException {
    String errorcode;
    private static final long serialVersionUID = -3124516882843051395L;

    public BusinessException(String message) {
        super(message);
        this.errorcode = "E00001";
    }

    public BusinessException(String message, Throwable e) {
        super(message, e);
        this.errorcode = "E00001";
    }

    public BusinessException(String errorcode, String message) {
        super(message);
        this.errorcode = errorcode;
    }

    public BusinessException(String errorcode, String message, Throwable e) {
        super(message, e);
        this.errorcode = errorcode;
    }

    public BusinessException(ResponseInfo responseInfo) {
        this(responseInfo.getCode(), responseInfo.getMessage());
    }

    public BusinessException(ResponseInfo responseInfo, Throwable e) {
        this(responseInfo.getCode(), responseInfo.getMessage(), e);
    }

    public BusinessException(ResponseInfo responseInfo, Object... argumentArray) {
        this(responseInfo.getCode(), responseInfo.getFormattedMessage(argumentArray));
    }

    public BusinessException(ResponseInfo responseInfo, Throwable e, Object... argumentArray) {
        this(responseInfo.getCode(), responseInfo.getFormattedMessage(argumentArray), e);
    }

    public String getErrorcode() {
        return this.errorcode;
    }

    public void setErrorcode(String errorcode) {
        this.errorcode = errorcode;
    }
}