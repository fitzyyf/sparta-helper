/*
 * Copyright © 2012-2013 mumu@yfyang. All Rights Reserved.
 */

package io.github.sparta.helpers.tree;

import java.util.HashSet;
import java.util.Set;


/**
 * 应用于自身关联的无级树型字典表.
 *
 * 实际中使用时，实现类需要使用导入javax.persistence.Column
 * 并对name字段进行如下注释
 * 我们考虑使用NamedEntityBean的情况，很可能是除了id就只有name字段的情况
 * 这样，name重复，或者为空，就没有实际意义了，所以在进行这样的解释
 *
 * getParent()获得上级节点
 * getChildren()获得所有下级子节点
 *
 * @author Lingo
 * @since 2007-09-15
 * @param <T> 本身子表关联，T代表的就是本身的类型
 */
@SuppressWarnings("UnusedDeclaration")
public class LongTreeNode<T extends LongTreeNode> implements TreeNode<T> {
    private static final long serialVersionUID = -516380726188216153L;

    // ==========================================
    // entity field
    // ==========================================
    /** id. */
    private Long id = null;

    /** name. */
    private String name = null;

    /** 排序. */
    private Integer theSort = null;

    /**
     * parent.
     */
    private T parent = null;

    /** children. */
    private Set<T> children = new HashSet<T>(0);

    // ==========================================
    // transient field
    // ==========================================

    /** 提示. */
    private String qtip = null;

    /** 可拖拽. */
    private boolean draggable = true;

    /** 可编辑. */
    private boolean allowEdit = true;

    /** 可删除. */
    private boolean allowDelete = true;

    /** 允许有子节点. */
    private boolean allowChildren = true;

    /** 图标. */
    private String iconCls = null;

    /** 上级节点id. */
    private Long parentId = null;

    // ==========================================
    // entity method
    // ==========================================

    /** @return id. */
    public Long getId() {
        return id;
    }

    /** @param id Long. */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return String 名称.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name 名称.
     */
    public void setName(String name) {
        this.name = name;
    }

    /** @return 排序. */
    public Integer getTheSort() {
        return theSort;
    }

    /** @param theSort Integer. */
    public void setTheSort(Integer theSort) {
        this.theSort = theSort;
    }

    /**
     * @return 上级节点.
     */
    public T getParent() {
        return parent;
    }

    /**
     * @param parent LongTreeNode.
    */
    public void setParent(T parent) {
        this.parent = parent;
    }

    /**
     * @return Set 子节点集合.
     */
    public Set<T> getChildren() {
        return children;
    }

    /**
     * @param children 子节点集合.
     */
    public void setChildren(Set<T> children) {
        if (children != null) {
            this.children = children;
        } else {
            System.err.println("children不能为null.");
        }
    }

    // ==========================================
    // transient method
    // ==========================================
    /** @return qtip. */
    public String getQtip() {
        return qtip;
    }

    /** @param qtip String. */
    public void setQtip(String qtip) {
        this.qtip = qtip;
    }

    /** @return text. */
    public String getText() {
        return getName();
    }

    /** @return iconCls. */
    public String getIconCls() {
        return iconCls;
    }

    /** @param iconCls String. */
    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    /** @return draggable. */
    public boolean getDraggable() {
        return draggable;
    }

    /** @param draggable boolean. */
    public void setDraggable(boolean draggable) {
        this.draggable = draggable;
    }

    /** @return allowEdit. */
    public boolean getAllowEdit() {
        return allowEdit;
    }

    /** @param allowEdit boolean. */
    public void setAllowEdit(boolean allowEdit) {
        this.allowEdit = allowEdit;
    }

    /** @return allowDelete. */
    public boolean getAllowDelete() {
        return allowDelete;
    }

    /** @param allowDelete boolean. */
    public void setAllowDelete(boolean allowDelete) {
        this.allowDelete = allowDelete;
    }

    /** @return allowChildren. */
    public boolean getAllowChildren() {
        return allowChildren;
    }

    /** @param allowChildren boolean. */
    public void setAllowChildren(boolean allowChildren) {
        this.allowChildren = allowChildren;
    }

    /** @return parentId. */
    public Long getParentId() {
        return parentId;
    }

    /** @param parentId Long. */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 是否为根节点.
     * 如果getParent() == null就是根节点
     *
     * @return boolean
     */
    public boolean isRoot() {
        return getParent() == null;
    }

    /**
     * 是否为叶子节点.
     * 如果getChildren().size() == 0，就是叶子节点
     *
     * @return boolean
     */
    public boolean isLeaf() {
        // 我们不负责children==null的情况判断
        // 因为首先会对children进行初始化，并在setChildren的时候保证不会让children=null
        // 也就是说，通过正常途径，children永远不会变成null
        // 只有在强行反射的情况下，才有可能让children=null，这种非法情况下发生的问题我们不做处理
        return getChildren().size() == 0;
    }

    /**
     * 不允许将外键关系设置成环状.
     * 就是说，当前的current的子节点中，如果包含parent，就不能把parent设置为current的上级节点
     *
     * @param parentNode TreeNode
     * @return boolean 是否形成环状
     */
    public boolean isDeadLock(T parentNode) {
        return LongTreeUtils.isDeadLock(this, parentNode);
    }
}
