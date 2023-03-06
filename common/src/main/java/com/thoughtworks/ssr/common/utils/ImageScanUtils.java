package com.thoughtworks.ssr.common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class ImageScanUtils {
    public static String getCreateTime() {
        Long currentTimestamp = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(Long.parseLong(String.valueOf(currentTimestamp))));
    }

    public static File getScanFile(URLConnection connection) throws IOException {
        InputStream is = connection.getInputStream();
        String downloadFilePath = System.getProperty("user.home");
        File file = new File(downloadFilePath + File.separator + "Desktop" + File.separator + "scan_report.docx");
        FileOutputStream os = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int len;
        while ((len = is.read(buffer)) != -1) {
            os.write(buffer, 0, len);
        }
        is.close();
        os.close();
        return file;
    }

    public static String generateUUID() {
        String expenseUUID = UUID.randomUUID().toString();
        return expenseUUID.replaceAll("-", "");
    }
}
