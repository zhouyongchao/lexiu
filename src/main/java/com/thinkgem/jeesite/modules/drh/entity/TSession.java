package com.thinkgem.jeesite.modules.drh.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * Created by root on 2017/9/26.
 */
public class TSession extends DataEntity<TSession> {

    private static final long serialVersionUID = 1L;

    private String uuid;

    private String context;

    /**
     *  0 验证码 1 用户信息
     */
    private int type;
    /**
     * 0无效 1有效
     */
    private int status;

    private Date startTime;

    public TSession() {
        super();
    }

    public TSession(String id) {
        super(id);
    }



    @Length(min=0, max=64, message="uuid长度必须介于 0 和 64 之间")
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    @Length(min=0, max=256, message="context长度必须介于 0 和 256 之间")
    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    @Length(min=0, max=10, message="type长度必须介于 0 和 10 之间")
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Length(min=0, max=10, message="status长度必须介于 0 和 10 之间")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
}
