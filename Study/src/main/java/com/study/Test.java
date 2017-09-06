package com.study;

import java.nio.charset.Charset;

import org.apache.commons.lang.StringUtils;

public class Test {

	public static int HEX_STR_LEN_ONE_BYTE = 2;

	public static byte[] toBytes(String str) {
		if (str == null || str.trim().equals("")) {
			return new byte[0];
		}

		byte[] bytes = new byte[str.length() / 2];
		for (int i = 0; i < str.length() / 2; i++) {
			String subStr = str.substring(i * 2, i * 2 + 2);
			bytes[i] = (byte) Integer.parseInt(subStr, 16);
		}

		return bytes;
	}
	
	
	public static Integer toInt(String hexStre) {
		return Integer.parseInt(hexStre, 16);
	}

	public static void main(String[] args) {

//		Charset charSet = Charset.forName("UTF-8");

		String hexS = "360000000a352e352e362d72630003000000446d772f21597a7b00fff7080200000000000000000000000000007b76616248226e27712a784200";

//		System.out.println(new String(toBytes(hexS)));

		String packge_length = hexS.substring(0, 6);

		System.out.println(toInt(packge_length));

		String seq_num = hexS.substring(6, 6+2);

		System.out.println(toInt(seq_num));

		String protocol_ver = hexS.substring(8, 8+2);
		
		System.out.println(toInt(protocol_ver));
		
		hexS=hexS.substring(10);
		
		int nullIndex=hexS.indexOf("00");
		
		String mysql_ver=hexS.substring(0,nullIndex);
		System.out.println(new String(toBytes(mysql_ver)));
		
		hexS=StringUtils.substringAfter(hexS, "00");
		
		String threa_id=hexS.substring(0,8);
		System.out.println(new String(toBytes(threa_id)));
	}

}
