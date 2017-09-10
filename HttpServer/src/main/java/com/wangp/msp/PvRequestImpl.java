package com.wangp.msp;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wangp.msp.interfaces.PvRequest;

public class PvRequestImpl implements PvRequest {

	private Socket socket;

	private List<String> reqStr;

	public PvRequestImpl(Socket socket,List<String> reqStr) {
		super();
		this.reqStr=reqStr;
		this.socket = socket;

	}

	public Object getAttr(String attrName) {
		return null;
	}

	public Object getParam(String paramName) {

		String value="";
		try {
			Map<String, String> paramMap = new HashMap<String, String>();

			String url = reqStr.get(0).split(" ")[1];
			String[] parms = url.substring(url.indexOf("?") + 1, url.length()).split("&");

			for (String parm : parms) {
				String[] parmArr = parm.split("=");
				paramMap.put(parmArr[0], parmArr[1]);
			}
			value = paramMap.get(paramName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return value;
	}

}
