package com.mcz.validation;

import com.mcz.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class StateValidate implements ConstraintValidator<State, String> {
    /**
     *
     * @param value 将来要读取的数据
     * @param constraintValidatorContext 校验器上下文对象
     * @return  如果返回false校验不通过，如果返回true校验通过
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        //校验规则
        if (value == null) {
            return false;
        }
        return value.equals("已发布") || value.equals("草稿");
    }
}
