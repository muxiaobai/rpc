package io.github.muxiaobai.spring_my_demo.mvcframework.annotation;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowried {
    String value() default  "";
}
