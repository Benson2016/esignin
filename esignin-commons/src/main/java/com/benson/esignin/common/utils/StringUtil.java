package com.benson.esignin.common.utils;

import com.benson.esignin.common.cons.CommonCons;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


/**
 * String 工具类，提供常用的字符串操作方法
 * 
 * @since 2016年05月12日 18:13
 * @author Benson Xu
 * @version 1.0
 */
public class StringUtil {

    public final static boolean ENCODE_TO_CN = false;
	public final static String XML_ELEM_FMT = "<%s>%s</%s>";
	
    /*
	 * 16进制数字字符集
	 */
	private static String hexString = "0123456789ABCDEF";
 
    
    /**
     * 判断是否是空白字符串
     * @param string 需判断的字符串
     * @return 布尔类型结果
     */
    public static boolean isNullString(String string) {
        if(null == string || string.length() == 0)
            return true;
        return false;
    }

    /**
     * 将null转换成空字符串 
     * 若源字符串为null，则将其转换成空字符串""；若不为null，则放回源字符串
     * @param string 所要转换的字符串
     */
    public static String nullToString(String string) {
        return string == null ? "" : string;
    }
    
    /**
     * 对字符串进行格式化，格式化的方式为：对源字符串(sourceString)中的{n}(其中n表示数字，从0开始)
     * 进行替换，该功能类似C#中的string.Format(string s, params objs)。
     * @param sourceString 源字符串
     * @param args 所要格式化的参数信息（Object[]数组）

     * @return
     */
    public static String formater(String sourceString, Object[] args){
        if(isNullString(sourceString))
            return "";
        if(null == args || args.length == 0)
            return sourceString;
        return MessageFormat.format(sourceString, args);
    }
    
    /**
     * 对字符串为空时进行处理返回空值，如果不是null则返回相应的值
     * @param sourceString 源字符串
     * @return
     */
    public static String formatNull(String sourceString){
        if(isNullString(sourceString))
            return "";
        return sourceString;
    }
    
    /**
     * 是否为真
     * 判断内容：true/yes/y/on字符串
     * @param string 比较字符串
     * @return 布尔类型结果
     */
    public static boolean isTrue(String string){
    	if(isNullString(string))
    		return false;
    	
    	string = string.trim();
    	if("true".equalsIgnoreCase(string))
    		return true;
    	if("yes".equalsIgnoreCase(string))
    		return true;
    	if("y".equalsIgnoreCase(string))
    		return true;
    	if("on".equalsIgnoreCase(string))
    		return true;
    	return false;
    }
	
    /**
     * 是否为False
     * 判断内容：false/no/n/off字符串
     * @return
     */
	public static boolean isFalse(String string){
    	if(isNullString(string))
    		return false;
    	
    	string = string.trim();
    	if("false".equalsIgnoreCase(string))
    		return true;
    	if("no".equalsIgnoreCase(string))
    		return true;
    	if("n".equalsIgnoreCase(string))
    		return true;
    	if("off".equalsIgnoreCase(string))
    		return true;
    	return false;
    }
	
	/**
	 * 判断两个字符串是否相等
	 * @param str1 字符串1
	 * @param str2 字符串2
	 * @return
	 */
	public static boolean isEquals(String str1, String str2){
		boolean oneNull = isNullString(str1);
		boolean twoNull = isNullString(str2);
		if(oneNull && twoNull)
			return true;
		if((oneNull && (!twoNull)) || ((!oneNull) && twoNull))
			return false;
    	
    	if(str1.equalsIgnoreCase(str2))
    		return true;
    	return false;
    }
	
	/**
	 * 判断是否包含
	 * @param strings
	 * @param string
	 * @return
	 */
	public static boolean isContain(String[] strings, String string) {
		if (null == strings || strings.length == 0)
			return false;

		for (int i = 0; i < strings.length; i++) {
			if (isEquals(string, strings[i]))
				return true;
		}
		return false;
	}
	
