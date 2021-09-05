package demo;

import java.util.Iterator;

import thaler.Struct;

public class StaticFuns<T> extends Struct<T> implements Iterable<T> {
	public static StaticFuns fun() {
		return null;
	}

	public static StaticFuns fun(String s) {
		return null;
	}

	@Override
	public T get(Object index) {
		return null;
	}

	@Override
	public Iterator<T> iterator() {
		return null;
	}

	@Override
	public void put(Object index, T value) {
	}
}
