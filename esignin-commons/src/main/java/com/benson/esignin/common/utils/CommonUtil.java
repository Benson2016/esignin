package com.benson.esignin.common.utils;


import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 通用工具类 
 * @since 2016年05月12日 18:15
 * @author Benson Xu
 * @version 1.0
 */

@SuppressWarnings("unchecked")
public class CommonUtil {

	// 默认日期格式
	public static final String DATE_FMT = "yyyy-MM-dd"; // 日期

	public static final String TIME_FMT = "HH:mm:ss"; // 时间

	public static final String DATE_TIME_FMT = "yyyy-MM-dd HH:mm:ss"; // 日期时间


	// 验证的正则表达式
	private static final String REG_ALPHA = "^[a-zA-Z]+$";

	private static final String REG_ALPHANUM = "^[a-zA-Z0-9]+$";

	private static final String REG_NUMBER = "^\\d+$";

	private static final String REG_INTEGER = "^[-+]?[1-9]\\d*$|^0$/";

	private static final String REG_FLOAT = "[-\\+]?\\d+(\\.\\d+)?$";

	private static final String REG_PHONE = "^((\\(\\d{2,3}\\))|(\\d{3}\\-))?(\\(0\\d{2,3}\\)|0\\d{2,3}-)?[1-9]\\d{6,7}(\\-\\d{1,4})?$";

	private static final String REG_MOBILE = "^((\\+86)|(86))?(1)\\d{10}$";

	private static final String REG_QQ = "^[1-9]\\d{4,10}$";

	private static final String REG_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

	private static final String REG_ZIP = "^[1-9]\\d{5}$";

	private static final String REG_IP = "^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";

	private static final String REG_URL = "^(http|https|ftp):\\/\\/(([A-Z0-9][A-Z0-9_-]*)(\\.[A-Z0-9][A-Z0-9_-]*)+)(:(\\d+))?\\/?/i";

	private static final String REG_CHINESE = "^[\\u0391-\\uFFE5]+$";
	
	private static final String REG_MONEY = "[\\-\\+]?\\d+(\\.\\d+)?$";

	/** 通过正则表达验证 */
	public static boolean matchesByRegex(String regex, String value){
		if(isNull(regex, value)){
			return false;
		}
		return Pattern.matches(regex, value);
	}
	
	/** 可以用于判断Object,String,Map,Collection,String,Array是否为空 */
	public static boolean isNull(Object value) {
		if (value == null) {
			return true;
		} else if(value instanceof String){
			if(((String)value).trim().replaceAll("\\s", "").equals("")){
                return true;
            }
		}else if(value instanceof Collection) {
            if(((Collection)value).isEmpty()){
                return true;
            }
        } else if(value.getClass().isArray()) {
            if(Array.getLength(value) == 0){
                return true;
            }
        } else if(value instanceof Map) {
            if(((Map)value).isEmpty()){
                return true;
            }
        }else {
            return false;
        }
		return false;
		 
	}
	


	public static boolean isNull(Object value, Object... items){
		if (isNull(value) || isNull(items)) {
			return true;
		}
		for (Object item : items) {
			if (isNull(item)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isNotNull(Object value){
		
		return !isNull(value);
	}
	
	public static boolean isNotNull(Object value, Object... items){
		
		return !isNull(value,items);
	}
	
	
	public static boolean Null(Object value, Object... items){
		if (isNull(value) || isNull(items)) {
			return true;
		}
		for (Object item : items) {
			if (isNull(item)) {
				return true;
			}
		}
		return false;
	}
	
	
	
	
	public static boolean isAllEmpty(String... items){  
		if (isNull(items)) {
			return true;
		}
		for (Object item : items) {
			if (isNotNull(item)) {
				return false;
			}
		}
		return true;  
    }  
	
	/**
	 * 将数组转换成List<Map<String,Object>>对象
	 * @param array 要转换的数组
	 * @return List<Map<String,Objct>>
	 */
	public static List<Map<String, Object>> arrayToList(String[] array) {
		List<Map<String,Object>> items = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < array.length; i++) {
			Map<String,Object> item = new HashMap<String,Object>();
			item.put("value", i);
			item.put("text", array[i]);
			items.add(item);
		}
		return items;
	}
	
	/**
	 * linwenming Mar 30, 2012
	 * @return Map<String,Object>
	 */
	public static Map<String, Object> arrayToMap(String[] array) {
		Map<String,Object> maps = new HashMap<String,Object>();
		for (int i = 0; i < array.length; i++) {
			maps.put(String.valueOf(i), array[i]);
		}
		return maps;
	}
	
	public static Map<String, Object> arrToMap(String[] array) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < array.length; i++) {
			map.put(array[i], array[i]);
		}
		return map;
	}
	public static boolean isAlpha(String value) {
		if(isNull(value))return false;
		return Pattern.matches(REG_ALPHA, value);
	}

