package com.object.util.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

public class HttpUtil {

	/**
	 * @param addr
	 *            http地址
	 * @param parms
	 *            传输的参数
	 */
	public static String get(String addr, Map<String, String> parms) throws Exception {
		try {
			addr = addr + "?";
			for (Map.Entry<String, String> entry : parms.entrySet()) {
				addr += URLEncoder.encode(entry.getKey()) + "=" + URLEncoder.encode(entry.getValue()) + "&";
			}
			URL url = new URL(addr);
			URLConnection urlConnection = url.openConnection(); // 打开连接
			BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8")); // 获取输入流
			String line = null;
			StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			return sb.toString();
		} catch (MalformedURLException e) {
			throw new Exception("找不到任何合法协议，或者无法解析字符串。 "+addr);
		} catch (UnsupportedEncodingException e) {
			throw new Exception("不支持字符编码。"+addr+";   utf-8");
		} catch (IOException e) {
			throw new Exception("访问错误。"+addr);
		}
	}

}
