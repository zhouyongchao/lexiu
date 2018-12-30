package com.thinkgem.jeesite.modules.drh.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import java.util.Date;

/**
 * Created by Niexuyang on 2017/11/15.
 */
public class GiftRecord extends DataEntity<GiftRecord> {

    private static final long serialVersionUID = 1L;

    private String userid;//主播

    private String userName;

    private String senderid;//送礼物的人

    private String senderName;

    private String giftid;//礼物

    private String giftName;

    private Date date;//时间

    public GiftRecord() {
    }

    public GiftRecord(String id) {
        super(id);
    }

    public GiftRecord(String userid, String senderid, String giftid, Date date) {
        this.userid = userid;
        this.senderid = senderid;
        this.giftid = giftid;
        this.date = date;
    }

    public GiftRecord(String id, String userid, String senderid, String giftid, Date date) {
        super(id);
        this.userid = userid;
        this.senderid = senderid;
        this.giftid = giftid;
        this.date = date;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getSenderid() {
        return senderid;
    }

    public void setSenderid(String senderid) {
        this.senderid = senderid;
    }

    public String getGiftid() {
        return giftid;
    }

    public void setGiftid(String giftid) {
        this.giftid = giftid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }
}
