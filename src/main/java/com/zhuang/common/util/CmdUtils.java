package com.zhuang.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by zhuang on 12/10/2017.
 */
public class CmdUtils {

    public static String exec(String cmd) {
        StringBuilder sbResult = new StringBuilder();
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            process.waitFor();
            //normal
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sbResult.append(line + "\n");
            }
            //error
            bufferedReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while ((line = bufferedReader.readLine()) != null) {
                sbResult.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return sbResult.toString();
    }

}
