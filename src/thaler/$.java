package thaler;

import static thaler.$.List$;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Locale;
import java.util.Scanner;

import thaler.Generator.Wrap;

public class $ {
	public static List List$() {
		return new List();
	}

	public static List List$(String str) {
		return new List(str);
	}

	public static <T> List<T> List$(T... args) {
		return new List<T>(args);
	}

	public static List<Integer> List$(Iterable<Integer> itrble) {
		return new List(itrble);
	}

	public static <T> List List$(T x, Iterable<T> itrble) {
		return new List(itrble);
	}

	//---

	public static Dict Dict$() {
		return new Dict();
	}

	public static Dict Dict$(List list) {
		return new Dict(list);
	}

	public static Dict Dict$(Object... args) {
		return new Dict(args);
	}

	public static Dict Dict$(String str) {
		return new Dict(str);
	}

	//--
	public static Double sqrt(double x) {
		return java.lang.Math.sqrt(x);
	}

	private static boolean equal(BigDecimal a, Number b) {
		BigDecimal bd;
		if ((b instanceof BigDecimal))
			bd = (BigDecimal) b;
		else {
			if (b instanceof BigInteger)
				bd = new BigDecimal((BigInteger) b);
			else if (b instanceof Double || b instanceof Float) {
				double dv = b.doubleValue();
				if (dv != java.lang.Math.rint(dv))
					return false;
				bd = BigDecimal.valueOf(b.doubleValue());
			} else if (b instanceof Byte || b instanceof Short || b instanceof Integer || b instanceof Long)
				bd = BigDecimal.valueOf(b.longValue());
			else
				return false;
		}
		return a.compareTo(bd) == 0;
	}

	public static boolean equal(Object a, Object b) {
		if (a == b)
			return true;
		if (a == null || b == null)
			return false;
		if (a instanceof Number && b instanceof Number) {
			Number an = (Number) a;
			Number bn = (Number) b;
			if (an instanceof BigDecimal)
				return equal((BigDecimal) an, bn);
			if (bn instanceof BigDecimal)
				return equal((BigDecimal) bn, an);
			if (an instanceof BigInteger)
				return equal(new BigDecimal((BigInteger) an), bn);
			if (bn instanceof BigInteger)
				return equal(new BigDecimal((BigInteger) bn), an);
			if (an instanceof Double || bn instanceof Double)
				return an.doubleValue() == bn.doubleValue();
			if (an instanceof Float || bn instanceof Float)
				return an.doubleValue() == bn.doubleValue();
			if (!(a instanceof Byte || a instanceof Short || a instanceof Integer || a instanceof Long))
				return false;
			if (!(b instanceof Byte || b instanceof Short || b instanceof Integer || b instanceof Long))
				return false;
			return an.longValue() == bn.longValue();
		}
		return a.equals(b);

	}

	//--
	public static Range Range$(int stop) {
		return new Range(stop);

	}

	public static Range Range$(int start, int stop) {
		return new Range(start, stop);

	}

	public static Range Range$(int start, int stop, int step) {
		return new Range(start, stop, step);
	}
	//--

	public static <T> Generator<T> Generator$(T x, GenFun genFun) {
		return new Generator(genFun);
	}

	//--

	static public <Q> Wrap<Q> $(Iterable<Q> q) {
		return new Wrap<Q>(q);
	}

	//--
	static public Slice Slice$(Integer start) {
		return new Slice(start, null, null);
	}

	static public Slice Slice$(Integer start, Integer stop) {
		return new Slice(start, stop, null);
	}

	static public Slice Slice$(Integer start, Integer stop, Integer step) {
		return new Slice(start, stop, step);
	}

	// --

	public static List<String> asList(String str) {
		var list = List$();
		for (int i = 0; i < str.length(); i++)
			list.append("" + str.charAt(i));
		return list;
	}

	public static String asString(List list) {
		StringBuilder str = new StringBuilder();
		for (Object obj : list)
			str.append(obj);
		return str.toString();
	}

	//--
	public static void del(List list, Slice slice) {
		var l = List$();
		var p = 0;
		for (var i : slice.bind(list)) {
			if (i > p)
				l.inset(0, i);
			else
				l.append(i);
			p = i;
		}
		for (var i : l)
			list.del((int) i);
	}

	//--
	public static void printf(String format, Object... args) {
		System.out.printf(Locale.US, format, args);
	}

	public static void print(String format, Object... args) {
		printf(format, args);
	}

	public static void println(String format, Object... args) {
		print(format, args);
		println();
	}

	public static String input(String prompt) {
		printf("%s ", prompt);
		return input();
	}

	public static void print(Object x) {
		System.out.print(x);
	}

	public static void println(Object x) {
		System.out.println(x);
	}

	public static void println() {
		System.out.println();
	}

	public static String input() {
		return new Scanner(System.in).nextLine();
	}

	public static Double double$(String x) {
		println(x);
		return Double.parseDouble(x);
	}

}
