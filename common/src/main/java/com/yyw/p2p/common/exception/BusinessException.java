package com.yyw.p2p.common.exception;

import com.yyw.p2p.common.result.ResponseEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
自定义异常类：
好处是不需要再ExceptionHandler中对每个抛出的异常写对应的异常处理方法，
因为以后所有的异常都抛出的是BusinessException异常对象，所以只需要写一个对该异常的异常处理方法即可
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessException extends RuntimeException{ //自定义异常必须是一个运行时异常，因为只有运行时异常（unchecked异常）才能运行在抛出时不进行捕获（try-catch或throws）
    private Integer errorCode;
    private String errorMessage;

    public BusinessException(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    public BusinessException(String errorMessage, Integer errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }


    public BusinessException(String errorMessage, Integer errorCode, Throwable cause) { // cause是原始异常对象
        super(cause);
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }


    public BusinessException(ResponseEnum responseEnum) {
        this.errorMessage = responseEnum.getMessage();
        this.errorCode = responseEnum.getCode();
    }


    public BusinessException(ResponseEnum responseEnum, Throwable cause) {
        super(cause);
        this.errorMessage = responseEnum.getMessage();
        this.errorCode = responseEnum.getCode();
    }

}
