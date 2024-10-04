package org.itheima.easychatback.Utils;

import jodd.util.RandomString;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.itheima.easychatback.entity.constants.Constants;

public class StringTools {


    public static boolean isEmpty(String str) {
        if(null == str || "".equals(str)||"null".equals(str)||"\u0000".equals(str))
        {
          return true;
        }else if("".equals(str.trim()))
        {
            return true;
        }
        return false;
    }

    public static String getUserId() {
        return "U"+getRandomNumber(Constants.LENGTH_11);
    }
    public static String getRandomNumber(Integer count) {
        return RandomStringUtils.random(count,false,true);
    }
    public static String getRandomString(Integer count) {
        return RandomStringUtils.random(count,false,true);
    }

    public static  String encodeMD5(String originString) {
        return StringTools.isEmpty(originString)?null: DigestUtils.md5Hex(originString);
    }






}
