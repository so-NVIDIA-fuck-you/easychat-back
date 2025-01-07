package org.itheima.easychatback.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2024-11-16
 */
@Data
public class Group_info implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 群ID
     */
    private String group_id;

    /**
     * 群组名
     */
    private String group_name;

    /**
     * 群主Id
     */
    private String group_owner_id;

    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * 群公告
     */
    private String group_notice;

    /**
     * 0:直接加入,1：同意后加入
     */
    private Integer join_type;

    /**
     * 状态1：正常2：解散
     */
    private Boolean status;

    /**
     * 群成员数量
     */
    private Long memberCount;



    @Override
    public String toString() {
        return "Group_info{" +
        "group_id=" + group_id +
        ", group_name=" + group_name +
        ", group_owner_id=" + group_owner_id +
        ", create_time=" + create_time +
        ", group_notice=" + group_notice +
        ", join_type=" + join_type +
        ", status=" + status +
        ", memberCount=" + memberCount +
        "}";
    }
}
