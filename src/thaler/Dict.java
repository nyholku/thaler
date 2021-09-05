package thaler;

import static thaler.List.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Dict<T> extends Struct<T> {
	private LinkedHashMap<Object, T> m_Map = new LinkedHashMap<>();

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

	public Dict() {
	}

	public Dict(String str) {
		Json.parse(str, this);
	}

	public Dict(List list) {
		if (list.length() % 2 != 0)
			throw new IllegalArgumentException("number of arguments is not even");
		for (int i = 0; i + 1 < list.length(); i += 2) {
			m_Map.put(list.get(i), (T) list.get(i + 1));
		}
	}

	public Dict(Object... args) {
		if (args.length % 2 != 0)
			throw new IllegalArgumentException("number of arguments is not even");
		for (int i = 0; i + 1 < args.length; i += 2) {
			m_Map.put(args[i], (T) args[i + 1]);
		}
	}

	public T get(Object key) {
		return m_Map.get(key);
	}

	public void put(Object key, T value) {
		m_Map.put(key, value);
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		for (Map.Entry entry : m_Map.entrySet()) {
			if (s.length() == 0)
				s.append("{ ");
			else
				s.append(" , ");
			s.append("\"");
			s.append(entry.getKey());
			s.append("\"");
			s.append(" : ");
			Object val = entry.getValue();
			if (val instanceof String) {
				s.append("\"");
				s.append(val);
				s.append("\"");
			} else
				s.append(val);
		}
		s.append(" }");
		return s.toString();
	}

}
