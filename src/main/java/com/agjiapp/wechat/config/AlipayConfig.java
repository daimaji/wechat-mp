package com.agjiapp.wechat.config;

/**
 * 支付宝常量配置
 *
 * @author kwliu
 * @date 2016年11月19日 下午4:29:27
 * @version 1.0
 */
public class AlipayConfig {
	// 签名方式ßß
	public static String sign_type = "RSA";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String charset = "utf-8";

	// APP支付接口名（固定值）
	public static String method = "alipay.trade.app.pay";

	// 产品码（固定值）
	public static String product_code = "QUICK_MSECURITY_PAY";

	// 接口版本号（固定值）
	public static String version = "1.0";
}
