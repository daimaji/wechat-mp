package com.agjiapp.wechat.config;

/**
 * 数据字典
 * 
 * @author kwliu
 * @date 2016年11月17日 下午2:31:33
 * @version 1.0
 */
public class DictEnum {
	/** 交易状态 */
	public static class TradeStatus {
		/** 交易完成 */
		public static final String TRADE_FINISHED = "TRADE_FINISHED";
		/** 等待付款 */
		public static final String WAIT_BUYER_PAY = "WAIT_BUYER_PAY";
		/** 次月退订 （咪咕联合会员订单）**/
		public static final String UNSUBSCRIBE_NEXT_MONTH = "UNSUBSCRIBE_NEXT_MONTH";
		/** 立即退订 （咪咕联合会员订单）**/
		public static final String UNSUBSCRIBE_AT_ONCE = "UNSUBSCRIBE_AT_ONCE";
	}

	/** 套餐业务类型 */
	public static class Sub {
		/** 评测 */
		public static final String ISE = "ise";
		/** 合成 */
		public static final String TTS = "tts";
		/** 听写 */
		public static final String IAT = "iat";
	}

	/** 套餐等级 */
	public static class PackLevel {
		/** 基础包 */
		public static final String BASIC = "basic";
		/** 体验包 */
		public static final String FREE = "free";
	}

	/** 支付渠道 */
	public static class PaymentChannel {
		/** 支付宝钱包 */
		public static final String ALIPAY = "Alipay";
		/** 支付宝网页 */
		public static final String ALIPAY_WEB = "AlipayWap";
	}
}
