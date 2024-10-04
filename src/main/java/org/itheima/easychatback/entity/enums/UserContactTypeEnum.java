package org.itheima.easychatback.entity.enums;

import org.itheima.easychatback.Utils.StringTools;

public enum UserContactTypeEnum {
    USER(0,"U","好友"),
    GROUP(1,"G","群");

    private Integer type;
    private String prefix;
    private String desc;

    UserContactTypeEnum(Integer type, String prefix, String desc) {
        this.type = type;
        this.prefix = prefix;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }
    public String getPrefix() {
        return prefix;
    }
    public String getDesc() {
        return desc;
    }

    public static UserContactTypeEnum getByName(String name)
    {
        try{
            if(StringTools.isEmpty(name))
            {
                return null;
            }
            return UserContactTypeEnum.valueOf(name.toUpperCase());
        }catch (Exception e)
        {
            return null;
        }
    }

    public static UserContactTypeEnum getByPrefix(String prefix)
    {
        try{
            if(StringTools.isEmpty(prefix))
            {
                return null;
            }
            prefix=prefix.substring(0,1);
            for(UserContactTypeEnum type:UserContactTypeEnum.values())
            {
                if(type.getPrefix().equals(prefix))
                {
                    return type;
                }
            }
            return null;
        }catch (Exception e)
        {
            return null;
        }
    }
}
