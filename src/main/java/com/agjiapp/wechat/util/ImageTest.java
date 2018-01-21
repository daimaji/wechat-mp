package com.agjiapp.wechat.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @author 姬爱国
 * @description
 * @create 2018-01-18 上午9:35
 **/
public class ImageTest {
    public static void main(String[] args) throws IOException {
//        InputStream in = new FileInputStream("/Users/agji/Documents/test.jpg");//图片路径
        BufferedImage image = ImageIO.read(new File("/Users/agji/Documents/0.jpg"));
        Graphics2D graphics2D = image.createGraphics();
        graphics2D.setColor(Color.RED);
        Stroke stroke = new BasicStroke(8f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
        graphics2D.setStroke(stroke);
        graphics2D.drawRect(84, 135, 191, 191);
        FileOutputStream out = new FileOutputStream("/Users/agji/Documents/03.jpg");//输出图片的地址
        ImageIO.write(image, "jpeg", out);
        BufferedImage image2 = ImageIO.read(new File("/Users/agji/Documents/test.jpg"));
        Graphics g = image2.getGraphics();
        g.setColor(Color.RED);//画笔颜色
        g.drawRect(100, 100, 100, 100);//矩形框(原点x坐标，原点y坐标，矩形的长，矩形的宽)
        //g.dispose();
        FileOutputStream out2 = new FileOutputStream("/Users/agji/Documents/test2.jpg");//输出图片的地址
        ImageIO.write(image2, "jpeg", out2);
    }

}
