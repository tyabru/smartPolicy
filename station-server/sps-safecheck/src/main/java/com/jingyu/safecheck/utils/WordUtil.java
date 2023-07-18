package com.jingyu.safecheck.utils;

import cn.afterturn.easypoi.word.WordExportUtil;
import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @description Word相关处理
 */
public class WordUtil {

    /**
     * EasyPoi 替换数据 导出 word
     * @param templatePath word模板地址
     * @param tempDir      临时文件存放地址
     * @param filename     文件名称
     * @param data         替换参数
     * @param request
     * @param response
     */
    public static String easyPoiExport(String templatePath, String tempDir, String filename, Map<String, Object> data, HttpServletRequest request, HttpServletResponse response) {
        Assert.notNull(templatePath, "模板路径不能为空");
        Assert.notNull(tempDir, "临时文件路径不能为空");
        Assert.notNull(filename, "文件名称不能为空");
        Assert.isTrue(filename.endsWith(".docx"), "文件名称仅支持docx格式");

        if (!tempDir.endsWith("/")) {
            tempDir = tempDir + File.separator;
        }

        File file = new File(tempDir);
        if (!file.exists()) {
            file.mkdirs();
        }
        String pdfFilename = "";
        try {
            String userAgent = request.getHeader("user-agent").toLowerCase();
            if (userAgent.contains("msie") || userAgent.contains("like gecko")) {
                filename = URLEncoder.encode(filename, "UTF-8");
            } else {
                filename = new String(filename.getBytes("utf-8"), "ISO-8859-1");
            }

            XWPFDocument document = WordExportUtil.exportWord07(templatePath, data);
            String tempPath = tempDir + filename;
            FileOutputStream out = new FileOutputStream(tempPath);
            document.write(out);

            pdfFilename = filename.substring(0, filename.indexOf('.')) + ".pdf";
            InputStream docxInputStream = new FileInputStream(tempPath);
            OutputStream outputStream = new FileOutputStream(tempDir + pdfFilename);
            IConverter converter = LocalConverter.builder().build();
            converter.convert(docxInputStream).as(DocumentType.DOCX).to(outputStream).as(DocumentType.PDF).execute();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            deleteTempFile(tempDir, filename);
            System.out.println(tempDir+filename);
        }
        return pdfFilename;
    }

    /**
     * 删除临时生成的文件
     */
    public static void deleteTempFile(String filePath, String fileName) {
        File file = new File(filePath + fileName);
//        File f = new File(filePath);
        file.delete();
//        f.delete();
    }
}
