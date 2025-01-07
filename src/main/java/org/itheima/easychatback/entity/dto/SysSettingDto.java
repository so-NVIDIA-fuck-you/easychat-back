package org.itheima.easychatback.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.itheima.easychatback.entity.constants.Constants;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class SysSettingDto implements Serializable {

    //最大群组数
    private Integer maxGroupCount=5;
    //最大群组人数
    private Integer maxGroupMemberCount=500;
    private Integer maxImageSize=2;
    private Integer maxVideoSize=5;
    private Integer maxFileSize=5;
    //机器人id
    private String robotId= Constants.ROBOT_UID;
    private String robotNickName="EasyChat";
    private String robotWelcome="欢迎使用EasyChat";
}
