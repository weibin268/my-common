package com.zhuang.common.web.util;

import java.io.*;

public class FileUtils {

	public static String readHtmlInOneLine(String filePath, String charsetName) {
		StringBuilder stringBuilder = new StringBuilder();
		FileInputStream fileInputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileInputStream = new FileInputStream(new File(filePath));
			inputStreamReader = new InputStreamReader(fileInputStream, "utf-8");
			bufferedReader = new BufferedReader(inputStreamReader);
			String tempString;
			while ((tempString = bufferedReader.readLine()) != null) {
				stringBuilder.append(tempString);
			}
		} catch (Exception e) {
			throw new RuntimeException("读取文件:“" + filePath + "”出错！", e);
		} finally {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				inputStreamReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				fileInputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return stringBuilder.toString();
	}
}
