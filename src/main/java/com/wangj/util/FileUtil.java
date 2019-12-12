package com.wangj.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class FileUtil {

    private final static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public static String read(InputStream inputStream) {
        StringBuilder content = new StringBuilder();
        byte[] buffer = new byte[1024];
        int position = 0;
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            while((position = bufferedInputStream.read(buffer)) != -1) {
                content.append(new String(buffer, 0, position));
            }
            bufferedInputStream.close();
            return content.toString().trim();
        } catch (IOException e) {
            logger.error("error happend at write, " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public static boolean write(OutputStream outputStream, String content) {
        try {
            BufferedOutputStream bOutputStream = new BufferedOutputStream(outputStream);
                bOutputStream.write(content.getBytes());
            bOutputStream.flush();
            bOutputStream.close();
            return true;
        } catch (IOException e) {
            logger.error("error happend at write, " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public static String read(File file) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            return read(inputStream);
        } catch (FileNotFoundException e) {
            logger.error("file {} not exist, " + e.getMessage(), file.getName());
            e.printStackTrace();
        }
        return null;
    }

    public static boolean write(File file, String content) {
        try {
            OutputStream outputStream = new FileOutputStream(file);
            return write(outputStream, content);
        } catch (FileNotFoundException e) {
            logger.error("file {} not exist, " + e.getMessage(), file.getName());
            e.printStackTrace();
        }
        return false;
    }


}
