package edu.hut.bookshop.exception;

import edu.hut.bookshop.util.ResultCode;
import edu.hut.bookshop.util.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @Description: 全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    /**
     * 自定义异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(CustomizeException.class)
    public ResultVO customizeExceptionHandler(CustomizeException e){
        log.error(e.getMessage());
        return new ResultVO(e.getCode(),e.getMsg(),null);
    }

    /**
     * 参数校验异常处理和数据绑定异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class, BindException.class})
    public ResultVO methodArgumentNotValidExceptionHandler(Exception e) {
        BindingResult bindingResult;
        if(e instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException ex= (MethodArgumentNotValidException)e;
            bindingResult = ex.getBindingResult();
        }else {
            BindException ex=(BindException)e;
            bindingResult = ex.getBindingResult();
        }
        ObjectError error = bindingResult.getAllErrors().get(0);
        return new ResultVO(ResultCode.ARGUMENT_NOT_VALID,error.getDefaultMessage(),null);
    }


    /**
     * 其他异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResultVO ExceptionHandler(Exception e){
        log.error(e.getMessage());
        return new ResultVO(ResultCode.UNKNOWN_ERROR,e.getMessage());
    }
}
