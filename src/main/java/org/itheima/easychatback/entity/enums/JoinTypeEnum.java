package org.itheima.easychatback.entity.enums;


import org.itheima.easychatback.Utils.StringTools;

public enum JoinTypeEnum {
   JOIN(0,"直接加入"),
   APPLY(1,"需要审核");

   private Integer type;
   private String desc;
 JoinTypeEnum(Integer type, String desc) {
     this.type = type;
     this.desc = desc;
 }
    public Integer getType() {return type;}
    public String getDesc() {return desc;}

    public static JoinTypeEnum getIByName(String name) {
        try {
            if(StringTools.isEmpty(name))
            {
                return null;
            }
            return JoinTypeEnum.valueOf(name.toUpperCase());
        } catch (Exception e) {
            return null;
        }
    }

}
