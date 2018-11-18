package com.kid297.mvcframework.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME) //运行时
@Documented
public @interface KidController {
    String value() default "";
}