    /**
     * 将列表转换为字符串 
     * @param sourceList 字符串列表
     * @param separator 分隔符
     * @return 组合好的字符串
     */
    public static String list2String(List<Object> sourceList, String separator) {
        if (null == sourceList || sourceList.size() < 1)
            return "";
        StringBuffer result = new StringBuffer();
        for (Iterator<Object> iter = sourceList.iterator(); iter.hasNext();) {
            String element = (String) iter.next();
            result.append(element + separator);
        }
    
        return result.substring(0, result.length() - separator.length());
    }

    /**
     * 将字符串数组转换为以分割符连接的字符串 
     * @param sourceString 所要转换的字符串数组
     * @param separator 分隔符
     * @param removeBlank 是否需要移除空白列表中的空值：Constants.BLANK_STRING_VALUE
     * @return 组合后的字符串
     */
    public static String array2String(String[] sourceString, String separator,
            						  boolean removeBlank) {
        if (sourceString == null || sourceString.length == 0)
            return "";
        
        StringBuffer buffer = new StringBuffer();
        if (removeBlank) {
            for (int i = 0; i < sourceString.length; i++) {
                if(sourceString[i].equalsIgnoreCase("-1"))
                    continue;
                buffer.append(sourceString[i]);
                buffer.append(separator);
            }
        } else {
            for (int i = 0; i < sourceString.length; i++) {
                buffer.append(sourceString[i]);
                buffer.append(separator);
            }
        }

        String result = buffer.toString();
        if (result.length() > separator.length())
            result = result.substring(0, result.length() - separator.length());
        return result;
    }

    /**
     * 将指定的字符串转换成UTF-8的编码
     * @param isoString 需要转化的ISO-8859-1格式字符串
     * @return UTF-8编码的字符串
     */
    public static String encodingToCN(String isoString) {
    	if(isNullString(isoString))
    		return "";
    	try {
    		if(ENCODE_TO_CN){
    			String returnString = new String(isoString.getBytes(CommonCons.CHARSET_ISO88591), CommonCons.CHARSET_UTF8);
    			return returnString;
    		}else{
    			return isoString;
    		}
    	} catch (Exception e) {
    		return "";
    	}
    }
    
    /**
     * 将指定的字符串转换成UTF-8的编码
     * @param isoString 需要转化的ISO-8859-1格式字符串
     * @return UTF-8编码的字符串
     */
    public static String encodingToUTF8(String isoString) {
        return encodingToCN(isoString);
    }
    
    /**
     * 将指定的字符串转换成ISO-8859-1的编码
     * @param cnString UTF-8格式的字符串
     * @return ISO-8859-1编码字符串
     */
    public static String encodingToEN(String cnString) {
        if (isNullString(cnString))
            return "";
        try {
            return new String(cnString.getBytes(CommonCons.CHARSET_UTF8), CommonCons.CHARSET_ISO88591);
        } catch (Exception e) {
            return "";
        }
    }
    
    /**
     * 将指定字符串的第一个字母转换成小写
     * @param sourceString 所要转换的字符串
     * @return 转换后的字符串
     */
    public static String firstCharToLowCase(String sourceString){
        if(isNullString(sourceString))
            return sourceString;
        if(sourceString.length() == 1)
            return sourceString.toLowerCase();
        return sourceString.substring(0, 1).toLowerCase() + sourceString.substring(1, sourceString.length());
    }
    
    /**
     * 将指定的字符串从源字符中剔除
     * @param sourceString 源字符串
     * @param trimString 所要剔除的字符串
     * @return 返回剔除后的字符串
     */
    public static String trim(String sourceString, String trimString){
        if(isNullString(sourceString))
            return "";
        if(isNullString(trimString))
            return sourceString;
        String[] temp = sourceString.split(trimString);
        if(null == temp || temp.length == 0)
            return sourceString;
        else{
            String result = "";
            for(int i = 0; i < temp.length; i++)
                result += temp[i];
            return result;
        }
    }
    
