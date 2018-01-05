package com.mapi.bomb;

import java.math.BigDecimal;
import java.util.Date;

public class TypeChange {

	/**
	 * 类型转换，包括String 和 BigDecimal,不包含boolean
	 * @param value
	 * @return
	 */
	public static Object changeType(Object value) {
		String classType = value.getClass().getSimpleName();

		switch (classType) {
		case "Long":
			return longToDouble((Long)value);
			
		case "Integer":

			return integerToDouble((Integer)value);
		case "Short":

			return shortToDouble((Short)value);

		case "Byte":

			return byteToDouble((Byte)value);
		case "Float":

			return floatToString((Float)value);
		case "Double":
			
			return doubleToString((Double)value);
		case "String":

			return ((String)value).toCharArray();
		case "Character":

			return charToInt((char)value);
		case "BigDecimal":

			return bigdecimalToString((BigDecimal)value);

		default:
			return null;
		}
		

		
	}

	public static String intToString(Integer value) {

		Integer integer = new Integer(value);
		return integer.toString();
	}

	public static String floatToString(Float value) {
		Float f = new Float(value);

		return f.toString();
	}

	public static String doubleToString(Double value) {
		Double d = new Double(value);

		return d.toString();
	}

	public static String bigdecimalToString(BigDecimal value) {

		return value.toString();
	}

	public static String dateToString(Date date) {

		return date.toString();
	}

	public static Double byteToDouble(Byte by) {

		double d = by.doubleValue();
		return d;
	}

	public static Double shortToDouble(Short s) {

		double d = s.doubleValue();
		return d;
	}

	public static Double integerToDouble(Integer i) {

		double d = i.doubleValue();
		return d;
	}

	public static Double longToDouble(Long l) {

		double d = l.doubleValue();
		return d;
	}

	public static Double floatToDouble(Float f) {

		double d = f.doubleValue();
		return d;
	}

	public static Integer charToInt(Character c) {

		int i = c;
		return new Integer(i);
	}

	public static void main(String[] args) {

		char a = 4;
		double c = a;
		System.out.println(c);
		
		
		String ss = "就像";
		char[] charArray = ss.toCharArray();
		System.out.println(charArray.getClass().getSimpleName());
		
		
		Character cj = 2;
		Object cha = charToInt((char)cj);
		Object changeType = changeType(cj);
		System.out.println(changeType + ":" + changeType.getClass().getSimpleName());
	}

}
