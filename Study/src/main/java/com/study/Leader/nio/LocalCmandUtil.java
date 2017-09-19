package com.study.Leader.nio;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Leader.us
 * @author wuzhihui
 *
 */
public class LocalCmandUtil {

	public static String callCmdAndgetResult(String cmd)
	{
		StringBuilder result=new StringBuilder();
		 try {
	            ProcessBuilder pb = new ProcessBuilder(cmd.split("\\s"));// 鍒涘缓杩涚▼绠＄悊瀹炰緥
	            Process process = pb.start();// 鍚姩杩涚▼
	            InputStream is = process.getInputStream(); // 鑾峰緱杈撳叆娴�
	            InputStreamReader isr = new InputStreamReader(is, "GBK");
	            BufferedReader br = new BufferedReader(isr);
	            String line;
	            while ((line = br.readLine()) != null) {// 寰幆璇诲彇鏁版嵁
	            	result.append(line);
	            }
	            is.close();
	            isr.close();
	            br.close();
	            process.waitFor(); 
	        } catch (Exception e) {// 鎹曡幏寮傚父
	        	result.append(e.toString());
	        }	
		 return result.toString();
		
	}
	
}
