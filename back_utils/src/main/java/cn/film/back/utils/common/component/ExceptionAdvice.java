package cn.film.back.utils.common.component;

import cn.film.back.utils.common.vo.BaseResponseVo;
import cn.film.back.utils.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.List;

import static cn.film.back.utils.enums.BusinessEnum.*;

/**
 * description: 统一异常处理
 * create_time: 2019/12/5
 * writed by xinyue
 */
@Slf4j
@RestControllerAdvice
@ResponseBody
public class ExceptionAdvice {

    /**
     * 入参错误
     * @param e
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public BaseResponseVo authExceptionHandler( BindException e) {
        List<ObjectError> errors = e.getAllErrors();
        String errMsg = errors.stream().map(ObjectError::getDefaultMessage)
                .reduce((t1,t2)->t1+ ";"+t2).get();
        return BaseResponseVo.fail(BAD_REQUEST.getCode(),"错误"+BAD_REQUEST.getCode()+":"+errMsg);
    }

    /**
     * Get/Post请求方式不受支持
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public BaseResponseVo handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return BaseResponseVo.fail(NOSUPORT.getCode(),"错误"+NOSUPORT.getCode()+":"+NOSUPORT.getMessage());
    }

    /**
     * 请求格式不受支持
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    public BaseResponseVo handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        return BaseResponseVo.fail(UNSUPPORTEDTYPE.getCode(),"错误"+UNSUPPORTEDTYPE.getCode()+":"+UNSUPPORTEDTYPE.getMessage());
    }

    /**
     * 自定义异常
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({BusinessException.class})
    public BaseResponseVo handleBusinessException(BusinessException e) {
        return BaseResponseVo.fail(SUCCESS.getCode(),e.getMessage());
    }

    /**
     * 出现未知异常
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class})
    public BaseResponseVo handleException(SQLException e) {
        return BaseResponseVo.fail(INTERNALSERVERERROR.getCode(), "错误"+INTERNALSERVERERROR.getCode()+":"+INTERNALSERVERERROR.getMessage());
    }

}
