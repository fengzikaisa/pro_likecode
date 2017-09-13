package com.likecode.common.utils;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Appender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;
/***
 * 
 * @author ly
 * @version v1.0
 * @deprecated: 自定义日志输出(与目标文件同目录)
 *
 */
public class Loger {
	protected Loger() throws Exception {
		throw new Exception();
	}

	public static Logger getLogger(Class<?> aClass) {
		return getLog4jLogger(aClass);
	}

	public static Logger getLog4jLogger(Class<?> aClass) {
		org.apache.log4j.Logger logger = null;
		logger = Logger.getLogger(aClass);
		logger.addAppender((Appender) getAppender(aClass.getName(), Level.INFO));

		return logger;
	}

	public static RollingFileAppender getAppender(String className, Level lv) {
		RollingFileAppender appender = null;
		try {
			String pattern = "[%5p]%d{yyyy-MM-dd HH:mm:ss}[%c](%F:%L)%n%m%n%n";
			Layout layout = new PatternLayout(pattern);

			String path = getPath(className);
			if ("INFO".equals(lv.toString())) {
				path = path.substring(0, path.length() - 4).replace(".", "/")
						+ ".log";
			} else {
				path = "logs/error.log";
			}
			appender = new RollingFileAppender(layout, path);
			appender.setMaximumFileSize(5000000000l);//.setMaxFileSize("5MB");
			appender.setMaxBackupIndex(5);
		} catch (IOException e) {
			new Exception(e);
		}
		return appender;
	}

	private static String getPath(String className) {
		StringBuffer path = new StringBuffer();
		path.append("logs");
		checkPathExists(path);
		path.append("/" + className);
		DateFormat dformate = new SimpleDateFormat("yyyy-MM-dd");
		path.append(dformate.format(new Date()) + ".log");
		return path.toString();
	}

	private static void checkPathExists(StringBuffer path) {
		String thePath = path.toString();
		File file = new File(thePath);
		if (!file.exists()) {
			file.mkdir();
		}
	}
}
