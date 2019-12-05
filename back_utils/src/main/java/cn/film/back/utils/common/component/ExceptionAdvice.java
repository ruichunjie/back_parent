package cn.film.back.utils.common.component;

import cn.film.back.utils.common.vo.BaseResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static cn.film.back.utils.enums.BusinessEnum.PRECONDITION_FAILED;

/**
 * description:
 * create_time: 2019/12/5
 * writed by xinyue
 */
@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(value = BindException.class)
    @ResponseStatus(value = HttpStatus.PRECONDITION_FAILED)
    public BaseResponseVo authExceptionHandler( BindException e) {
        List<ObjectError> errors = e.getAllErrors();
        String errMsg = errors.stream().map(ObjectError::getDefaultMessage)
                .reduce((t1,t2)->t1+ ";"+t2).get();
        return BaseResponseVo.fail(PRECONDITION_FAILED.getCode(),"错误"+PRECONDITION_FAILED.getCode()+":"+errMsg);
    }
}
