/*
 * Copyright © 2012-2013 mumu@yfyang. All Rights Reserved.
 */

package io.github.sparta.helpers.tree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 树的工具类.
 * 主要用于LongTreeNode与json之间的转换
 *
 * @author Lingo
 * @since 2007-09-15
 */
public class LongTreeUtils {
    /** * logger. */
    private static Logger logger = LoggerFactory.getLogger(LongTreeUtils.class);

    /** * 工具类需要的保护构造方法. */
    protected LongTreeUtils() {
        logger.info("start");
    }

    /**
     * 不允许将自表外键关系设置成环状.
     * 就是说，当前的current的子节点中，如果包含parent，就不能把parent设置为current的上级节点
     *
     * @param current TreeEntityBean
     * @param parent TreeEntityBean
     * @param <T> TreeEntityBean
     * @return boolean 是否形成环状
     */
    public static <T extends LongTreeNode> boolean isDeadLock(
        LongTreeNode<T> current, LongTreeNode<T> parent) {
        if ((current == null) || (parent == null)
                || current.equals(parent)) {
            return true;
        } else {
            for (T child : current.getChildren()) {
                boolean isDeadLock = isDeadLock(child, parent);

                if (isDeadLock) {
                    return true;
                }
            }
        }

        return false;
    }
}
