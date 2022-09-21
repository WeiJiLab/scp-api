package com.thoughtworks.ssr.business.scan;

import com.thoughtworks.ssr.common.utils.ZipUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
public class ExportSecurityCheckReportController extends HttpServlet {
    @RequestMapping(value = "compliance/securityCheckExport", method = RequestMethod.GET)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String path = ZipUtil.createZip("reportPath", "currUser.getUsername()");

        //通过文件路径得到一个输入流
        FileInputStream fis = new FileInputStream(path);
        //创建字节输出流,这里我们使用Servlet的输出流
        ServletOutputStream sos = response.getOutputStream();
        //得到要下载文件的文件名
        String filename = path.substring(path.lastIndexOf("\\") + 1);
        System.out.println(filename);
        //告诉客户端下载文件
        response.setHeader("Content-Disposition", "attachment; filename=" + filename);
        response.setContentType("application/zip");
        //执行输出操作
        int len = 1;
        byte[] b = new byte[1024]; //创建一个字节数组
        while ((len = fis.read(b)) != -1) {
            sos.write(b, 0, len);
        }
        fis.close();
    }
}
