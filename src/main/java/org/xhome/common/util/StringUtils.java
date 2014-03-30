package org.xhome.common.util;

/**
 * @project xhome-util
 * @author jhat
 * @email cpf624@126.com
 * @date Feb 4, 20135:55:42 PM
 */
public final class StringUtils {
	
	private StringUtils() {}
	
	/**
	 * 将数字转换成中文
	 * 如 1001 --> 一千零一
	 * 
	 * @param num
	 *            待转换的数字
	 * @return
	 */
	public static String formatNumbertoUpperCase(int num) {
		String[] nStr = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
		String[] qStr = { "", "十", "百", "千", "万" };
		boolean negative = num < 0;
		if (negative) num = -num;
		if (num >= 100000) return "" + num;
		StringBuffer sb = new StringBuffer();
		int quality = 0;
		int w = 0;
		boolean lastZero = false;
		while (num > 0) {
			w = num % 10;
			if (w == 0 && quality == 0) {
				sb.insert(0, qStr[quality % 5]);
				lastZero = true;
			} else if (w == 1 && (quality == 1 || quality == 5)) {
				sb.insert(0, qStr[quality % 5]);
			} else if (w == 0) {
				if (!lastZero) sb.insert(0, nStr[w]);
				lastZero = true;
			} else {
				sb.insert(0, nStr[w]);
				sb.insert(1, qStr[quality % 5]);
				lastZero = false;
			}
			num /= 10;
			quality++;
		}
		if (negative) sb.insert(0, "负");
		return sb.toString();
	}
	
	/**
	 * 判断str是否不为空字符窜
	 * 
	 * @param str
	 * @return 如果不为空字符窜，则返回true
	 */
	public static boolean isNotEmpty(String str) {
		if (str == null) return false;
		return str.trim().equals("") ? false : true;
	}
	
	/**
	 * 判断str是否为空字符窜
	 * 
	 * @param str
	 * @return 如果是空字符窜，则返回true
	 */
	public static boolean isEmpty(String str) {
		if (str == null) return true;
		return str.trim().equals("") ? true : false;
	}
	
}
