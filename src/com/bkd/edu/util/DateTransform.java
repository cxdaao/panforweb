package com.bkd.edu.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 日期转换工具类
 * 输入Date类型
 * 转换输出为能保存到数据库中的类型变量
 * @author CXDAAO
 * span
 * @vision 0.1
 */
public class DateTransform {
	static public Timestamp transDate(Date date){
		Timestamp ts = new Timestamp(date.getTime());
		return ts;
	}
	
	static public String simpleTransDate(Date date){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String stringDate = df.format(date);
        return stringDate;
	}
}
