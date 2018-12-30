package com.thinkgem.jeesite.common.utils;

import java.util.Random;

/**
 * Created by Administrator on 2016/10/15.
 */
public class CodeUtil {

    public static String getRandomStr(int length){
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        while (length>0){
            sb.append(random.nextInt(10));
            length--;
        }
        return sb.toString();
    }
}
