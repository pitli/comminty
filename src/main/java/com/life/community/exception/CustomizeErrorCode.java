package com.life.community.exception;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author zp
 * @version v1.0.0
 * @date 2020/3/19
 * @see com.life.community.exception
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {
    /**
     * question ID is NULL
     */
    QUESTION_NOT_FOUND(2001, "你找的问题不在了或已删除，要不换个问题试试？"),
    /**
     * Comment parentId is NULL
     */
    TARGET_PARAM_NOT_FOUND(2002, "未选择任何问题或评论进行回复！"),
    /**
     * 未登录
     */
    NO_LOGIN(2003, "当前操作需要登录，请登录后重试！"),
    /**
     * 服务器异常
     */
    SYS_ERROR(2004, "服务冒烟了，请稍后再试！！！"),
    /**
     * 评论错误
     */
    TYPE_PARAM_WRONG(2005, "评论类型错误或不存在！"),
    /**
     * 评论不存在
     */
    COMMENT_NOT_FOUND(2006, "回复的评论不存在了，要不换个试试？"),
    /**
     * 输入内容为空
     */
    CONTENT_IS_EMPTY(2007, "输入内容不能为空！"),
    /**
     * 读取信息错误
     */
    READ_NOTIFICATION_FAIL(2008, "读取信息错误！"),
    /**
     * 消息不在了或已删除
     */
    NOTIFICATION_NOT_FOUND(2009, "你找的消息不在了或已删除！")
    ;


    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    private String message;
    private Integer code;

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }
}
