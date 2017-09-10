package com.wangp.msp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;

import com.wangp.msp.interfaces.OutPutStream;
import com.wangp.msp.interfaces.PvReponse;
import com.wangp.msp.interfaces.PvRequest;

import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class MspParse {

	public static String DYNAMIC_START = "<%";

	public static String DYNAMIC_END = "%>";

	public static String SEPARATOR = System.getProperty("line.separator");

	public String read(File file) throws IOException {

		StringBuffer sb = new StringBuffer();

		sb.append(" package com.wangp.msp;").append(SEPARATOR);
		sb.append(" import com.wangp.msp.MspPervlet;").append(SEPARATOR);
		sb.append(" import com.wangp.msp.interfaces.OutPutStream;").append(SEPARATOR);
		sb.append(" import com.wangp.msp.interfaces.PvReponse;").append(SEPARATOR);
		sb.append(" import com.wangp.msp.interfaces.PvRequest;").append(SEPARATOR);
		sb.append(" public class jsp_").append(file.getName().replace(".jsp", "")).append(" extends MspPervlet{")
				.append(SEPARATOR);

		sb.append(" public  jsp_").append(file.getName().replace(".jsp", ""))
				.append(" () {").append(SEPARATOR);
		sb.append(" super();").append(SEPARATOR);
		sb.append(" 	}").append(SEPARATOR);

		sb.append(" public  jsp_").append(file.getName().replace(".jsp", ""))
				.append(" (PvRequest req, PvReponse res, OutPutStream out) {").append(SEPARATOR);
		sb.append(" super(req, res, out);").append(SEPARATOR);
		sb.append(" 	}").append(SEPARATOR);

		sb.append(" public void handlerRequest(){").append(SEPARATOR);

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

				lineStr = lineStr.replace("\"", "\\\"");
				sb.append(" response.getOut().println(\"").append(lineStr).append("\");").append(SEPARATOR);
			} else {

				sb.append(lineStr).append(SEPARATOR);
			}

		}
		sb.append("}").append(SEPARATOR);
		sb.append("}").append(SEPARATOR);
		return sb.toString();

	}

	public void genJavaFile(String content, String path) {
		File file = new File(path);
		try {
			FileWriter fw = new FileWriter(file);
			fw.write(content);
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {

		MspParse parse = new MspParse();
		String url = parse.getClass().getClassLoader().getResource("my.jsp").getFile();
		File file = new File(url);
		parse.parse(file);

	}

	public Class parse(File jspfile) {

		String jspFileName = jspfile.getName();
		String jspUrl = this.getClass().getClassLoader().getResource(jspFileName).getFile();
		File jspFile = new File(jspUrl);
		String javaContent = null;
		try {
			javaContent = this.read(jspFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String basePath = this.getClass().getClassLoader().getResource("").getPath();
		basePath = new File(basePath).getParentFile().getParentFile().getPath()
				+ "\\src\\main\\java\\com\\wangp\\msp\\";
		String javaPath = basePath + "jsp_" + jspFileName.replace("jsp", "java");
		this.genJavaFile(javaContent, javaPath);

		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager fileManage = compiler.getStandardFileManager(null, null, null);
		Iterable<? extends JavaFileObject> fileObjects = fileManage.getJavaFileObjects(javaPath);
		CompilationTask cTask = compiler.getTask(null, fileManage, null, null, null, fileObjects);
		cTask.call();

		Class c = null;
		try {
			c = this.getClass().getClassLoader().loadClass("com.wangp.msp.jsp_" + jspFileName.replace(".jsp", ""));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return c;
	}

}
