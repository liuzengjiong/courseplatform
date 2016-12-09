package com.courseplatform.util;

import java.util.UUID;

/**
 * id生成器
 * @Author chen cy
 * Created by ye on 2016/12/9.
 */
public class IDFactory {
    public static String newID(){
        return UUID.randomUUID().toString();
    }
}
