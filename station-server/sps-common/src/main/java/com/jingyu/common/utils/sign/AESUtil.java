package com.jingyu.common.utils.sign;

import com.jingyu.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 **
 * AES128 算法，加密模式为ECB，填充模式为 pkcs7（实际就是pkcs5）
 *
 *
 */
public class AESUtil {
	// 加密密钥
	private static final String PASS_WORD = "mXG2CGtBkb80LGX/gyyl4A==";
	private static SecretKeySpec AES_PASSWORD;
	private static final String KEY_ALGORITHM = "AES";
	// 默认的加密算法
	private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
	// 日志记录
	private static final Logger log = LoggerFactory.getLogger(AESUtil.class);

	/**
	 * AES 加密操作
	 *
	 * @param content
	 *            待加密内容
	 * @return 返回Base64转码后的加密数据
	 */
	public static String encrypt(String content) {
		if (StringUtils.isEmpty(content)) {
			return content;
		}
		try {
			// 创建密码器
			Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
			// 初始化为加密模式的密码器
			cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(PASS_WORD));
			byte[] encrypted = cipher.doFinal(content.getBytes("utf-8"));
			return parseByte2HexStr(encrypted);
		} catch (Exception ex) {
			log.error("加密失败");
		}
		return content;
	}

	/**
	 * 指定加密字符串进行AES的加密操作
	 *
	 * @param content
	 *            待加密内容
	 * @return 返回Base64转码后的加密数据
	 */
	public static String encrypt(String content, String password) {
		if (StringUtils.isEmpty(content)) {
			return content;
		}
		try {
			// 创建密码器
			Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
			// 初始化为加密模式的密码器
			cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(password));
			byte[] encrypted = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
			return parseByte2HexStr(encrypted);
		} catch (Exception ex) {
			log.error("加密失败");
		}
		return content;
	}

	/**
	 * AES 解密操作
	 *
	 * @param content
	 * @return
	 */
	public static String decrypt(String content) {
		if (StringUtils.isEmpty(content)) {
			return content;
		}
		try {
			// 实例化
			Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
			// 使用密钥初始化，设置为解密模式
			cipher.init(Cipher.DECRYPT_MODE, getSecretKey(PASS_WORD));
			byte[] base64Data = parseHexStr2Byte(content);
			byte[] original = cipher.doFinal(base64Data);
			String originalString = new String(original, "utf-8");
			return originalString;
		} catch (Exception ex) {
			// Logger.defalutLogger.error("解密失败"+ex.getStackTrace());
		}
		return content;
	}

	/** 这个解密方法如果解密失败则返回一个空字符串不再返回原字符
	 * */
	public static String decrypt2(String content) {
		if (StringUtils.isEmpty(content)) {
			return content;
		}
		try {
			// 实例化
			Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
			// 使用密钥初始化，设置为解密模式
			cipher.init(Cipher.DECRYPT_MODE, getSecretKey(PASS_WORD));
			byte[] base64Data = parseHexStr2Byte(content);
			byte[] original = cipher.doFinal(base64Data);
			String originalString = new String(original, "utf-8");
			return originalString;
		} catch (Exception ex) {
			// Logger.defalutLogger.error("解密失败"+ex.getStackTrace());
			log.error("解密失败" + ex.getStackTrace());
		}
		return "";
	}

	/**
	 * 生成加密秘钥
	 *
	 * @return
	 */
	private static SecretKeySpec getSecretKey(final String password) throws Exception {
		if (AES_PASSWORD != null) {
			return AES_PASSWORD;
		}
		try {
			// 返回生成指定算法密钥生成器的 KeyGenerator 对象
			byte[] keyBytes = Arrays.copyOf(password.getBytes("ASCII"), 16);
			// 转换为AES专用密钥
			AES_PASSWORD = new SecretKeySpec(keyBytes, KEY_ALGORITHM);
			return AES_PASSWORD;
		} catch (Exception ex) {
			log.error("加密秘钥失败" + ex.getStackTrace());
			throw new Exception("加密秘钥失败");
		}

	}

	/**
	 * 将16进制转换为二进制
	 *
	 * @param hexStr
	 * @return
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	/**
	 * 将二进制转换成16进制
	 *
	 * @param buf
	 * @return
	 */
	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}
}
