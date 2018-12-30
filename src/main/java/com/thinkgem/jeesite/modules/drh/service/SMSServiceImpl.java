package com.thinkgem.jeesite.modules.drh.service;

import com.cloopen.rest.sdk.CCPRestSmsSDK;
import org.springframework.stereotype.Service;

/**
 * Created by root on 2017/9/26.
 */
@Service("smsService")
public class SMSServiceImpl implements SMSService {

    @Override
    public void sendAuthCode(String mobile, String authCode) {
        CCPRestSmsSDK restAPI = new CCPRestSmsSDK();
        restAPI.init("app.cloopen.com", "8883");
        restAPI.setAccount("8a216da86715511501673f530f501868", "a55bc1ff67a842f3a604260828f443fe");
        restAPI.setAppId("8a216da86715511501673f530fa7186f");
        restAPI.sendTemplateSMS(mobile,"1" ,new String[]{authCode,"15"});
    }
}
