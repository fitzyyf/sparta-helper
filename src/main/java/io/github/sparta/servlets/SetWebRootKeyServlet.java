/*
 * Copyright © 2012-2013 mumu@yfyang. All Rights Reserved.
 */

package io.github.sparta.servlets;

import java.io.File;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * 辅助应用获取当前Webapp的物理路径.
 * </p>
 *
 * @author mumu@yfyang
 * @version 1.0 2013-06-14 上午11:38
 * @since JDK 1.5
 */
public class SetWebRootKeyServlet extends HttpServlet {

    private static Logger _logger = LoggerFactory.getLogger(SetWebRootKeyServlet.class);

    @Override
    public void init() throws ServletException {
        super.init();
        String webAppRootKey = getServletContext().getRealPath("/");
        System.setProperty(SporesValues.WEB_ROOT, webAppRootKey);

        // 判断是否存在附件文件夹，如果不存在 则创建
        File file = new File(webAppRootKey + SporesValues.ATTACHMENT);
        if (!file.exists()) {
            boolean createAttachementFolder = file.mkdirs();
            if (createAttachementFolder) {
                _logger.error("附件文件夹创建失败!" + file.getAbsolutePath());
            }
        }
    }
}
