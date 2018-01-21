package com.agjiapp.wechat.controller;

import com.agjiapp.wechat.config.WxmpConfig;
import com.agjiapp.wechat.entity.Image;
import com.agjiapp.wechat.entity.ImageMessage;
import com.agjiapp.wechat.entity.TextMessage;
import com.agjiapp.wechat.util.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 姬爱国
 * @description
 * @create 2017-12-31 下午5:58
 **/
@Controller
public class MessageController {

    Logger log = Logger.getLogger(this.getClass());


    /**
     * 验证微信服务器的真实性
     * @param request
     * @return
     */
    @RequestMapping(value = "/msg", produces = "text/plain;charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public String checkMsg(HttpServletRequest request) {
        String signature = request.getParameter("signature");
        String echostr = request.getParameter("echostr");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        if (!CheckUtil.checkSignature(signature, timestamp, nonce)) {
            return "error";
        }
        return echostr;
    }


    /**
     * 接收微信服务器转发来的消息消息
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/msg", produces = "text/plain;charset=utf-8", method = RequestMethod.POST)
    @ResponseBody
    public String getMsg(ModelMap model, HttpServletRequest request) {
        String respMessage = null;
        try {
//          xml请求解析
            Map<String, String> requestMap = MessageUtil.xmlToMap(request);
//          发送方帐号（open_id）
            String fromUserName = requestMap.get("FromUserName");
//          公众帐号
            String toUserName = requestMap.get("ToUserName");
//          消息类型
            String msgType = requestMap.get("MsgType");
//          消息内容
            String content = requestMap.get("Content");

            log.info("发送方帐号fromUserName："+ fromUserName +
            "\n公众帐号toUserName："+ toUserName +
            "\n消息类型msgType："+ msgType +
            "\n消息内容content："+ content);
//          文本消息
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
//              特殊问题过滤
                if(content.equals("我的账号信息")){
//                  获取access_token
                    Map<String, String> map = new HashMap<String, String>();
                    String tokenJson = WxmpConfig.getToken();
                    log.info("tokenJson = " + tokenJson);
                    JSONObject jsonObj = JSONObject.parseObject(tokenJson);
                    String accessToken = jsonObj.getString("access_token");
//                    个人微信公众账号，没有获取用户信息的权限
//                    Map<String, String> userInfoParams = new HashMap<>();
//                    userInfoParams.put("access_token", accessToken);
//                    userInfoParams.put("openid", fromUserName);
//                    userInfoParams.put("lang", "zh_CN");
//                    String message = HttpUtil.get(WxmpConfig.GET_USER_INFO_URL, userInfoParams);
                    //获取用户信息
                    TextMessage text = new TextMessage();
                    text.setContent("获取的accessToken为："+ accessToken);
                    text.setToUserName(fromUserName);
                    text.setFromUserName(toUserName);
                    text.setCreateTime(new Date().getTime() + "");
                    text.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

                    respMessage = MessageUtil.messageToXml(text);
                    log.info("回复内容respMessage："+ respMessage);
                }

                //自动回复
                TextMessage text = new TextMessage();
                text.setToUserName(fromUserName);
                text.setFromUserName(toUserName);
                text.setCreateTime(new Date().getTime() + "");
                text.setMsgType(msgType);

                String url = "http://www.tuling123.com/openapi/api?key=1a2cebc582b540b7bfe32c58614b3fdd&info=" + content;
                String resJson = HttpUtil.get(url);
                JSONObject jsonObj = JSON.parseObject(resJson);
                text.setContent(jsonObj.getString("text"));

                respMessage = MessageUtil.messageToXml(text);

                log.info("回复内容respMessage："+ respMessage);

            } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {// 事件推送
                String eventType = requestMap.get("Event");// 事件类型
                // 订阅
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {

                    TextMessage text = new TextMessage();
                    text.setContent("欢迎关注，xxx");
                    text.setToUserName(fromUserName);
                    text.setFromUserName(toUserName);
                    text.setCreateTime(new Date().getTime() + "");
                    text.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

                    respMessage = MessageUtil.messageToXml(text);
                }
                // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息，如有需要，可记录粉丝退订的相关信息
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {// 取消订阅


                }
                // 自定义菜单点击事件
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
                    String eventKey = requestMap.get("EventKey");// 事件KEY值，与创建自定义菜单时指定的KEY值对应

                    //联系客服
                    if (eventKey.equals("CONTACT")) {

                        TextMessage text = new TextMessage();
                        text.setContent("客服QQ：2656653358\n客服微信：mimi15215517279");
                        text.setToUserName(fromUserName);
                        text.setFromUserName(toUserName);
                        text.setCreateTime(new Date().getTime() + "");
                        text.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

                        respMessage = MessageUtil.messageToXml(text);
                        log.info("回复内容respMessage："+ respMessage);
                    }
                }
            } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
                String picUrl = requestMap.get("PicUrl");
                log.info("PicUrl ＝ " + picUrl);
                StringBuffer sb = new StringBuffer(WxmpConfig.MEDIA_UPLOAD_URL);
                String tokenJson = WxmpConfig.getToken();
                JSONObject jsonObj = JSONObject.parseObject(tokenJson);
                String accessToken = jsonObj.getString("access_token");
                log.info("accessToken ＝ " + accessToken);
                sb.append("?access_token=").append(accessToken).append("&type=image");
                File file = DownloadUtil.downLoadFromUrl(picUrl, "temp_pic_" + System.currentTimeMillis() + ".jpg", "/home/agji/pic/");
                String uploadImgRes = FileUploadUtil.fileload(sb.toString(),file);
                log.info("uploadImgRes ＝ " + uploadImgRes);
                if (file.exists()) {
                    file.delete();
                }
                JSONObject uploadImgObj = JSON.parseObject(uploadImgRes);
                String mediaId = uploadImgObj.getString("media_id");
                Image image = new Image();
                ImageMessage imageMessage = new ImageMessage();
                imageMessage.setFromUserName(toUserName);
                imageMessage.setToUserName(fromUserName);
                image.setMediaId(mediaId);
                imageMessage.setImage(image);
                imageMessage.setMsgType("image");
                imageMessage.setCreateTime(System.currentTimeMillis() + "");
                respMessage = MessageUtil.messageToXml(imageMessage);
                //获取access_token
//                String resJson = EyekeyCheckingUtil.checkImgByUrl(picUrl);
//                log.info("EyekeyCheckingUtil.checkImgByUrl resJson ＝ " + resJson);
//                TextMessage text = new TextMessage();
//                text.setContent(resJson);
//                text.setToUserName(fromUserName);
//                text.setFromUserName(toUserName);
//                text.setCreateTime(new Date().getTime() + "");
//                text.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
//
//                respMessage = MessageUtil.messageToXml(text);
                log.info("回复内容respMessage："+ respMessage);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return respMessage;
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        Image image = new Image();
        ImageMessage imageMessage = new ImageMessage();
        imageMessage.setFromUserName("toUserName");
        imageMessage.setToUserName("fromUserName");
        image.setMediaId("oL3uhlo3jFx0uCA2-g4SopoVHnJy3i9C27alt6c5I6EykJx0Rmx8YMlzzrPGCPpG");
        imageMessage.setImage(image);
        imageMessage.setMsgType("image");
        imageMessage.setCreateTime(System.currentTimeMillis() + "");
        String respMessage = MessageUtil.messageToXml(imageMessage);
        System.out.println(respMessage);
    }
}
