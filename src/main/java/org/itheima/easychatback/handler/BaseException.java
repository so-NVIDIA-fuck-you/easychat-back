package org.itheima.easychatback.handler;

import org.itheima.easychatback.entity.enums.ResponseCodeEnum;

/**
 * 业务异常
 */
public class BaseException extends RuntimeException {

    public BaseException() {
    }

    public BaseException(String msg) {
        super(msg);
    }

}
