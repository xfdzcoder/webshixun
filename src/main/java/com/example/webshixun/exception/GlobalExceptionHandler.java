package com.example.webshixun.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
//
//    /**
//     * 全局登录异常处理
//     * @param exception 登录抛出的异常
//     * @return 提示信息
//     */
//    @ExceptionHandler(NotLoginException.class)
//    public Result<Object> handlerNotLoginException(NotLoginException exception) {
//        // 打印日志
//        log.error(exception.getMessage(), exception);
//        // 判断登录异常的场景，定制化返回提示
//        String type = exception.getType();
//        switch (type){
//            case NotLoginException.INVALID_TOKEN:
//            case NotLoginException.TOKEN_TIMEOUT:
//                return Result.error("令牌过期");
//            case NotLoginException.BE_REPLACED:
//            case NotLoginException.KICK_OUT:
//                return Result.error("登录取代");
//            default:
//                return Result.error("没有令牌");
//        }
//    }
//
//    /**
//     * 全局权限校验异常处理
//     * @param exception 权限校验异常
//     * @return
//     */
//    @ExceptionHandler(NotPermissionException.class)
//    public Result<String> handlerNotPermissionException(NotPermissionException exception){
//        // 打印日志
//        log.error(exception.getMessage(), exception);
//        return Result.error("权限错误");
//    }

}
