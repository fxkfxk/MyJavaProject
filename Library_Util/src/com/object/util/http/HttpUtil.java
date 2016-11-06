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
	 *            http��ַ
	 * @param parms
	 *            ����Ĳ���
	 */
	public static String get(String addr, Map<String, String> parms) throws Exception {
		try {
			addr = addr + "?";
			for (Map.Entry<String, String> entry : parms.entrySet()) {
				addr += URLEncoder.encode(entry.getKey()) + "=" + URLEncoder.encode(entry.getValue()) + "&";
			}
			URL url = new URL(addr);
			URLConnection urlConnection = url.openConnection(); // ������
			BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8")); // ��ȡ������
			String line = null;
			StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			return sb.toString();
		} catch (MalformedURLException e) {
			throw new Exception("�Ҳ����κκϷ�Э�飬�����޷������ַ����� "+addr);
		} catch (UnsupportedEncodingException e) {
			throw new Exception("��֧���ַ����롣"+addr+";   utf-8");
		} catch (IOException e) {
			throw new Exception("���ʴ���"+addr);
		}
	}

}
