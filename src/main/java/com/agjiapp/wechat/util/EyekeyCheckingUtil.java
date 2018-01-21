package com.agjiapp.wechat.util;

import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 人脸检测的Demo
 * @Title: EyekeyCheckingUtil.java
 * @Package: com.test
 * @ClassName: EyekeyCheckingUtil
 * @author Jaylee
 *@date 2015年12月12日 上午10:23:45
 */
public class EyekeyCheckingUtil {

	private static final String APPID = "42d272d6f41a4ef4848841934f251b82";
	private static final String APPKEY = "b9139161ae9c40acac33f9f67159d83e";
	private static final String API_URL = "http://api.eyekey.com/face/Check/checking";
	//待检测图片的网络路径
	private static String imgUrl="http://img2.imgtn.bdimg.com/it/u=3350941610,3792241192&fm=206&gp=0.jpg";
	//待检测图片的本地路径
	private static String imgPath="D:\\1.jpg";
	
	public static void main(String[] args) {
		
	/*********************第一种方法：通过网络图片的url来进行检测  begin********************/
		System.out.println("人脸检测信息："+checkImgByUrl(imgUrl));//
	/*********************第一种方法：通过网络图片的url来进行检测  end**********************/
		
		
	/*********************第二种方法：通过本地图片的url来进行检测  begin********************/
		File img=new File(imgPath);
		System.out.println("人脸检测信息："+checkImgBybase64(encodeImgageToBase64(img)));
	/*********************第二种方法：通过本地图片的url来进行检测  begin********************/	
	
	}
	
	
	/**
	 * 1.通过网络图片url进行人脸检测
	 * @param url 网络图片地址
	 * @return 人脸检测的json字符串
	 * @author Jaylee
	 */
	public static String checkImgByUrl(String url){
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("app_id", APPID);
		paramMap.put("app_key", APPKEY);
		paramMap.put("url", url);
		return HttpUtil.post(API_URL, paramMap);
	}
	
	/**
	 * 2.通过图片的base64方式进行人脸检测到
	 * @param base64
	 * @return 人脸检测的json字符串
	 * @author Jaylee
	 */
	public static String checkImgBybase64(String base64){
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("app_id", APPID);
		paramMap.put("app_key", APPKEY);
		paramMap.put("img", base64);
		return HttpUtil.post(API_URL, paramMap);
	}
	
	
	/**
	 * File文件转base64格式
	 * @param imageFile
	 * @author Jaylee
	 * @return string
	 */
	 public static String encodeImgageToBase64(File imageFile) {
		 
		 ByteArrayOutputStream outputStream = null;
		    try {
		      BufferedImage bufferedImage = ImageIO.read(imageFile);
		      outputStream = new ByteArrayOutputStream();
		      ImageIO.write(bufferedImage, "jpg", outputStream);
		    } catch (MalformedURLException e1) {
		      e1.printStackTrace();
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		    
		    BASE64Encoder encoder = new BASE64Encoder();
		    return encoder.encode(outputStream.toByteArray());// 返回Base64编码过的字节数组字符串
	}
}
