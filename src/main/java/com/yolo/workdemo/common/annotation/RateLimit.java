package com.yolo.workdemo.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimit {

    /**
     * @return
     */
    String code();

    /**
     * 次/分钟，非正数不限制
     *
     * @return
     */
    int permitsPerMinute();

    /**
     * 次/天，非正数不限制
     *
     * @return
     */
    int permitsPerDay();
}
