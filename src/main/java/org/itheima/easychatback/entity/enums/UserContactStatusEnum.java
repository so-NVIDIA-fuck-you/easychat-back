package org.itheima.easychatback.entity.enums;

import org.itheima.easychatback.Utils.StringTools;

public enum UserContactStatusEnum {
    NOT_FRIEND(0,"非好友"),
    FRIEND(1,"好友"),
    DEL(2,"已删除好友"),
    DEL_BE(3,"被好友删除"),
    BLACKLIST(4,"已拉黑好友"),
    BLACKLIST_BE(5,"已被好友拉黑");

    private Integer status;
    private String desc;

    UserContactStatusEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public static UserContactStatusEnum getByStatus(String status) {
        try {
            if(StringTools.isEmpty(status))
            {
                return null;
            }
            return UserContactStatusEnum.valueOf(status.toUpperCase());
        } catch (Exception e) {
            return null;
        }

    }

    public  static UserContactStatusEnum getByStatus(Integer status) {
        for(UserContactStatusEnum e : UserContactStatusEnum.values()) {
            if(e.getStatus().equals(status)) {
                return e;
            }
        }
        return null;
    }

    public Integer getStatus() {return status;}
    public String getDesc() {return desc;}

}
