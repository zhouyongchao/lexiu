package com.thinkgem.jeesite.modules.drh.service;

/**
 * Created by Administrator on 2016/9/6.
 * 短信相关业务逻辑
 */
public interface SMSService {
    /**
     * 发送短信验证码
     * @param mobile
     * @param authCode
     */
    void sendAuthCode(String mobile, String authCode);
}
