package com.thinkgem.jeesite.modules.pay.resource;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfig;
import com.thinkgem.jeesite.common.utils.HttpReqUtils;
import com.thinkgem.jeesite.modules.drh.ResultModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/wechat/pay")
public class WeChatPayResource {

    /**
     * step1：app向服务器发起请求，生成支付订单
     * @return
     */
    @RequestMapping("/order/create")
    @ResponseBody
    public ResultModel requestCreatePayOrder() throws Exception {
        //step2:调用统一下单API，生成预付单，返回预付单信息，生成带签名的客户端支付信息，返回
        WXPayConfig config = null;
        WXPay wxPay = new WXPay(config);

        Map body = new HashMap<String,String>();
        body.put("body","");//商品描述，APP——需传入应用市场上的APP名字-实际商品名称，天天爱消除-游戏充值。
        body.put("out_trade_no","");
        body.put("total_fee","");//金额
        body.put("spbill_create_ip","");//终端ip
        body.put("notify_url","");//异步通知回调地址
        body.put("trade_type","APP");//支付类型
        //data.put("device_info", "");
        //data.put("fee_type", "CNY");
        //data.put("product_id", "12");
        //返回预付单信息
        Map result = wxPay.unifiedOrder(body);
        return new ResultModel(0,"success",result);
    }

    /**
     * 异步通知商户支付结果
     */
    public void AsynNotification(){
        //接受和保存支付通知
        //返回通知接受成功

    }

    /**
     * 后台查询支付结果
     */
    public void queryPayResult(){
        //调用微信查询api查询支付结果
        //返回支付结果
    }
}
