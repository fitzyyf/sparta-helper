/*
 * Copyright © 2012-2013 mumu@yfyang. All Rights Reserved.
 */

package io.github.sparta.servlets;

import java.io.File;

/**
 * <p>
 * Spores辅助类.
 * </p>
 *
 * @author mumu@yfyang
 * @version 1.0 2013-06-14 上午11:44
 * @since JDK 1.5
 */
public class SporesHelp {

    /**
     * 给定指定附件的路径来获取webapp目录下的附件的物理地址
     *
     * @param attachmentPath 附件地址
     * @return 物理地址
     */
    public static String getWebappPath(String attachmentPath) {
        return System.getProperty(SporesValues.WEB_ROOT) + SporesValues.ATTACHMENT + File.separator + attachmentPath;
    }

    public static String getWebRoot() {
        return System.getProperty(SporesValues.WEB_ROOT);
    }
}
