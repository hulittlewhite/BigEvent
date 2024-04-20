package com.itheima.annotations;

import com.itheima.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented // 元注解
@Constraint(validatedBy = {StateValidation.class}) // 指定提供校验规则的类
@Target({ElementType.FIELD}) // 元注解
@Retention(RetentionPolicy.RUNTIME) // 元注解
public @interface State {

    // 提供参数校验失败后的提示信息
    String message() default "state 参数的值只能为已发布或草稿！";

    // 指定分组
    Class<?>[] groups() default {};

    // 负载   获取 @State 注解的附加信息
    Class<? extends Payload>[] payload() default {};

}
