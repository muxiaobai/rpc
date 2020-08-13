package io.github.muxiaobai.spring_boot.common.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**

 * @version V1.0
 * @date 2019/6/4/004 16:43
 * 用户
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionUser {

    private String id;

    private String name;
    private String userName;

    /**
     * 当前登录用户标识
     */
    private String token;


}
