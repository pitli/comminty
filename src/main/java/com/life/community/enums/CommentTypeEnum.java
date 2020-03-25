package com.life.community.enums;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author zp
 * @version v1.0.0
 * @date 2020/3/21
 * @see com.life.community.enums
 */
public enum CommentTypeEnum {
    /**
     *
     */
    QUESTION(1),
    /**
     *
     */
    COMMENT(2);

    private Integer type;

    public static boolean isExist(Integer type) {
        for (CommentTypeEnum commentTypeEnum : CommentTypeEnum.values()) {
            if (type.equals(commentTypeEnum.getType())) {
                return true;
            }
        }
        return false;
    }

    public Integer getType() {
        return type;
    }

    CommentTypeEnum(Integer type) {
        this.type = type;
    }
}
