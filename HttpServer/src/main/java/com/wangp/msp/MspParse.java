package com.wangp.msp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class MspParse {

	public static String DYNAMIC_START = "<%";

	public static String DYNAMIC_END = "%>";

	public String read(File file) throws IOException {

		StringBuffer sb = new StringBuffer();
		sb.append(" public class jsp_").append(file.getName()).append(" extends Pervlet{").append("/r/n");
		sb.append(" public void handlerRequest(PvRequest req,PvReponse res){/r/n");

		Reader reader = new InputStreamReader(new FileInputStream(file));

		LineNumberReader lineReader = new LineNumberReader(reader);
		String lineStr = null;
		while ((lineStr = lineReader.readLine()) != null) {

			if (lineStr.indexOf(DYNAMIC_START) != -1) {

			} else if (lineStr.indexOf(DYNAMIC_END) != -1) {

			} else {

				sb.append(" res.getOut().println(\"").append(lineStr).append("\"/r/n");
				
			}

		}

		return null;

	}

	public void parse(File file) {

		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

	}

}
