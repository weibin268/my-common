package com.zhuang.common.web.util;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.FileCleanerCleanup;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class FileUploadUtils {

    public static List<FileItem> getFileItems(HttpServletRequest request) {
        DiskFileItemFactory factory = createDiskFileItemFactory(request);
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("utf-8");
        try {
            return upload.parseRequest(request);
        } catch (FileUploadException e) {
            throw new RuntimeException("FileUploadUtils.getFileItems", e);
        }
    }

    public static FileItem getFirstFileItem(HttpServletRequest request) {
        List<FileItem> fileItems = getFileItems(request);
        for (FileItem fileItem :
                fileItems) {
            if (!fileItem.isFormField()) return fileItem;
        }
        return null;
    }

    public static void saveAs(HttpServletRequest request, String fileName) {
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        FileItem fileItem = null;
        try {
            fileItem = getFirstFileItem(request);
            inputStream = fileItem.getInputStream();
            fileOutputStream = new FileOutputStream(new File(fileName));
            byte[] buffer = new byte[1024];
            int count;
            while ((count = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, count);
            }
            fileOutputStream.flush();
        } catch (Exception e) {
            throw new RuntimeException("FileUploadUtils.saveAs", e);
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileItem != null) {
                fileItem.delete();
            }
        }
    }

    private static File getTempDir(HttpServletRequest request) {
        String tempPath = request.getServletContext().getRealPath("/WEB-INF/temp");
        File file = new File(tempPath);
        if (!file.exists() && !file.isDirectory()) {
            file.mkdir();
        }
        return file;
    }

    private static DiskFileItemFactory createDiskFileItemFactory(HttpServletRequest request) {
        File tempDif = getTempDir(request);
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory(1024 * 1024 * 1024, tempDif);//1G
        diskFileItemFactory.setFileCleaningTracker(FileCleanerCleanup.getFileCleaningTracker(request.getServletContext()));
        return diskFileItemFactory;
    }

}
