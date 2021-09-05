package thaler;

abstract public class Struct<T> {
	abstract public T get(Object index);

	abstract public void put(Object index, T value);

}
