package org.itheima.easychatback.handler;

import lombok.extern.slf4j.Slf4j;
import org.itheima.easychatback.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(BaseException ex){
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }


    @ExceptionHandler
    public Result exceptionHandler(SQLIntegrityConstraintViolationException ex){

        //Duplicate entry 'zhangsan' for key 'employee.idx_username'
        String message=ex.getMessage();
        if(message.contains("Duplicate entry"))
        {
          String[] split=message.split(" ");
          String username=split[2];
          String msg=username+ "用户名已存在";
          return  Result.error(msg);
        }else{
            return Result.error("未知错误");
        }



    }

}