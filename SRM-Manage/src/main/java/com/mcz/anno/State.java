package com.mcz.anno;

import com.mcz.validation.StateValidate;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {StateValidate.class})
@Target( ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface State {
    //校验不通过时的提示信息
    String message() default "状态只能为已发布或者草稿";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
