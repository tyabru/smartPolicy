package com.jingyu.web.controller.common;

import com.jingyu.common.core.domain.AjaxResult;
import com.jingyu.common.utils.FTPUtils;
import com.jingyu.common.utils.MD5Util;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

/**
 * 图片处理Controller
 *
 * @author ruoyi
 * @date 2020-11-10
 */
@RestController
@RequestMapping("/common/imgHandler")
public class ImageHandleController {

	@Value("${jingyu.profile}")
	private String fileUploadPath;

	@Value("${ftpServer.ip}")
	private String ip;

	@Value("${ftpServer.port}")
	private int port;

	@Value("${ftpServer.username}")
	private String username;

	@Value("${ftpServer.password}")
	private String password;

	@Value("${ftpServer.vice_username}")
	private String vice_username;

	@Value("${ftpServer.vice_password}")
	private String vice_password;


	@Value("${ftpServer.enable}")
	private boolean enable;

	//本地开发与部署在内网上传图片
	@RequestMapping(value = "/imgUpload")
	public AjaxResult imgUploadNw(HttpServletRequest req, MultipartHttpServletRequest multiReq) throws IOException {

		System.out.println("<=========================>");
		// 获取图片上传的公共路径（文件夹）
		File imgDirPath = new File(fileUploadPath);
		if (!imgDirPath.exists()) {
			// 若不存在文件夹，则创建一个文件夹
			imgDirPath.mkdirs();
		}
		// 获取市、区、派出所、警务室以及小区编码
		String profilePicture = "identificationPhotos";
		Date now = new Date();
		String dataStr1 = DateFormatUtils.format(now, "yyyyMMdd");
		String dataStr2 = DateFormatUtils.format(now, "HH");
		String storePath = "/" + profilePicture + "/" + dataStr1 + "/" + dataStr2;
		String subDirPath = fileUploadPath + storePath;
		imgDirPath = new File(subDirPath);

		if (!imgDirPath.exists()) {
			// 若不存在文件夹，则创建一个文件夹
			imgDirPath.mkdirs();
		}
		MultipartFile file = multiReq.getFile("file");
		// 获取图片原文件名
		String originalFilename = file.getOriginalFilename();
		// 获取图片格式后缀
		String suffix = originalFilename.substring(originalFilename.indexOf("."));
		// 重新生成图片名称
		String localFileName = MD5Util.md5(file.getInputStream()) + System.currentTimeMillis() + suffix;
		File localFile = new File(subDirPath + "/" + localFileName);
		try {

			file.transferTo(localFile);

			// 这是上传到远程FTP的代码 如果本地报错可以设置application.yml中的ftpServer.enable=false
//			if(enable) {
//				// 利用ftp将图片上传到远程的远程
//				FileInputStream fis = new FileInputStream(localFile);
//				FileInputStream fis_vice = new FileInputStream(localFile);
//				FTPUtils.storeFile(ip, port, username, password, storePath, localFileName, fis);
//
//				//利用ftp将图片文件上传到远程的远程FTP通道文件夹中
//				FTPUtils.storeFile(ip, port, vice_username, vice_password, storePath, localFileName, fis_vice);
//			}
		} catch (IOException e) {
			e.printStackTrace();
			return AjaxResult.error("照片上传失败，请联系管理员！");
		}
		System.out.println("照片上传成功，保存位置：" + subDirPath + "/" + localFileName);
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.put("imgUrl", storePath + "/" + localFileName);
		return ajaxResult;
	}

	// 本地开发与部署在内网获取图片
	@RequestMapping(value="/getImg")
	public void getImgNw(@RequestParam(value = "fileRelativePath", required = true) String fileRelativePath,
						 HttpServletResponse response) throws IOException {
		if (fileRelativePath.equals("") || fileRelativePath == null)
			return;
		FileInputStream fis = null;
		OutputStream os = null;
		String realPath = fileUploadPath + fileRelativePath;
		try {
			fis = new FileInputStream(realPath);
			os = response.getOutputStream();
			int count = 0;
			byte[] buffer = new byte[1024 * 3];
			while ((count = fis.read(buffer)) != -1) {
				os.write(buffer, 0, count);
				os.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