	public static boolean isAlphanum(String value) {
		if(isNull(value))return false;
		return Pattern.matches(REG_ALPHANUM, value);
	}

	public static boolean isNumber(String value) {
		if(isNull(value))return false;
		return Pattern.matches(REG_NUMBER, value);
	}

	public static boolean isInteger(String value) {
		if(isNull(value))return false;
		return Pattern.matches(REG_INTEGER, value);
	}

	public static boolean isFloat(String value) {
		if(isNull(value))return false;
		return Pattern.matches(REG_FLOAT, value);
	}
	
	public static boolean isMoney(String value) {
		if(isNull(value))return false;
		return Pattern.matches(REG_MONEY, value);
	}
	
	public static boolean isPhone(String value) {
		if(isNull(value))return false;
		return Pattern.matches(REG_PHONE, value);
	}

	public static boolean isMobile(String value) {
		if(isNull(value))return false;
		return Pattern.matches(REG_MOBILE, value);
	}

	public static boolean isEmail(String value) {
		if(isNull(value))return false;
		return Pattern.matches(REG_EMAIL, value);
	}

	public static boolean isQQ(String value) {

		return Pattern.matches(REG_QQ, value);
	}

	public static boolean isZip(String value) {

		return Pattern.matches(REG_ZIP, value);
	}

	public static boolean isIP(String value) {

		return Pattern.matches(REG_IP, value);
	}

	public static boolean isURL(String value) {

		return Pattern.matches(REG_URL, value);
	}

	public static boolean isChinese(String value) {

		return Pattern.matches(REG_CHINESE, value);
	}
	
	public static String[] splitToArray(String str, String separatorChar,boolean removeDuplicateFlag) {
		String[] ret = null;
		if (isNull(str) || null == separatorChar) {
            return null;
        }
		
		
		ret = str.split(separatorChar);
		//用set集合过滤重复的关键字
		Set<String> set = new HashSet<String>();
        if(removeDuplicateFlag){
        	for (String string : ret) {
        		if(!"".equals(string)){
        			set.add(string.trim());
    			}
			}
        	
        }
        
        Object[] tempArrays = set.toArray();
        
        ret = new String[tempArrays.length];
        for (int i = 0 ;i <tempArrays.length;i++) {
        	ret[i] = (String)tempArrays[i];
		}
        return ret;
    }
	
	public static String[] splitToArray(String str, String separatorChar) {
	        // Performance tuned for 2.0 (JDK1.4)
	        if (isNull(str)) {
	            return null;
	        }
	        return str.split(separatorChar);
	    }
	   

	/** 验证是否为合法身份证 */
	public static boolean isIdcard(String value) {
		value = value.toUpperCase();
		if (!(Pattern.matches("^\\d{17}(\\d|X)$", value)||Pattern.matches("\\d{15}$", value))) {
			return false;
		}
		int provinceCode = Integer.parseInt(value.substring(0,2));
		if (provinceCode < 11 || provinceCode > 91) {
			return false;
		}
		return true;
	}

