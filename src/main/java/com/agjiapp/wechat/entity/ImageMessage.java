package com.agjiapp.wechat.entity;

/**
 * @author 姬爱国
 * @description
 * @create 2018-01-17 下午11:33
 **/
public class ImageMessage {
//    ToUserName	是	接收方帐号（收到的OpenID）
//    FromUserName	是	开发者微信号
//    CreateTime	是	消息创建时间 （整型）
//    MsgType	是	image
//    MediaId	是	通过素材管理中的接口上传多媒体文件，得到的id。
    private String ToUserName;
    private String FromUserName;
    private String CreateTime;
    private String MsgType;
    private Image Image;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public com.agjiapp.wechat.entity.Image getImage() {
        return Image;
    }

    public void setImage(com.agjiapp.wechat.entity.Image image) {
        Image = image;
    }
}
