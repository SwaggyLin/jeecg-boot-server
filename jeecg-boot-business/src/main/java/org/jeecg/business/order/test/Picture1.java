package org.jeecg.business.order.test;

/**
 * @Description
 * @Author linminfeng
 * @Date 2019-09-25 17:25
 */

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 把两张图片合并
 * @version 2018-2-27 上午11:12:09
 *
 */
public class Picture1
{
    private Graphics2D g        = null;

    /**
     * 导入本地图片到缓冲区
     */
    public BufferedImage loadImageLocal(String imgName) {
        try {
            return ImageIO.read(new File(imgName));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public BufferedImage modifyImagetogeter(BufferedImage b, BufferedImage d) {

        try {
            int w = b.getWidth();
            int h = b.getHeight();

            g = d.createGraphics();
            g.drawImage(b, 0, 0, w, h, null);
            g.dispose();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return d;
    }

    /**
     * 生成新图片到本地
     */
    public void writeImageLocal(String newImage, BufferedImage img) {
        if (newImage != null && img != null) {
            try {
                File outputfile = new File(newImage);
                ImageIO.write(img, "jpg", outputfile);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * 合成
     * @param
     */
    public static void getNewAvatar(){
        Picture1 tt = new Picture1();

        BufferedImage d = tt.loadImageLocal("/Users/linminfeng/Desktop/test/1.jpg");
        BufferedImage b = tt.loadImageLocal("/Users/linminfeng/Desktop/test/flag.png");

        tt.writeImageLocal("/Users/linminfeng/Desktop/test/avatar.jpg", tt.modifyImagetogeter(b, d));
        //将多张图片合在一起
        System.out.println("success");
    }




    public static void main(String[] args) {
        Picture1 tt = new Picture1();
        tt.getNewAvatar();

    }
}