    /**
     * 验证字符串是否是可转换为数字格式
     * @param value 要验证的字符串
     * @return 如果输入的字符串可转换为数字格式则返回true，否则返回false
     */
    public static boolean isNumeric(String value) {
        try {
            if (isNullString(value))
                return false;
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    

	/**
	 * 将字符串编码成16进制数字,适用于所有字符（包括中文）
	 * @param str 需要转化的字符串
	 * @return 十六进制的字符串
	 */
	public static String String2Hex(String str) {
		// 根据默认编码获取字节数组
		byte[] bytes = str.getBytes();
		StringBuffer sb = new StringBuffer(bytes.length * 2);
		// 将字节数组中每个字节拆解成2位16进制整数
		for (int i = 0; i < bytes.length; i++) {
			sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
			sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
		}
		return sb.toString();
	}


	/**
	 * 将16进制数字解码成字符串,适用于所有字符（包括中文）
	 * @param bytes 十六进制的字符串
	 * @return 解码后的字符串
	 */
	public static String Hex2String(String bytes) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length() / 2);
		
		// 将每2位16进制整数组装成一个字节
		for (int i = 0; i < bytes.length(); i += 2)
			baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString.indexOf(bytes.charAt(i + 1))));
		return new String(baos.toByteArray());
	}
    
    /**
     * 将字符串数组转换成long数组
     * @param sArray
     * @return
     */
    public static long[] stringArray2LongArray(String[] sArray){
        if(null == sArray || sArray.length == 0)
            return null;
        long[] longArray = new long[sArray.length];
        for(int i = 0; i < sArray.length; i++){
            try {
                longArray[i] = Long.parseLong(sArray[i]);
            } catch (NumberFormatException e) {
                longArray[i] = 0;
            } 
        }
        return longArray;
    }
    
    /**
     * 将字符串数组转换成long数组
     * @param sArray
     * @return
     */
    public static long[] stringArray2LongArray(Collection<?> sArray){
        if(null == sArray || sArray.isEmpty())
            return null;
        long[] longArray = new long[sArray.size()];
		Iterator<?> itor = sArray.iterator();
		int i = 0;
        try {
			while(itor.hasNext()){
                longArray[i] = Long.parseLong(String.valueOf(itor.next()));
                i++;
			}
        } catch (NumberFormatException e) {
            longArray[i] = 0;
        } 
        return longArray;
    }
    
    /**
     * 将字符串数组转换成int数组
     * @param sArray 字符串数组
     * @return int数组数据
     */
    public static int[] stringArray2IntArray(String[] sArray){
        if(null == sArray || sArray.length == 0)
            return null;
        int[] intArray = new int[sArray.length];
        for(int i = 0; i < sArray.length; i++){
            try {
                intArray[i] = Integer.parseInt(sArray[i]);
            } catch (NumberFormatException e) {
                intArray[i] = 0;
            } 
        }
        return intArray;
    }
    
    /**
     * 将字符串数组转换成boolean数组
     * @param sArray 需转换的字符串数组
     * @return 布尔类型数组
     */
    public static boolean[] stringArray2BooleanArray(String[] sArray){
        if(null == sArray || sArray.length == 0)
            return null;
        boolean[] boolArray = new boolean[sArray.length];
        for(int i = 0; i < sArray.length; i++){
            boolArray[i] = (sArray[i].equalsIgnoreCase("true"));
        }
        return boolArray;
    }
    
    /**
     * 合并数组中的字符串
     * @param array 要处理的数组
     * @param symbol 连接符号
     * @return 数组中各个字符串用指定符号连接
     */
    public static String join(String[] array,String symbol){
        if(null == array || array.length == 0)
            return "";
        StringBuffer t = new StringBuffer(array[0]);
        for(int i = 1; i < array.length; i++){
            t.append(symbol + array[i]);
        }
        return t.toString();
    }
    
    /**
	 * 将整型数值转换为N位字符串，不够位时在前面不0
	 * 举例：convertToNBit(1,4) --> 0001
	 * @param val
	 * @param n
	 * @return
	 */
	public static String convertToNBit(int val, int n) {
		String valStr = String.valueOf(val);
		valStr = getZero(n-valStr.length()) + valStr;
		return valStr;
	}
	
	/**
	 * 获取N个0的字符串
	 * @param n 0的个数
	 * @return N个0的字符串
	 */
	public static String getZero(int n) {
		if (n <= 0) {
			return "";
		}
		
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < n; i++) {
			sb.append("0");
		}
		return sb.toString();
	}
	
	/**
	 * 比较两个字符串是否相等
	 * @param one 字符串1
	 * @param two 字符串2
	 * @param igonre 是否忽略大小写（true为忽略，false则区分大小写）
	 * @return 比较结果
	 */
	public static boolean compare(String one, String two, boolean igonre) {
		if (igonre) {
			return (one.trim().equalsIgnoreCase(two.trim()));
		} else {
			return (one.trim().equals(two.trim()));
		}
	}
	
	/**
	 * xujj
	 * 
	 * 将11位的手机号码转成隐藏模式：13760110110 --> 137***10
	 * @param fullPhone
	 * @return
	 */
	public static String phoneHidden(String fullPhone) {
		
		if(fullPhone.length() == 11) {
			
			String m = "***";
			
			String s = fullPhone.substring(0, 3);
			String e = fullPhone.substring(9, 11);
			
			return s + m + e;
			
		} else {
			
			return "";
		}
		
	}

	public static String emailHidden(String email) {
		
		int atIndex = email.indexOf("@");
		
		if(atIndex == -1) {
			return "";
		}
		
		String[] emails = email.split("@");
		String p = emails[0];
		String e = emails[1];
		
		p = "***" + p.substring(p.length()-2);
		
		return p + "@" + e;
	}
	
	/**
	 * 给源文本添加前后缀
	 * @param source 源文本
	 * @param prefix 前缀
	 * @param suffix 后缀
	 * @return 拼装后的结果
	 */
	public static String addPrefixAndSuffix(String source, String prefix, String suffix) {
		if (isNullString(source)) {
			return null;
		}
		
		String result = prefix + source + suffix;
		
		return result;
	}
	
	
	/**
	 * 获取字符串所占的字节数
	 * 每个英文、数字和标点符号都是占1位，每个中文汉字占2位
	 * 此处使用GBK编码获取字节数长度，而不使用UTF-8，因为UTF-8是可变长度，3~4位。
	 * @param str 目标字符串
	 * @return 所占的字节数
	 */
	public static int getBytesLength(String str) {
		if (isNullString(str)) {
			return 0;
		}
		int len = 0;
		try {
			len = str.getBytes(CommonCons.CHARSET_GBK).length;
		} catch (UnsupportedEncodingException e) {
			len = 0;
		}
		
		return len;
	}
	
	/**
	 * 将Map转化为XML格式的字符串
	 * @param param Map参数
	 * @param rootElement 根节点名称
	 * @return XML格式字符串
	 * @throws Exception
	 */
	public static String convertMapToXml(Map<String, Object> param, String rootElement) throws Exception {
		if (CommonUtil.isNull(param)) {
			return null;
		}
		
		StringBuffer elemSB = new StringBuffer();
		for (Entry<String, Object> entry : param.entrySet()) {
			elemSB.append(String.format(XML_ELEM_FMT, entry.getKey(), entry.getValue().toString(), entry.getKey()));
		}
		
		String xml = String.format(XML_ELEM_FMT, rootElement, elemSB.toString(), rootElement);

		return xml;
	}
	
	/**
	 * 解码URL
	 * 编码格式：UTF-8
	 * @param url
	 * @return
	 */
	public static String decodeUrl(String url) {
		String decUrl = url; 
		try {
			decUrl = URLDecoder.decode(url, CommonCons.CHARSET_UTF8);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return decUrl;
	}
	
	
}
