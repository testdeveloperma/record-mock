package com.mapi.bomb;

import java.math.BigDecimal;
 
public class DataChange {

	public static Object getEmpty(String classType) {

		switch (classType) {
		case "BigDecimal":
			BigDecimal bd = BigDecimal.ZERO;
			return bd;
		case "Integer":
			int i = 0;
			return i;
		case "Double":
			double d = 0;
			return d;
		case "Float":
			float f = 0;
			return f;
		case "Long":
			long l = 0;
			return l;
		case "Short":
			Short s = 0;
			return s;
		case "Byte":
			Byte by = 0;
			return by;
		case "Character":
			char c = 0;
			return c;
		case "Boolean":
			Boolean b = false;
			return b;
		case "String":
			String str = "";
			return str;

		default:
			return null;

		}

	}
	
	public static Object getChanageValue(String classType) {

		switch (classType) {
		case "BigDecimal":
			BigDecimal bd = new BigDecimal(Double.MAX_VALUE);
			return bd;

		case "Integer":
			int i = Integer.MAX_VALUE;
			return i;

		case "Double":
			double d = Double.MAX_VALUE;
			return d;
		case "Float":
			float f = Float.MAX_VALUE;
			return 0;
		case "Long":
			long l = Long.MAX_VALUE;
			return l;
		case "Short":
			Short s = Short.MAX_VALUE;
			return s;
		case "Byte":
			Byte by = Byte.MAX_VALUE;
			return by;
		case "Character":
			char c = Character.MAX_VALUE;
			return c;
		case "Boolean":
			Boolean b = false;
			return b;
		case "String":
			String str = new String(
					"《通知》规定，住房公积金管理中心要规范贷款业务流程，压缩审批时限，自受理贷款申请之日起10个工作日内完成审批工作。受托银行要及时受理职工住房公积金贷款申请和办理相关委托贷款手续，住房公积金管理中心要加强对受托银行的业务考核。要公开住房公积金贷款业务流程、审批要件、办理地点、办理部门和办结时限，并在业务网点予以明示。各地住房城乡建设、不动产登记、人民银行等部门要建立住房公积金贷款业务办理信息共享机制，让数据多跑路，职工不跑路或少跑路。");
			return str;

		default:
			return null;

		}
	}

	public static Object[] changeArrayLength(String classType, Object[] objarr) {

		switch (classType) {
		case "BigDecimal[]":
			BigDecimal[] bd = new BigDecimal[100];
			bd = (BigDecimal[]) initArray(bd, objarr);
			return bd;
			
		case "Integer[]":
			Integer[] i = new Integer[100];
			i = (Integer[]) initArray(i, objarr);
			return i;
		case "Double[]":
			Double[] d = new Double[100];
			d = (Double[]) initArray(d, objarr);
			return d;
		case "Float[]":
			Float[] f = new Float[100];
			f = (Float[]) initArray(f, objarr);
			return f;
		case "Long[]":
			Long[] l = new Long[100];
			l = (Long[]) initArray(l, objarr);
			return l;
		case "Short[]":
			Short[] s = new Short[100];
			s = (Short[]) initArray(s, objarr);
			return s;
		case "Byte[]":
			Byte[] by = new Byte[100];
			by = (Byte[]) initArray(by, objarr);
			return by;
		case "Character[]":
			Character[] c = new Character[100];
			c = (Character[]) initArray(c, objarr);
			return c;
		case "Boolean[]":
			Boolean[] b = new Boolean[100];
			b = (Boolean[]) initArray(b, objarr);
			return b;
		case "String[]":
			String[] str = new String[100];
			str = (String[]) initArray(str, objarr);
			return str;
		default:
			return null;

		}
	}

	/**
	 * 将旧数组 赋值给 新数组，实现数组复制
	 * 
	 * @param objnew
	 *            新数组
	 * @param objold
	 *            旧数组
	 * @return
	 */
	public static Object[] initArray(Object[] objnew, Object[] objold) {

		for (int i = 0; i < objold.length; i++) {
			objnew[i] = objold[i];
		}
		return objnew;
	}

	public static void main(String[] args) {
		Object i = getEmpty("Integer");
		Object f = getEmpty("Float");
		Object d = getEmpty("Double");
		Object l = getEmpty("Long");
		Object s = getEmpty("Short");
		Object by = getEmpty("Byte");
		Object c = getEmpty("Character");
		Object b = getEmpty("Boolean");
		Object str = getEmpty("String");
		System.out.println(i + ":" + i.getClass());

		System.out.println(f + ":" + f.getClass());
		System.out.println(d + ":" + d.getClass());

		System.out.println(l + ":" + l.getClass());
		System.out.println(s + ":" + s.getClass());

		System.out.println(by + ":" + by.getClass());
		System.out.println(c + ":" + c.getClass());

		System.out.println(b + ":" + b.getClass());

		System.out.println(str.getClass());
		String[] sarr = new String[30];

		System.out.println(sarr.getClass().getSimpleName());

		System.out.println(sarr.toString());

		String[] sss = new String[20];

		sss = sarr;

		System.out.println(sss.length);

		// for (String string : sss) {
		// System.out.println(string);
		// }

		// int[] o = null ;
		// for (Integer integer : o) {
		// System.out.println(integer);
		// }

		// Integer[] b1 = { 2, 5, 0, 2 };
		//
		// Object[] changeValue = getChangeValue("Integer[]", b1);
		// System.out.println(changeValue.getClass());
		// for (Object integer : changeValue) {
		// System.out.println(integer);
		// }
		System.out.println(getChanageValue("Double"));
		System.out.println(getChanageValue("Integer"));

		System.out.println(getChanageValue("Character"));

		BigDecimal bd = BigDecimal.valueOf(Double.MAX_VALUE);
		System.out.println(bd);

	}
}
