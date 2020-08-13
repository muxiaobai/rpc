package io.github.muxiaobai.spring_boot.common.session;

import java.lang.annotation.*;

/**

 * @version V1.0

 * @date 2019/6/19/019 13:40
*/
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {
}