package com.agjiapp.wechat.config;


import com.agjiapp.wechat.util.HttpUtil;

/**
 * Created by agji on 17/8/20.
 */
public class WxmpConfig {
    public static String GET_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?";
    public static String GET_USER_INFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info";
    public static String MEDIA_UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/media/upload";

    public static String APPID = "wx2dbef1e6e438d15e";
    public static String APPSECRET = "30c12eade04996f5dee52bb0efa15e50";
    public static String getToken() {
        StringBuffer sb = new StringBuffer(GET_TOKEN_URL);
        sb.append("grant_type=client_credential&");
        sb.append("appid=").append(APPID).append("&");
        sb.append("secret=").append(APPSECRET);
        String tokenRes = HttpUtil.get(sb.toString());
        return tokenRes;
    }

    public static void main(String[] args) {
        String res = getToken();
        System.out.println(res);
    }
}
