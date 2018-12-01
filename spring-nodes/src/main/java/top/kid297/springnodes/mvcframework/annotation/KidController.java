package top.kid297.springnodes.mvcframework.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE})   //注解所修饰范围
@Retention(RetentionPolicy.RUNTIME) //运行时被反射代码所读取
@Documented   //被javadoc工具记录
public @interface KidController {
    String value() default "";
}
