package com.life.community.enums;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author zp
 * @version v1.0.0
 * @date 2020/3/22
 * @see com.life.community.enums
 */
public enum NotificationStatusEnum {
    /**
     *未读
     */
    UNREAD(0),
    /**
     * 已读
     */
    READ(1);

    private int status;

    public int getStatus() {
        return status;
    }

    NotificationStatusEnum(int status) {
        this.status = status;
    }
}
