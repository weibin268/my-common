package com.zhuang.common.web.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * Created by zhuang on 9/1/2017.
 */
public class ValidateCodeUtils {

    public static final String VALIDATE_CODE_KEY = "validateCode";

    public static BufferedImage generateValidateCodeImage(String code, int width, int height) throws IOException {
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = bufferedImage.getGraphics();
        renderBackground(graphics, width, height);
        renderCode(graphics, code);
        graphics.dispose();
        return bufferedImage;
    }

    public static String getRandomCode(int codeLength) {
        char[] codeSeq = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J',
                'K', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
                'X', 'Y', 'Z', '2', '3', '4', '5', '6', '7', '8', '9'};
        String[] fontTypes = {"Arial", "Arial Black", "AvantGarde Bk BT", "Calibri"};
        Random random = new Random();
        StringBuilder sbCode = new StringBuilder();
        for (int i = 0; i < codeLength; i++) {
            String r = String.valueOf(codeSeq[random.nextInt(codeSeq.length)]);//random.nextInt(10));
            sbCode.append(r);
        }
        return sbCode.toString();
    }

    private static Color getRandomColor(int fc, int bc) {
        int f = fc;
        int b = bc;
        Random random = new Random();
        if (f > 255) {
            f = 255;
        }
        if (b > 255) {
            b = 255;
        }
        return new Color(f + random.nextInt(b - f), f + random.nextInt(b - f), f + random.nextInt(b - f));
    }

    private static void renderBackground(Graphics g, int width, int height) {
        // 填充背景
        g.setColor(getRandomColor(220, 250));
        g.fillRect(0, 0, width, height);
        // 加入干扰线条
        for (int i = 0; i < 8; i++) {
            g.setColor(getRandomColor(40, 150));
            Random random = new Random();
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            g.drawLine(x, y, x1, y1);
        }
    }

    private static void renderCode(Graphics graphics, String code) {
        String[] fontTypes = {"Arial", "Arial Black", "AvantGarde Bk BT", "Calibri"};
        Random random = new Random();
        for (int i = 0; i < code.length(); i++) {
            graphics.setColor(new Color(50 + random.nextInt(100), 50 + random.nextInt(100), 50 + random.nextInt(100)));
            graphics.setFont(new Font(fontTypes[random.nextInt(fontTypes.length)], Font.BOLD, 26));
            graphics.drawString(String.valueOf(code.charAt(i)), 15 * i + 5, 25);
        }
    }

}
