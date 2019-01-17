package com.ycl.nettytest.demo.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User: OF1089 杨成龙
 * Date: 2019/1/17
 * Time: 4:25 PM
 * Desc: 类描述
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Session {
    private String userId;
    private String userName;

    @Override
    public String toString() {
        return userId + ":" + userName;
    }
}
