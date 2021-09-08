package thaler;

import java.util.Iterator;

interface ClosableIterator<T> extends AutoCloseable, Iterator<T> {
	void close();
}
