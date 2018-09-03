package webchat_gd.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	// 常用的格式
	public static final String COMMON_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String COMMON_DATE_FORMAT = "yyyy-MM-dd";

	public static String dateToString(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);// 日期格式输出形式
		return sdf.format(date);
	}

	public static Date stringToDate(String string, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);// 日期格式输出形式
		Date date = null;

		try {
			date = sdf.parse(string);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;
	}

}