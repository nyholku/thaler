package thaler;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;

import static thaler.Console.*;

public class Generator<T> implements Iterable<T>, AutoCloseable {
	GenFun m_GenFun;

	static class GenInterrupted extends RuntimeException {

	}

	public Generator(GenFun genFun) {
		m_GenFun = genFun;
	}

	public static <T> Generator<T> Generator$(T x, GenFun genFun) {
		return new Generator(genFun);
	}

	public ClosableIterator<T> iterator() {
		return new GeneratorIterator<T>(m_GenFun);
	};

	interface ClosableIterator<T> extends AutoCloseable, Iterator<T> {
		void close();
	}

	static public class Wrap<T> implements AutoCloseable, Iterable<T> {

		Iterable<T> m_Iterable;
		Iterator<T> m_Iterator;

		public Wrap(Iterable<T> l) {
			m_Iterable = l;
		}

		@Override
		public void close() {
			try {
				if (m_Iterator != null && m_Iterator instanceof AutoCloseable)
					((AutoCloseable) m_Iterator).close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		@Override
		public Iterator<T> iterator() {
			m_Iterator = m_Iterable.iterator();
			return m_Iterator;
		}

	}

	static public <Q> Wrap<Q> $(Iterable<Q> q) {
		return new Wrap<Q>(q);
	}

	public static void main(String... args) {
		var g = Generator$(1, ($) -> {
			$.yield(2);
			$.yield(3);
			$.yield(4);
			for (var i = 1; i < 100; i += 7) {
				$.yield(i);
			}

		});
		for (var i : g)
			println(i + 2);
	}

	@Override
	public void close() {
	}

}
