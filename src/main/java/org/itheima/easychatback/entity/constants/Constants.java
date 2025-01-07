package org.itheima.easychatback.entity.constants;

import org.itheima.easychatback.entity.enums.UserContactTypeEnum;

public class Constants {
 public static final String REDIS_KEY_CHECK_CODE="easychat:checkcode";

 public static final String REDIS_KEY_WS_USER_HEART_BEAT="easychat:ws:user:heartbeat";

 public static final String REDIS_KEY_WS_TOKEN="easychat:ws:token";

 public static final String REDIS_KEY_WS_TOKEN_USERID="easychat:ws:token:userid";

 public static final Integer REDIS_TIME_OUT=300;

 public static final Integer REDIS_KEY_EXPIRES_DAY=3600*24;

 public static final Integer LENGTH_11=11;

 public static final Integer LENGTH_20=20;

 public static final boolean ENABLE=true;

 public static  final String ROBOT_UID= UserContactTypeEnum.USER.getPrefix()+"robot";

 public static final String REDIS_KEY_SYS_SETTING="easychat:syssetting";

 public  static  final String FILE_FOLDER_FILE="/file/";

 public static final String FILE_FOLDER_AVATAR_NAME="avatar/";

 public  static final  String IMAGE_SUFFIX=".jpg";
 public  static final  String COVER_IMAGE_SUFFIX="_cover.jpg";
    


}
