package org.itheima.easychatback.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 联系人申请
 * </p>
 *
 * @author ${author}
 * @since 2024-11-16
 */
@Data
public class User_contact_apply implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增ID
     */
    @TableId(value = "apply_id", type = IdType.AUTO)
    private Integer apply_id;

    /**
     * 申请人id
     */
    private String apply_user_id;

    /**
     * 接收人id
     */
    private String receive_user_id;

    /**
     * 联系人类型0：好友1：群组
     */
    private Boolean contact_type;

    /**
     * 联系人群组id
     */
    private String contact_id;

    /**
     * 最后申请时间
     */
    private Long last_apply_time;

    /**
     * 状态0:待处理1：已同意2：已拒绝3：已拉黑
     */
    private Boolean status;

    /**
     * 申请信息
     */
    private String apply_info;


    @Override
    public String toString() {
        return "User_contact_apply{" +
        "apply_id=" + apply_id +
        ", apply_user_id=" + apply_user_id +
        ", receive_user_id=" + receive_user_id +
        ", contact_type=" + contact_type +
        ", contact_id=" + contact_id +
        ", last_apply_time=" + last_apply_time +
        ", status=" + status +
        ", apply_info=" + apply_info +
        "}";
    }
}
