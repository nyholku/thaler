package thaler;

import java.util.Iterator;
import java.util.LinkedList;
import static thaler.Slice.*;
import static thaler.Console.*;

public class List<T> extends Struct<T> implements Iterable<T> {
	private LinkedList<T> m_List = new LinkedList<T>();

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

	public List(String str) {
		Json.parse(str, this);
	}

	public List(Object... args) {
		for (Object obj : args)
			append((T) obj);
	}

	public List(Iterable<T> itrble) {
		for (Object obj : itrble)
			append((T) obj);
	}

	public void append(T obj) {
		m_List.add(obj);
	}

	public void extend(Iterable<T> list) {
		for (T obj : list)
			append(obj);
	}

	public void inset(int idx, T obj) {
		m_List.add(idx, obj);
	}

	public void remove(Object obj) {
		m_List.remove(obj);
	}

	public void clear() {
		m_List.clear();
	}

	public void del(int idx) {
		m_List.remove(idx);
	}

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

	public T get(Object idx) {
		return m_List.get(obj2idx(idx));
	}

	int obj2idx(Object obj) {
		Number n = (Number) obj;
		long i = n.longValue();
		if (i < Integer.MIN_VALUE || i > Integer.MAX_VALUE)
			throw new IllegalArgumentException("idx " + i + " out of int range ");
		int mxi = m_List.size() - 1;
		if (i > mxi)
			throw new IllegalArgumentException("idx " + i + " out of range 0.." + mxi);
		double d = n.doubleValue();
		if (i != d)
			throw new IllegalArgumentException("idx " + n + " not integer");
		return (int) i;
	}

	public void put(Object idx, T value) {
		m_List.set(obj2idx(idx), value);
	}

	private String toString(Object x) {
		if (x == null)
			return "null";
		if (x instanceof String)
			return "\"" + x + "\"";
		return x.toString();
	}

	public int length() {
		return m_List.size();
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("[");
		for (Object obj : m_List) {
			if (s.length() > 1)
				s.append(" , ");
			s.append(toString(obj));
		}
		s.append("]");
		return s.toString();
	}

	@Override
	public Iterator<T> iterator() {
		return m_List.iterator();
	}


	public List slice(Slice slice) {
		List list = List$();
		for (var i : new IterableSlice(m_List.size(), slice))
			list.append(m_List.get(i));
		return list;
	}

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

}
