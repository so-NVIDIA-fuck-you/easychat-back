package org.itheima.easychatback.entity.vo;

import lombok.Data;
import org.itheima.easychatback.entity.po.Group_info;
import org.itheima.easychatback.entity.po.User_contact;

import java.util.List;

@Data
public class GroupInfoVO {
    private Group_info groupInfo;
    private List<User_contact> userContactList;

}
