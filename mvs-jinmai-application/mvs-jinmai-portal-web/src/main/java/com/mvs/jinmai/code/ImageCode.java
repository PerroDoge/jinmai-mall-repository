package com.mvs.jinmai.code;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import lombok.Data;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

import java.io.IOException;
import java.util.Random;

@Data
public class ImageCode {


    private String code;

    private ByteArrayInputStream image;

    private int width = 400;
    private int height = 100;

    public static ImageCode getInstance() {
        try {
            return new ImageCode();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private ImageCode() throws IOException {
        // 图形缓冲区
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 画笔
        Graphics graphics = image.getGraphics();
        // 画背景
        graphics.setColor(new Color(17, 147, 37));
        graphics.fillRect(0, 0, width, height);
        // 画数字
        graphics.setFont(new Font("微软雅黑", Font.PLAIN, 30));
        // 生成随机数
        Random random = new Random();
        graphics.setColor(new Color(225, 255, 255));
        for(int i = 0; i < 6; i++) {
            String s = String.valueOf(random.nextInt(10));
            code += s;
            graphics.drawString(s, ((width / 6 )* i), 40);
        }

        // 收笔
        graphics.dispose();

        ByteArrayInputStream inputStream = null;
        ByteOutputStream outputStream = new ByteOutputStream();
        ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(outputStream);
        try {
            ImageIO.write(image, "jpeg", imageOutputStream);
            inputStream = new ByteArrayInputStream(outputStream.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.image = inputStream;


    }
}
