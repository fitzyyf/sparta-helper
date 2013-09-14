/*
 * Copyright © 2012-2013 mumu@yfyang. All Rights Reserved.
 */

package io.github.sparta.servlets;

/**
 * <p>
 * ajax请求返回信息
 * </p>
 *
 * @param <E> 数据格式
 * @author mumu@yfyang
 * @version 1.0 2013-08-10 12:27 PM
 * @since JDK 1.5
 */
public class AjaxMessage<E> {

    /** 请求消息处理状态 */
    private enum MessageStatus {
        /** 正常 */
        OK,
        /** 发生内部错误 */
        ERROR,
        /** 处理失败 */
        FAILURE,
        /** 没有数据 */
        NODATA,
        /** 禁止访问 */
        FORBIDDEN,
        /** 没有登录 */
        NOLOGIN

    }

    /** 没有权限访问的提示语 */
    private static final String FORBIDDEN_MSG = "403,无权访问！";
    /** 没有权限访问的提示语 */
    private static final String NODATA_MSG = "您好，你所请求的内容为空!";
    /** 没有登录的提示语 */
    private static final String NOLOGIN_MSG = "您好，你是不是没有登录?只有登录后才能访问。";
    /** 返回带的消息数据 */
    private final E e;
    /** 消息提示语 */
    private final String message;
    /** 消息状态 */
    private final MessageStatus status;
    /** 异常 */
    private final Exception exception;

    private AjaxMessage(E e, String message, MessageStatus status) {
        this.e = e;
        this.message = message;
        this.status = status;
        this.exception = null;
    }

    private AjaxMessage(E e, String message, MessageStatus status, Exception exception) {
        this.e = e;
        this.message = message;
        this.status = status;
        this.exception = exception;
    }

    /**
     * 返回处理正常的消息内容
     *
     * @param message 消息提示
     * @param data    消息数据
     * @param <E>     数据泛型类型
     * @return 消息内容
     */
    public static <E> AjaxMessage ok(String message, E data) {
        return new AjaxMessage<E>(data, message, MessageStatus.OK);
    }

    /**
     * 返回处理正常的消息内容
     *
     * @param message 消息提示
     * @return 消息内容
     */
    public static <E> AjaxMessage ok(String message) {
        return ok(message, null);
    }

    public static AjaxMessage developing() {

        return ok("正在开发中...", null);
    }
    /**
     * 返回没有数据的消息内容
     *
     * @param data 消息数据
     * @param <E>  数据泛型类型
     * @return 消息内容
     */
    public static <E> AjaxMessage nodata(E data) {
        return new AjaxMessage<E>(data, NODATA_MSG, MessageStatus.OK);
    }

    /**
     * 返回没有数据的消息内容
     *
     * @return 消息内容
     */
    public static <E> AjaxMessage nodata() {
        return ok(NODATA_MSG, null);
    }

    /**
     * 返回没有登录时消息内容
     *
     * @param data 消息数据
     * @param <E>  数据泛型类型
     * @return 消息内容
     */
    public static <E> AjaxMessage nologin(E data) {
        return new AjaxMessage<E>(data, NOLOGIN_MSG, MessageStatus.OK);
    }

    /**
     * 返回没有登录时的消息内容
     *
     * @return 消息内容
     */
    public static <E> AjaxMessage nologin() {
        return ok(NOLOGIN_MSG, null);
    }

    /**
     * 返回禁止访问消息内容
     *
     * @param data 消息数据
     * @param <E>  数据泛型类型
     * @return 消息内容
     */
    public static <E> AjaxMessage forbidden(E data) {
        return new AjaxMessage<E>(data, FORBIDDEN_MSG, MessageStatus.OK);
    }

    /**
     * 返回禁止访问的消息内容
     *
     * @return 消息内容
     */
    public static <E> AjaxMessage forbidden() {
        return ok(FORBIDDEN_MSG, null);
    }

    /**
     * 返回处理错误的消息内容
     *
     * @param message   消息提示
     * @param exception 异常
     * @return 消息内容
     */
    public static <E> AjaxMessage error(String message, Exception exception) {
        return error(message, null, exception);
    }

    /**
     * 返回处理错误的消息内容
     *
     * @param message   消息提示
     * @param exception 异常
     * @param data      数据
     * @return 消息内容
     */
    public static <E> AjaxMessage error(String message, E data, Exception exception) {
        return new AjaxMessage<E>(data, message, MessageStatus.ERROR, exception);
    }

    /**
     * 返回处理失败的消息内容
     *
     * @param message   消息提示
     * @param exception 异常
     * @return 消息内容
     */
    public static <E> AjaxMessage failure(String message, Exception exception) {
        return failure(message, null, exception);
    }

    /**
     * 返回处理失败的消息内容
     *
     * @param message   消息提示
     * @param exception 异常
     * @param data      数据
     * @return 消息内容
     */
    public static <E> AjaxMessage failure(String message, E data, Exception exception) {
        return new AjaxMessage<E>(data, message, MessageStatus.ERROR, exception);
    }

    public E getE() {
        return e;
    }

    public String getMessage() {
        return message;
    }

    public MessageStatus getStatus() {
        return status;
    }

}
