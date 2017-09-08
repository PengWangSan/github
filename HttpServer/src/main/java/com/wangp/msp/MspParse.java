package com.wangp.msp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class MspParse {

	public static String DYNAMIC_START = "<%";

	public static String DYNAMIC_END = "%>";

	public static String SEPARATOR = System.getProperty("line.separator");

	public String read(File file) throws IOException {

		StringBuffer sb = new StringBuffer();
		sb.append(" public class jsp_").append(file.getName()).append(" extends Pervlet{").append(SEPARATOR);
		sb.append(" public void handlerRequest(PvRequest req,PvReponse res){").append(SEPARATOR);

		Reader reader = new InputStreamReader(new FileInputStream(file));

		LineNumberReader lineReader = new LineNumberReader(reader);
		String lineStr = null;
		boolean dynamic_code = false;
		while ((lineStr = lineReader.readLine()) != null) {

			if (lineStr.indexOf(DYNAMIC_START) != -1) {

				dynamic_code = true;

				lineStr = lineStr.replace(DYNAMIC_START, "");

			} else if (lineStr.indexOf(DYNAMIC_END) != -1) {
				dynamic_code = false;
				lineStr = lineStr.replace(DYNAMIC_END, "");
			}

			if (!dynamic_code) {
				sb.append(" res.getOut().println(\"").append(lineStr).append("\")").append(SEPARATOR);
			} else {

				sb.append(lineStr).append(SEPARATOR);
			}

		}
		sb.append("}").append(SEPARATOR);
		return sb.toString();

	}
	
	
	

	public static void main(String[] args) throws IOException {

		MspParse parse = new MspParse();

		String url = parse.getClass().getClassLoader().getResource("my.jsp").getFile();

		File file = new File(url);

		String result = parse.read(file);

		System.out.println(result);

	}

	public void parse(File file) {

		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

	}

}
