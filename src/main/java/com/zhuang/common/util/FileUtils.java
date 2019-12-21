package com.zhuang.common.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuang on 12/31/2017.
 */
public class FileUtils {

    public static List<String> readLines(String filePath) {
        return readLines(filePath, "UTF-8");
    }

    public static List<String> readLines(String filePath, String charsetName) {
        List<String> result = new ArrayList<String>();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), charsetName));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result.add(line);
            }
        } catch (Exception e) {
            throw new RuntimeException("FileUtils.readLines error!", e);
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static String readText(String filePath) {
        return readText(filePath, "UTF-8");
    }

    public static String readText(String filePath, String charsetName) {
        String result;
        FileInputStream fileInputStream = null;
        try {
            File file = new File(filePath);
            fileInputStream = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            fileInputStream.read(bytes);
            result = new String(bytes, charsetName);
        } catch (Exception e) {
            throw new RuntimeException("FileUtils.readText error!", e);
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static void writeText(String filePath, String text) {
        writeText(filePath, text, "UTF-8");
    }

    public static void writeText(String filePath, String text, String charsetName) {
        FileOutputStream fileOutputStream = null;
        try {
            File file = new File(filePath);
            File parentFile = file.getParentFile();
            if (!parentFile.isDirectory()) {
                parentFile.mkdirs();
            }
            fileOutputStream = new FileOutputStream(file);
            byte[] bytes = text.getBytes(charsetName);
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
        } catch (Exception e) {
            throw new RuntimeException("FileUtils.writeText error!", e);
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getLineSeparator() {
        return System.getProperty("line.separator");
    }

}
