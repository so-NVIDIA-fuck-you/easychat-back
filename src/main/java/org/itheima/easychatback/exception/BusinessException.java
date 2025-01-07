package org.itheima.easychatback.exception;

import org.itheima.easychatback.entity.enums.ResponseCodeEnum;
import org.itheima.easychatback.handler.BaseException;

public class BusinessException extends BaseException {

    public BusinessException(String msg) {
        super(msg);
    }


}
