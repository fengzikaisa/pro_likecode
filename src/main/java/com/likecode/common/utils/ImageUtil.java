package com.likecode.common.utils;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;

@SuppressWarnings("restriction")
public final class ImageUtil {

    public static final int SIZE_1024 = 1024;
    public static final int SIZE_512 = 512;
    public static final int SIZE_256 = 256;
    public static final int SIZE_128 = 128;
    public static final int SIZE_64 = 64;

    /**
     * 按比例根据宽度缩放图片
     *
     * @throws
     */
    public static void resizeFixByWidth(File imgFile, int size) throws IOException {
        BufferedImage img;
        try {
            img = ImageIO.read(imgFile);
        } catch (IOException e) {
            JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(new FileInputStream(imgFile));
            img = decoder.decodeAsBufferedImage();
        } // 构造Image对象
        int width = img.getWidth(null); // 得到源图宽
        int height = img.getHeight(null); // 得到源图长

        int w = width > size ? size : width;
        int h = (int) w * height / width;
        resize(img, imgFile.getAbsolutePath(), w, h, imgFile.getName());
    }

    /**
     * 强制压缩/放大图片到固定的大小
     *
     * @param img    原图片
     * @param target 目标文件路径
     * @param w      int 新宽度
     * @param h      int 新高度
     */
    public static void resize(BufferedImage img, String target, int w, int h, String filename) throws IOException {
        BufferedImage to = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        if (filename.toUpperCase().endsWith("PNG")) {
            Graphics2D g2d = to.createGraphics();
            to = g2d.getDeviceConfiguration().createCompatibleImage(w, h, Transparency.TRANSLUCENT);
            g2d.dispose();

            g2d = to.createGraphics();
            Image from = img.getScaledInstance(w, h, Image.SCALE_AREA_AVERAGING);
            g2d.drawImage(from, 0, 0, null);
            g2d.dispose();
            ImageIO.write(to, "png", new File(target));
        } else {
            to.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图
            // 可以正常实现bmp、png、gif转jpg
            FileOutputStream out = new FileOutputStream(target);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(to); // JPEG编码
            out.close();
            // ImageIO.write(to, "png", new File(target));
        }
    }

    //图片转化成base64字符串
    public static String GetImageStr() {
        //将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        String imgFile = "d://test.jpg";//待处理的图片
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);//返回Base64编码过的字节数组字符串
    }

    //base64字符串转化成图片
    public static boolean GenerateImage(String imgStr, String imgFilePath) {   //对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) //图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {//调整异常数据
                    b[i] += 256;
                }
            }
            //生成jpeg图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 合并图片
     */
    public static void mergeImage(String tplImage, String image, int x, int y, int width, String outFile){
        try {
            BufferedImage big = tplImage.startsWith("http") ? ImageIO.read(new URL(tplImage)) : ImageIO.read(new File(tplImage));
            BufferedImage small = image.startsWith("http") ? ImageIO.read(new URL(image)) : ImageIO.read(new File(image));
            Graphics2D g = big.createGraphics();
            g.drawImage(small, x, y, width, width, null);
            g.dispose();

            FileUtil.createParentDirs(new File(outFile));
            ImageIO.write(big, outFile.split("\\.")[1], new File(outFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static final Color COLOR_DEF = new Color(0,0,0,255);
    public static final Font FONT_DEF = new Font("微软雅黑", Font.PLAIN, 15);
    /**
     * 添加水印
     */
    public static void addWaterMark(String imagePath, int x, int y, String waterMarkContent,Color
            markContentColor,Font font) {
        try {
            // 读取原图片信息
            File srcImgFile = new File(imagePath);//得到文件
            Image srcImg = ImageIO.read(srcImgFile);//文件转化为图片
            int srcImgWidth = srcImg.getWidth(null);//获取图片的宽
            int srcImgHeight = srcImg.getHeight(null);//获取图片的高
            // 加水印
            BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufImg.createGraphics();
            g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
            g.setColor(markContentColor); //根据图片的背景设置水印颜色
            g.setFont(font);              //设置字体

            //设置水印的坐标
            g.drawString(waterMarkContent, x, y);  //画出水印
            g.dispose();
            // 输出图片
            FileOutputStream outImgStream = new FileOutputStream(imagePath);
            ImageIO.write(bufImg, "jpg", outImgStream);
            outImgStream.flush();
            outImgStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
