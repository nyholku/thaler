package thaler;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Math {
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
}
