package com.zhuang.common.util;

import org.junit.Test;

import java.util.List;

/**
 * Created by zhuang on 12/31/2017.
 */
public class FileUtilsTest {
    @Test
    public void readAllLines() {
        String filePath = System.getProperty("user.dir") + "//pom.xml";
        List<String> lines = FileUtils.readLines(filePath);
        for (String line :
                lines) {
            System.out.println(line);
        }
    }

    @Test
    public void readAllText() {
        String filePath = System.getProperty("user.dir") + "//pom.xml";
        System.out.print(FileUtils.readText(filePath));
    }

    @Test
    public void writeText() {
        FileUtils.writeText("/home/zhuang/myfiles/temp/aaa/bbb/ccc/ddd/text.txt","ccc");
    }


    @Test
    public void getLineSeparator() {
        System.out.println(FileUtils.getLineSeparator());
    }

}