	public static boolean isDate(String value) {
		if(value==null || value.isEmpty())
			return false;
		try {
			new SimpleDateFormat().parse(value);
			return true;
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
	}


	public static String formatNumber(String fmt, Object value) {
		if (isNull(fmt) || isNull(value)) {
			return null;
		}
		String temp = new DecimalFormat(fmt).format(value);

		return temp;
	}

	/**交换两个日期 */
	public static void changeDate(Date date1,Date date2){
		if (isNull(date1,date2)) {
			return;
		}
		//判断两个时间的大小
		Calendar c1 =Calendar.getInstance();
		c1.setTime(date1);
		Calendar c2 =Calendar.getInstance();
		c2.setTime(date2);
		if (c1.after(c2)) {
			date1 = c2.getTime();
			date2 = c1.getTime();
		}
	}
	
	/** 比较两个日期相差的年数 */
	public static int compareYear(Date date1,Date date2){
		if (isNull(date1)||isNull(date2)) {
			return 0;
		}
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date1);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(date2);
		
		if (c1.equals(c2)) {
			return 0;
		}
		
		if (c1.after(c2)) {
			Calendar temp = c1;
			c1 = c2;
			c2 = temp;
		}
		//计算差值
		int result = c2.get(Calendar.YEAR)-c1.get(Calendar.YEAR);
		return result;
	}
	
