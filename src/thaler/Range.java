package thaler;

import java.util.Iterator;

public class Range implements Iterable<Integer> {
	int m_Start;
	int m_Stop;
	int m_Step;

	public static Range Range$(int stop) {
		return new Range(stop);

	}

	public static Range Range$(int start, int stop) {
		return new Range(start, stop);

	}

	public static Range Range$(int start, int stop, int step) {
		return new Range(start, stop, step);
	}

	public Range(int stop) {
		this(0, stop, 1);

	}

	public Range(int start, int stop) {
		this(start, stop, 1);

	}

	public Range(int start, int stop, int step) {
		if (step == 0)
			throw new IllegalArgumentException("step cannot be zero");
		m_Start = start;
		m_Stop = stop;
		m_Step = step;
	}

	public Iterator<Integer> iterator() {
		return new Iterator<Integer>() {
			int m_Idx = m_Start;

			@Override
			public boolean hasNext() {
				return m_Step > 0 ? (m_Idx < m_Stop) : (m_Idx > m_Stop);
			}

			@Override
			public Integer next() {
				int t = m_Idx;
				m_Idx += m_Step;
				return t;
			}
		};

	}
}
