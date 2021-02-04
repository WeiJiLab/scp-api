package com.thoughtworks.security.scpapi.util;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class ZipUtil {
    public static String createZip(String filePath, String filePre) {
        String path = "/tmp/";
        // 压缩包名
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateString = formatter.format(currentTime);
        String fileFullName = path + filePre + dateString + ".zip";
        File zipFile = new File(fileFullName);
        File sourceFile = new File(filePath);
        BufferedInputStream bis = null;
        ZipOutputStream zos = null;
        try {
            File[] sourceFiles = sourceFile.listFiles();
            if ((sourceFiles == null) || (sourceFiles.length < 1)) {
                return null;
            }
            FileOutputStream fos = new FileOutputStream(zipFile);
            zos = new ZipOutputStream(fos);
            byte[] bufs = new byte[1024 * 10];
            for (int i = 0; i < sourceFiles.length; i++) {
                //创建ZIP实体，并添加进压缩包
                ZipEntry zipEntry = new ZipEntry(sourceFiles[i].getName());
                zos.putNextEntry(zipEntry);
                //读取待压缩的文件并写进压缩包里
                FileInputStream fis = new FileInputStream(sourceFiles[i]);
                bis = new BufferedInputStream(fis, 1024 * 10);
                int read = 0;
                while ((read = bis.read(bufs, 0, 1024 * 10)) != -1) {
                    zos.write(bufs, 0, read);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            //关闭流
            try {
                if (null != bis) {
                    bis.close();
                }

                if (null != zos) {
                    zos.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        System.out.print(fileFullName);
        return fileFullName;
    }

    public static void unZip(String zipPath, String descDir) throws IOException {
        unZipFiles(new File(zipPath), descDir);
    }

    public static void unZipFiles(File zipFile, String descDir) throws IOException {
        File pathFile = new File(descDir);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }
        ZipFile zip = new ZipFile(zipFile);
        for (Enumeration entries = zip.entries(); entries.hasMoreElements();) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            String zipEntryName = entry.getName();

            InputStream in = zip.getInputStream(entry);
            String outPath = (descDir + "/" + zipEntryName).replaceAll("\\\\", "/");

            //判断路径是否存在,不存在则创建文件路径
            File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
            if (!file.exists()) {
                file.mkdirs();
            }
            //判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
            if (new File(outPath).isDirectory()) {
                continue;
            }
            //输出文件路径信息
            System.out.println(outPath);

            OutputStream out = new FileOutputStream(outPath);
            byte[] buf1 = new byte[1024];
            int len;
            while ((len = in.read(buf1)) > 0) {
                out.write(buf1, 0, len);
            }
            in.close();
            out.close();
        }
        System.out.println("******************解压完毕********************");
    }
}
