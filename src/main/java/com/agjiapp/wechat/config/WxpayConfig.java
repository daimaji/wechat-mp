package com.agjiapp.wechat.config;

public class WxpayConfig {
	/**
	 * 微信支付相关常量
	 */
	public static final String WX_ORDER_PAY = "https://api.mch.weixin.qq.com/pay/unifiedorder"; // 统一下单

	public static final String WX_ORDER_PAY_QUERY = "https://api.mch.weixin.qq.com/pay/orderquery"; // 支付订单查询

	public static final String WX_ORDER_REFUND = "https://api.mch.weixin.qq.com/secapi/pay/refund"; // 申请退款

	public static final String WX_ORDER_REFUND_QUERY = "https://api.mch.weixin.qq.com/pay/refundquery"; // 申请退款

	public static final String WX_APP_ID = "wxe98b76cf94f6ea0c";

	public static final String WX_MCH_ID = "1427263302";

	public static final String WX_API_SECRET = "48beb26c307b7b6cd1343f62d02d3240";
	
}
