/*
 * Copyright © 2012-2013 mumu@yfyang. All Rights Reserved.
 */

package io.github.sparta.helpers.tree;

import java.io.Serializable;

import java.util.Set;


/**
 * 应用于自身关联的无级树型字典表.
 *
 * 实际中使用时，实现类需要使用导入javax.persistence.Column
 * 并对name字段进行如下注释
 * @Column(unique = true, nullable = false)
 * 我们考虑使用NamedEntityBean的情况，很可能是除了id就只有name字段的情况
 * 这样，name重复，或者为空，就没有实际意义了，所以在进行这样的解释
 *
 * getParent()获得上级节点
 * getChildren()获得所有下级子节点
 *
 * @author Lingo
 * @since 2007-06-06
 * @param <T> 本身子表关联，T代表的就是本身的类型
 */
public interface TreeNode<T extends TreeNode> extends Serializable {
    /**
     * 名称.
     *
     * 实际中使用时，实现类需要使用导入javax.persistence.Column
     * 并对name字段进行如下注释
     * @Column(unique = false, nullable = false)
     * name应该是可以重复的，应该可以允许不同级别的下的分类名称相同
     * 如果有特殊业务要求，则应该按照业务要求进行注释
     *
     * @return String 名称.
     */
    String getName();

    /**
     * @param name 名称.
     */
    void setName(String name);

    /**
     * @return 上级节点.
     */
    T getParent();

    /**
     * @param parent 上级节点.
     */
    void setParent(T parent);

    /**
     * @return Set 子节点集合.
     */
    Set<T> getChildren();

    /**
     * @param childrenIn 子节点集合.
     */
    void setChildren(Set<T> childrenIn);

    /**
     * 是否为根节点.
     * 如果getParent() == null就是根节点
     *
     * @return boolean
     */
    boolean isRoot();

    /**
     * 是否为叶子节点.
     * 如果getChildren().size() == 0，就是叶子节点
     *
     * @return boolean
     */
    boolean isLeaf();

    /**
     * 不允许将外键关系设置成环状.
     * 就是说，当前的current的子节点中，如果包含parent，就不能把parent设置为current的上级节点
     *
     * @param parent TreeNode
     * @return boolean 是否形成环状
     */
    boolean isDeadLock(T parent);

    /*
     * 抽象方法，为了避免Generic生成多个parent的getter和setter方法，提供的回调方法.
     *
     * @param parent Object
     *
     *void solveParent(Object parent);
         */
}
