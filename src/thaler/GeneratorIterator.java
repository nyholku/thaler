package thaler;

import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;

import thaler.Generator.ClosableIterator;
import thaler.Generator.GenInterrupted;

public class GeneratorIterator<T> implements ClosableIterator {
	GenFun m_GenFun;

	// create an iterator object that reads from a backing thread where the generator runs
	ArrayBlockingQueue m_Queue = new ArrayBlockingQueue(2);

	volatile boolean m_Done;
	Thread m_Thread;

	GeneratorIterator(GenFun genFun) {
		m_GenFun = genFun;

		m_Thread = new Thread(() -> {
			try {
				m_GenFun.run(this);
			} catch (GenInterrupted ie) {
			} finally {
				m_Done = true;
			}

		});
		m_Thread.start();
	}

	public void yield(T x) {
		try {
			m_Queue.put(x);
		} catch (InterruptedException e) {
			throw new GenInterrupted();
		}
	}

	@Override
	public boolean hasNext() {
		return !(m_Done && m_Queue.isEmpty());
	}

	@Override
	public T next() {
		try {
			return (T) m_Queue.take();
		} catch (InterruptedException e) {
			throw new NoSuchElementException();
		}
	}

	@Override
	public void close() {
		m_Thread.interrupt();
	}
};