package com.thinkgem.jeesite.modules.drh.util;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import com.sun.xml.internal.ws.util.Pool.TubePool;
import com.thinkgem.jeesite.modules.drh.entity.TUser;

public class CacheUtil {
    private static Map<String, Set<TUser>> watcherMap = new LinkedHashMap();

    public static void addWathcher(String userId, TUser tUser) {
        Set userSet = watcherMap.get(userId);
        if (userSet == null) {
            userSet = new HashSet();
        }
        userSet.add(tUser);
        watcherMap.put(userId,userSet);
    }

    public static void subWathcher(String userId, TUser tUser) {
        Set<TUser> userSet = watcherMap.get(userId);
        if (userSet == null) {
            return;
        }
        userSet.remove(tUser);
        Set<TUser> newSet = new HashSet();
        for (TUser user : userSet){
            if (!tUser.getId().equals(user.getId())){
                newSet.add(user);
            }
        }
        watcherMap.put(userId,newSet);
    }

    public static Set<TUser> getUserSet(String userId){
        Set userSet = watcherMap.get(userId);
        if (userSet == null) {
            userSet = new HashSet();
        }
        return userSet;
    }
}
