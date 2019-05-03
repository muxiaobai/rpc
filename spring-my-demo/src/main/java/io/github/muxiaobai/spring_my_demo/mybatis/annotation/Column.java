package io.github.muxiaobai.spring_my_demo.mybatis.annotation;

import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Column {
    public String value() default  "";
}
