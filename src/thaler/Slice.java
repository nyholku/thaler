package thaler;

import java.util.Iterator;

import thaler.Slice.IterableSlice;

public class Slice {
	Integer m_Start;
	Integer m_Stop;
	Integer m_Step;

	static public IterableSlice IterableSlice$(int size, Integer start, Integer stop, Integer step) {
		return new IterableSlice(size, start, stop, step);
	}

	static public class IterableSlice extends Slice implements Iterable<Integer> {
		int m_Size;

		IterableSlice(int size, Slice slice) {
			super(slice.m_Start, slice.m_Stop, slice.m_Stop);
			m_Size = size;
		}

		IterableSlice(int size, Integer start, Integer stop, Integer step) {
			super(start, stop, step);
			m_Size = size;
		}

		@Override
		public Iterator<Integer> iterator() {
			return new SliceIterator(m_Size, m_Start, m_Stop, m_Step);
		}

	}

	static int sgn(int x) {
		return x < 0 ? -1 : x > 0 ? 1 : 0;
	}

	static class SliceIterator implements Iterator<Integer> {
		int m_Index;
		int m_N;
		int m_Step;

		public SliceIterator(int size, Integer start, Integer stop, Integer step) {
			if (start == null)
				start = 0;
			if (step == null)
				step = 1;
			if (stop == null)
				stop = step >= 0 ? size : -size - 1;

			if (start < 0)
				start = size + start;
			if (stop < 0)
				stop = size + stop;
			if (stop < 0)
				stop = -1;
			if (stop > size)
				stop = size;

			if (start != null && (start < 0 || start >= size))
				throw new IndexOutOfBoundsException("slice start index " + start + " out of bounds ");

			m_N = (stop - start + step - sgn(step)) / step;
			m_Index = start;
			m_Step = step;
		}

		public boolean hasNext() {
			return m_N > 0;
		}

		@Override
		public Integer next() {
			int t = m_Index;
			m_Index += m_Step;
			m_N--;
			return t;
		}
	}

	static public Slice Slice$(Integer start) {
		return new Slice(start, null, null);
	}

	static public Slice Slice$(Integer start, Integer stop) {
		return new Slice(start, stop, null);
	}

	static public Slice Slice$(Integer start, Integer stop, Integer step) {
		return new Slice(start, stop, step);
	}

	public Slice(Integer start, Integer stop, Integer step) {
		m_Start = start;
		m_Stop = stop;
		m_Step = step;
		//		if (start != null && (start < 0 || start >= size))
		//			throw new IndexOutOfBoundsException("slice start index " + start + " out of bounds ");
	}

	public String toString() {
		return "Slice(" + m_Start + "," + m_Stop + "," + m_Step + ")";
	}

	public IterableSlice bind(List list) {
		return IterableSlice$(list.length(), m_Start, m_Stop, m_Step);
	}

}
