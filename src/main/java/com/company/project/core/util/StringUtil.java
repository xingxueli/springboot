package com.company.project.core.util;

import org.springframework.util.StringUtils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>StringUtil class.</p>
 *
 * @author mike
 * @version $Id: $Id
 */
public class StringUtil {
	
	/**
	 * 判断传入参数是否为空
	 *
	 * @param str input
	 * @return boolean
	 */
	public static boolean isEmpty(Object str){
		if (str == null || "".equals(str)) {
			return true;
		}
		return false;
	}
	/**
	 * <p>objToStr.</p>
	 *
	 * @param obj a {@link Object} object.
	 * @return a {@link String} object.
	 */
	public static String objToStr(Object obj){
		return (obj == null) ? "" : obj.toString();
	}
	
    /**
     * 利用正则表达式判断字符串是否是数字
     *
     * @param str input
     * @return boolean
     */
    public static boolean isNumeric(String str){
           Pattern pattern = Pattern.compile("[0-9]*");
           Matcher isNum = pattern.matcher(str);
           if( !isNum.matches() ){
               return false;
           }
           return true;
    }
    
    /**
     * 利用正则表达式判断对象是否是数字
     *
     * @param obj input
     * @return boolean
     */
    public static boolean isNumeric(Object obj){
    	if (isEmpty(obj)) {
			return false;
		}
    	return isNumeric(obj.toString());
	}
    
    /**
     * 利用正则表达式判断字符串是否是数字
     * 如果不是返回指定数字
     *
     * @param obj input
     * @param  resultInt resultInt
     * @return Integer
     */
    public static Integer numericProcessing(Object obj,Integer resultInt){
    	if(isNumeric(obj)){
    		return Integer.valueOf(obj.toString());
    	}
    	return isNumeric(resultInt)?resultInt:0;
    }

    /**
     * 删除特定字符
     *
     * @param str 原字符串
     * @param deleteStr 删除字符
     * @return return
     */
    public static String deleteChar(String str, String deleteStr) {
		StringBuffer stringBuffer = new StringBuffer(str);
		int iFlag;
		do {
			iFlag = stringBuffer.indexOf(deleteStr);
			if (iFlag != -1) {
				stringBuffer.deleteCharAt(iFlag);
			}
		} while (iFlag != -1);
		return stringBuffer.toString();
	}

	/**
	 * 获取随机字符串
	 *
	 * @param length input
	 * @param type random type
	 * @return String
	 */
	public static String randomStr(int length, int type) {
		Random random = new Random();
    	String str;
    	if (type == 1) {
    		str = "zxcvbnmlkjhgfdsaqwertyuiop";
		} else if (type == 2) {
    		str = "QWERTYUIOPASDFGHJKLZXCVBNM";
		} else if (type == 3) {
			str = "zxcvbnmlkjhgfdsaqwertyuiop1234567890";
		} else if (type == 4) {
			str = "QWERTYUIOPASDFGHJKLZXCVBNM1234567890";
		} else {
			str = "zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
		}
    	int ranDomLength = str.length();
    	StringBuffer randomStr = new StringBuffer();
    	for (int i=0; i < length; i++) {
			int number = random.nextInt(ranDomLength);
			randomStr.append(str.charAt(number));
		}
    	return randomStr.toString();
	}

	/**
	 * 字符替换
	 * @param str 原字符串
	 * @param remainLength 剩下不被替换的长度
	 * @param replaceChar 替换字符
	 * @return String
	 */
	private static String replace(String str, int remainLength, char replaceChar) {
		StringBuffer result = new StringBuffer();
		if (str.length() <= remainLength) {
			return str;
		}
		String replaceStr = str.substring(0, str.length() - remainLength);
		for (int i = 0; i < replaceStr.length(); i++){
			result.append(replaceChar);
		}
		result.append(str.substring(str.length()-remainLength));
		return result.toString();
	}

	/**
	 * 将String 首字母大写,保留前后空格,特殊字符
	 * @param str
	 * @return
	 */
	public static String captureStr(String str) {
		if(StringUtils.isEmpty(str)) return str;

		char[] cs=str.toCharArray();
		for(int i=0;i<cs.length;i++) {
			if(isLetter(cs[i])){
				if((cs[i] <=122 && cs[i]>=97)){
					cs[i]-=32;
					break;
				}
				break;
			}
		}
		return String.valueOf(cs);
	}

	private static boolean isLetter(char chars){
		if((chars <=90 && chars>=65) || (chars <=122 && chars>=97)){
			return true;
		}
		return false;
	}

}
