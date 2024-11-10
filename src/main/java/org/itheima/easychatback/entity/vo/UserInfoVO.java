package org.itheima.easychatback.entity.vo;


import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfoVO implements Serializable {


    private String userId;
    private String nickName;
    private Integer sex;

    private String joinType;

    private String personalSignature;

    private String areaCode;

    private String areaName;

    private String Token;

    private Boolean admin;

    private Integer contactStatus;

}
