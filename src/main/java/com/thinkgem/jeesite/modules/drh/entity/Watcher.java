package com.thinkgem.jeesite.modules.drh.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niexuyang on 2017/11/12.
 */
public class Watcher {

    private String userId;//主播
    private List<TUser> watcherList;
    private int offNumber;
    private int onNumber;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<TUser> getWatcherList() {
        return watcherList;
    }

    public void setWatcherList(List<TUser> watcherList) {
        this.watcherList = watcherList;
    }

    public int getOffNumber() {
        return offNumber;
    }

    public void setOffNumber(int offNumber) {
        this.offNumber = offNumber;
    }

    public int getOnNumber() {
        return onNumber;
    }

    public void setOnNumber(int onNumber) {
        this.onNumber = onNumber;
    }

    public void addOffWatcher(){
        offNumber+=1;
    }
    public void addOnWatcher(TUser tUser){
        if (watcherList==null){
            watcherList = new ArrayList<TUser>();
        }
        watcherList.add(tUser);
        onNumber+=1;
    }
    public void subOffWatcher(){
        offNumber-=1;
    }
    public void subOnWatcher(TUser tUser){
        watcherList.remove(tUser);
        onNumber-=1;
    }
}