	/** 比较两个日期相差的天数 */
	public static int compareDay(Date date1, Date date2) {
		if (date1 == null || date2 == null)
			return 0;

		Calendar d1 = Calendar.getInstance();
		d1.setTime(date1);
		Calendar d2 = Calendar.getInstance();
		d2.setTime(date2);
		if (d1.after(d2)) {
			Calendar swap = d1;
			d1 = d2;
			d2 = swap;
		}
		/*
		 * 经过上面的处理，保证d2在d1之后
		 * 下面这个days可能小于0，因为d2和d1可能不在同一年里，这样的话虽然d1的年份小，但其在一年中的"第几天"却可能比d2大。
		 */
		int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
		int y2 = d2.get(Calendar.YEAR);
		if (d1.get(Calendar.YEAR) != y2) {//如果不在同一年
			d1 = (Calendar) d1.clone();
			do {
				days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);
				/*
				 * 给定此 Calendar 的时间值，返回指定日历字段可能拥有的最大值。
				 * 例如，在某些年份中，MONTH 字段的实际最大值是 12，而在希伯来日历系统的其他年份中，该字段的实际最大值是 13。
				 * DAY_OF_YEAR：闰年366？
				 */
				d1.add(Calendar.YEAR, 1);
			} while (d1.get(Calendar.YEAR) != y2);
		}
		return days;

	}

	/** 比较两个日期相差的周数 */
	public static int compareWeek(Date date1,Date date2){
		if (isNull(date1)||isNull(date2)) {
			return 0;
		}
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date1);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(date2);
		
		if (c1.equals(c2)) {
			return 0;
		}
		
		if (c1.after(c2)) {
			Calendar temp = c1;
			c1 = c2;
			c2 = temp;
		}
		//计算差值
		int result = c2.get(Calendar.WEEK_OF_MONTH)-c1.get(Calendar.WEEK_OF_MONTH);
		return result;
	}
	
	/** 比较两个日期相差的月数 */
	public static int compareMonth(Date date1, Date date2) {
		if (date1 == null || date2 == null)
			return 0;

		int iMonth = 0;
		int flag = 0;
		try {
			Calendar objCalendarDate1 = Calendar.getInstance();
			objCalendarDate1.setTime(date1);

			Calendar objCalendarDate2 = Calendar.getInstance();
			objCalendarDate2.setTime(date2);

			if (objCalendarDate2.equals(objCalendarDate1))
				return 0;
			if (objCalendarDate1.after(objCalendarDate2)) {
				Calendar temp = objCalendarDate1;
				objCalendarDate1 = objCalendarDate2;
				objCalendarDate2 = temp;
			}

			if (objCalendarDate2.get(Calendar.YEAR) > objCalendarDate1.get(Calendar.YEAR))
				iMonth = ((objCalendarDate2.get(Calendar.YEAR) - objCalendarDate1.get(Calendar.YEAR))* 12 + objCalendarDate2.get(Calendar.MONTH) - flag)- objCalendarDate1.get(Calendar.MONTH);
			else
				iMonth = objCalendarDate2.get(Calendar.MONTH)- objCalendarDate1.get(Calendar.MONTH) - flag;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return iMonth;
	}

	/** 比较两个日期相差的秒数 */
	public static long compareTime(Date date1, Date date2) {
		if (date1 == null || date2 == null)
			return 0;

		Calendar c = Calendar.getInstance();

		c.setTime(date1);
		long l1 = c.getTimeInMillis();

		c.setTime(date2);
		long l2 = c.getTimeInMillis();

		return (l2 - l1) / 1000;
	}


	//设置日期时间
	private static Date setDateTime(Date date,int type,int num){
		if (date == null) {
			return null;
		}
		//初始化日历对象
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		//根据类型添加
		switch(type){
		case 1:		//添加年
			cal.set(Calendar.YEAR, num);
			break;
		case 2:		//添加月
			cal.set(Calendar.MONTH, num);
			break;
		case 3:		//添加日
			cal.set(Calendar.DATE, num);
			break;
		case 4:		//添加时
			cal.set(Calendar.HOUR_OF_DAY, num);
			break;
		case 5:		//添加分
			cal.set(Calendar.MINUTE, num);
			break;
		case 6:		//添加秒
			cal.set(Calendar.SECOND, num);
			break;
		}
		
		//返回操作结果
		return cal.getTime();
	}
	

	/** MD5加密 */
	public static String md5(String value) {
		StringBuilder result = new StringBuilder();

		try {
			// 实例化MD5加载类
			MessageDigest md5 = MessageDigest.getInstance("MD5");

			// 得到字节数据
			byte[] data = md5.digest(value.getBytes("UTF-8"));

			result.append(byte2hex(data));

		} catch (Exception e) {
			e.printStackTrace();
		}

		// 返回结果
		return result.toString().toUpperCase();
	}

	public static String byte2hex(byte[] data) {

		StringBuilder result = new StringBuilder();

		for (byte b : data) {
			// 将二进制转换成字符串
			String temp = Integer.toHexString(b & 0XFF);
			// 追加加密后的内容
			if (temp.length() == 1) { // 判断字符长度
				result.append("0").append(temp);
			} else {
				result.append(temp);
			}
		}

		return result.toString();
	}


	public static String htmlEncode(String value) {
		String result = "";
		if (!isNull(value)) {
			result = value.replaceAll("&", "&amp;").replaceAll(">", "&gt;").replaceAll("<", "&lt;").replaceAll("\"", "&quot;").replaceAll(" ", "&nbsp;").replaceAll("\r?\n", "<br/>");
		}
		return result;
	}

	public static String htmlDecode(String value) {
		String result = "";
		if (!isNull(value)) {
			result = value.replaceAll("&amp;", "&").replaceAll("&gt;", ">").replaceAll("&lt;", "<").replaceAll("&quot;", "\"").replace("&nbsp;", " ");
		}
		return result;
	}

	/** 字符串编码(默认使用UTF-8) */
	public static String stringEncode(String value){
		return stringEncode(value,"UTF-8");
	}
	
	public static String stringEncode(String value,String encoding){
		String result = null;
		if (!isNull(value)) {
			try {
				if (isNull(encoding)) {
					encoding = "UTF-8";
				}
				result = new String(value.getBytes("ISO-8859-1"),encoding);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 格式式化字符串
	 * 允许使用{0}{1}...作为为占位符
	 * @param value 要格式化的字符串
	 * @param args 点位符的值
	 * @return 格式化后的字符串
	 */
	public static String stringFormat(String value, Object... args) {
		// 判断是否为空
		if (isNull(value) || isNull(args)) {
			return value;
		}
		String result = value;
		Pattern p = Pattern.compile("\\{(\\d+)\\}");
		Matcher m = p.matcher(value);
		while (m.find()) {
			// 获取{}里面的数字作为匹配组的下标取值
			int index = Integer.parseInt(m.group(1));
			// 这里得考虑数组越界问题，{1000}也能取到值么？？
			if (index < args.length) {
				// 替换，以{}数字为下标，在参数数组中取值
				result = result.replace(m.group(), args[index].toString());
			}
		}
		return result;
	}
	

	/** 处理对象的String类型的属性值进行html编码 */
	public static void objectHtmlEncode(Object object) {
		if (!isNull(object)) {
			Method[] mList = object.getClass().getMethods();
			for (Method method : mList) {
				// 方法名
				String mName = method.getName();
				// 得到方法的方法值类型
				String mRetrunType = method.getReturnType().getSimpleName();
				// 得到方法的参数个数
				int mParamSize = method.getParameterTypes().length;
				// 判断方法值是否是String并参数个数为0
				if (mRetrunType.equals("String") && mParamSize == 0) {
					// 判断方法是否是以get开头
					if (mName.startsWith("get")) {
						// 得到相对应的set方法
						Method setMethod = null;
						String setMethodName = "set" + mName.substring(3);
						// 只有一个String类型的参数
						Class[] paramClass = { String.class };
						try {
							setMethod = object.getClass().getMethod(setMethodName, paramClass);
							// 判断set方法的返回值是否为空
							if (!setMethod.getReturnType().getSimpleName().equals("void")) {
								continue; // 查看下一个方法
							}
						} catch (SecurityException e) {
							continue; // 查看下一个方法
						} catch (NoSuchMethodException e) {
							continue; // 查看下一个方法
						}
						Object[] params = null;
						try {
							// 得到方法的值
							String mValue = method.invoke(object, params).toString();
							// 对值进行html编码
							mValue = htmlEncode(mValue);
							// 编码后重新赋值
							params = new Object[] { mValue };
							setMethod.invoke(object, params);
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	/**
	 * 根据属性名得到属性值(entity中必需存在get,set相应方法)
	 * @param entity
	 * @param propName
     * @return
     */
	public static Object getPropValue(Object entity,String propName){
		Object result = null;
		//判断对象和属性名是否为空
		if (isNull(entity)||isNull(propName)) {
			return result;
		}else{
			try {
				//调用方法得到get方法值
				Method getMethod = entity.getClass().getMethod(propName.trim());
				result = getMethod.invoke(entity	);
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	

	
	/** 生成随机UUID */
	public static String genUUID(){
		UUID uuid = UUID.randomUUID();
		String temp = uuid.toString();
		return temp.replaceAll("-", "").toUpperCase();
	}
	


	/** 保存文件 */
	public static void saveToFile(File target, String distPath) throws IOException {
		if (isNull(target,distPath)) {
			return;
		}
		File distFile = new File(distPath);
		//确保文件所在的文件夹都存在
		distFile.getParentFile().mkdirs();
		
		//输入流
		InputStream is = new BufferedInputStream(new FileInputStream(target));
		//输出流
		OutputStream os = new BufferedOutputStream(new FileOutputStream(distFile));
		//每次读取的大小
		byte[] size = new byte[is.available()];
		//流长度
		int len = 0;
		//循环读取
		while((len = is.read(size)) != -1){
			os.write(size, 0, len);
		}
		os.flush();
		os.close();
		is.close();
	}
	
	public static void saveToFile(InputStream target, String distPath) throws IOException {
		if (isNull(target,distPath)) {
			return;
		}
		File distFile = new File(distPath);
		//确保文件所在的文件夹都存在
		distFile.getParentFile().mkdirs();
		
		//输入流
		InputStream is = new BufferedInputStream(target);
		//输出流
		OutputStream os = new BufferedOutputStream(new FileOutputStream(distFile));
		//每次读取的大小
		byte[] size = new byte[1024];
		//流长度
		int len = 0;
		//循环读取
		while((len = is.read(size)) != -1){
			os.write(size, 0, len);
		}
		os.flush();
		os.close();
		is.close();
	}


	/** 使用正则表达查询字符串 */
	public static List<String> findStr(Object target, String regex){
		if(isNull(target,regex)){
			return null;
		}
		Pattern pattern = Pattern.compile(regex);		//正则表达式
		Matcher matcher = pattern.matcher(target.toString());	//操作的字符串
		List<String> tmp = new ArrayList<String>();
		while (matcher.find()) {
			tmp.add(matcher.group());
		}
		return tmp;
	}
	

	/**
	 * 直接删除非空目录
	 * @param dir File对象
	 */
	public static void deleteDirectory(File dir){
		 if(dir == null || !dir.exists() || !dir.isDirectory())
	        return; // 检查参数
	    for (File file : dir.listFiles()) {
	        if (file.isFile())
	            file.delete(); // 删除所有文件
	        else if (file.isDirectory())
	        	deleteDirectory(file); // 递规的方式删除文件夹
	    }
	    dir.delete();// 删除目录本身 
	}
	
	/**
	 * 直接删除非空目录
	 * @param dirPath 要删除的目录的绝对路径
	 */
	public static void deleteDirectory(String dirPath){
		File dir = new File(dirPath);
		deleteDirectory(dir);
	}
	

    /** 隐藏部分验证码数据 */
	public static String hideCode(String isHide, String target){
		// 0.不隐藏 1.隐藏
		if (!"0".equals(isHide)) {
			return target;
		}
		
		int code_len = 0;	
		if (target != null) {
			target = target.replaceAll("\\s", "");
			code_len = target.length();
		}else{
			return "";
		}
		
		if ( code_len > 8) {
			String s1 = target.substring(0, 4);
			String s2 = target.substring(4, code_len-4).replaceAll("\\w", "*");
			String s3 = target.substring(code_len-4, code_len);
			if(s2.length()>6){
				s2 = s2.substring(0,6);
			}
			String code = s1+s2+s3;
		
			return code;
		}else{
			return target.replaceAll("\\w", "*");
		}
	}
	
	/** 隐藏部分验证码数据 */
	public static String hideCode(String target){
		return hideCode("0", target);
	}
	


	/** 通过文件相对路径得到Web项目下的绝对路径 */
	public static String getPath(String path){
		URL url = CommonUtil.class.getResource(path);
		if (url != null) {
			String abs_path = CommonUtil.class.getResource(path).toString();
			abs_path = abs_path.substring(abs_path.indexOf("/")+1);
			return abs_path;
		}
		return null;
	}
	

	
	/**
	 * 获取项目物理路径 
	 */
	public static String getProjectPath(){
		String path = CommonUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath(); 
		if(path.indexOf("WEB-INF")>0){
			path = path.substring(0,path.indexOf("/WEB-INF"));
		}
		if(!path.endsWith("/")){
			path = path+"/";
		}
		if(path.startsWith("/")){
			path = path.substring(1);
		}
		return path;
	}
	
	
	/**
	 * 将姓名电话号码等字符串用*代替一部分
	 * @param str
	 * @return
	 */
	public static String replaceChar(String str){
		if(null == str)
			return "";
		int count = str.length();
		if(count == 1)
			return str+="*";
		StringBuffer strb = new StringBuffer(str);
		if(count == 2)
			strb.replace(1, 2, "*");
		if(count == 3)
			strb.replace(1, 2, "*");
		if(count > 3 && count < 9)
			strb.replace(1, count-1, "***");
		if(count > 9 && count < 11)
			strb.replace(3, count-3, "****");
		if(count >= 11)
			strb.replace(3, count-4, "*****");
		return strb.toString();
	}


}
