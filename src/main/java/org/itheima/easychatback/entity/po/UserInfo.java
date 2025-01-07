package org.itheima.easychatback.entity.po;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserInfo {


    private String email;
    private String passWord;
    private String nickName;
    @TableId
    private String userId;

    private Integer sex;

    private Integer joinType;

    private String personalSignature;

    private String areaCode;

    private String areaName;

    private LocalDateTime createTime;
    private Boolean Status;
    private LocalDateTime lastLoginTime;
    private Long lastOffTime;

}
