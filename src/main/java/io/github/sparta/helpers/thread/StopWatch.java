/*
 * Copyright © 2012-2013 mumu@yfyang. All Rights Reserved.
 */

package io.github.sparta.helpers.thread;

/**
 * <p>
 * 重做一个毫秒级的简单StopWatch轮子。
 * </p>
 *
 * @author mumu@yfyang
 * @version 1.0 2013-08-07 10:24 PM
 * @since JDK 1.5
 */
public class StopWatch {
    private long startTime;

    public StopWatch() {
        startTime = System.currentTimeMillis();
    }

    public long getMillis() {
        return System.currentTimeMillis() - startTime;
    }

    public void reset() {
        startTime = System.currentTimeMillis();
    }
}
