package org.itheima.easychatback.entity.query;

import lombok.Data;

import java.util.Date;

@Data
public class UserContactQuery {

    /**
     * 用户ID
     */
    private String user_id;

    /**
     * 联系人ID或者群组ID
     */
    private String contact_id;

    /**
     * 联系人类型 0：好友1：群组
     */
    private Integer contact_type;

    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * 状态 0：非好友 1：好友 2：已删除 3:拉黑
     */
    private Integer status;

    /**
     * 最后登录时间戳
     */
    private Date last_update_time;

    private Boolean QueryUserInfo;
}
