package org.itheima.easychatback.entity.po;

import lombok.Data;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 联系人
 * </p>
 *
 * @author ${author}
 * @since 2024-11-16
 */
@Data
public class User_contact implements Serializable {

    private static final long serialVersionUID = 1L;

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



    @Override
    public String toString() {
        return "User_contact{" +
        "user_id=" + user_id +
        ", contact_id=" + contact_id +
        ", contact_type=" + contact_type +
        ", create_time=" + create_time +
        ", status=" + status +
        ", last_update_time=" + last_update_time +
        "}";
    }
}
