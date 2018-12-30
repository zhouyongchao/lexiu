package com.thinkgem.jeesite.modules.pay.entity;

import java.math.BigDecimal;
import java.util.Date;

public class AlipayPaymentOrder {


    //订单所属用户
    private String userId;
    //选择的商品
    private String productId;
    //订单唯一编号
    private String outTradeNo;
    //客户端订单金额
    private BigDecimal totalAmount;
    //支付宝实际扣款金额
    private BigDecimal payAmount;
    //支付订单状态：init-初始化状态 sign-签名状态 submit-提交支付 processing-订单处理中 success-支付成功 fail-失败
    private String payStatus;
    //订单创建时间
    private Date createTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
