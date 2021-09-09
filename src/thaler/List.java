package thaler;

import java.util.Iterator;
import java.util.LinkedList;
import static thaler.$.*;
import thaler.$;
import thaler.Slice.IterableSlice;

public class List<T> extends Struct<T> implements Iterable<T> {
	private LinkedList<T> m_List = new LinkedList<T>();

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

	public List slice(Integer start) {
		return slice(start,null,null);
	}

	public List slice(Integer start,Integer stop) {
		return slice(start,stop,null);
	}

	public List slice(Integer start,Integer stop,Integer step) {
		var slice=Slice$(start,stop,step);
		List list = List$();
		for (var i : new IterableSlice(m_List.size(), slice))
			list.append(m_List.get(i));
		return list;
	}

}
