package thaler;

import static thaler.Console.*;
import static thaler.Dict.*;
import static thaler.List.*;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Json {

	public static Dict parse(String text) {
		var dict = Dict$();
		new JsonParser().parse(text, dict);
		return dict;
	}

	public static void parse(String text, Dict dict) {
		new JsonParser().parse(text, dict);
	}

	public static void parse(String text, List list) {
		new JsonParser().parse(text, list);
	}

	private static class JsonParser {
		int m_Index;
		String m_Text;
		char m_Next;

		public void parse(String text, Dict dict) {
			m_Text = text;
			m_Index = 0;
			m_Next = 0;
			if (text == null || text.length() == 0)
				throw new IllegalArgumentException("json string cannot be null or empty");
			nextChar();
			parseObject(dict);
		}

		public void parse(String text, List list) {
			m_Text = text;
			m_Index = 0;
			m_Next = 0;
			if (text == null || text.length() == 0)
				throw new IllegalArgumentException("an array string cannot be null or empty");
			nextChar();
			parseArray(list);
		}

		void parseObject(Dict dict) {

			expect('{');
			skip();
			if (m_Next != '}') {
				do {
					skip();
					var key = parseString();
					skip();
					expect(':');
					var val = parseValue();
					dict.put(key, val);
				} while (nextIs(','));
			}
			expect('}');
		}

		boolean isTermntr(char chr) {
			return (chr == '"') || (chr == '\'') || (chr == '`');
		}

		String parseString() {
			StringBuilder str = new StringBuilder();

			if (!isTermntr(m_Next))
				throw new IllegalArgumentException("illegal string terminator character '" + m_Next + "'");
			char termntr = m_Next;
			nextChar();
			while (m_Next != termntr) {
				str.append(m_Next);
				nextChar();
			}
			nextChar();
			//println("string " + str);
			return str.toString();
		}

		public void parseArray(List list) {
			expect('[');
			skip();
			if (m_Next != ']') {
				do {
					list.append(parseValue());
				} while (nextIs(','));
			}
			expect(']');
		}

		Object parseLiteral() {

			StringBuilder str = new StringBuilder();
			while (Character.isAlphabetic(m_Next)) {
				str.append(m_Next);
				nextChar();
			}
			String lit = str.toString();
			//println("literal " + lit);
			if ("true".equals(lit))
				return true;
			else if ("false".equals(lit))
				return false;
			else if ("null".equals(lit))
				return null;
			else
				throw new IllegalArgumentException("unrecognized literal '" + lit + "'");

		}

		Object parseNumber() {
			StringBuilder str = new StringBuilder();
			if (m_Next == '-') {
				str.append(m_Next);
				nextChar();
			}

			if (!Character.isDigit(m_Next))
				throw new IllegalArgumentException("expected a digit, found  '" + m_Next + "'");

			if (m_Next != '0')
				while (Character.isDigit(m_Next)) {
					str.append(m_Next);
					nextChar();
				}
			else {
				str.append(m_Next);
				nextChar();
			}

			if (m_Next == '.') {
				nextChar();
				while (Character.isDigit(m_Next)) {
					str.append(m_Next);
					nextChar();
				}
			}
			if (m_Next == 'e' || m_Next == 'E') {
				str.append(m_Next);
				nextChar();
				if (!Character.isDigit(m_Next))
					throw new IllegalArgumentException("expected a digit, found  '" + m_Next + "'");

				while (Character.isDigit(m_Next)) {
					str.append(m_Next);
					nextChar();
				}
			}
			//println("number " + str);
			try {
				BigDecimal bd = new BigDecimal(str.toString());
				try {
					BigInteger bi = bd.toBigIntegerExact();

					int iv = bi.intValue();
					if (bi.equals(BigInteger.valueOf(iv)))
						return iv;
					long lv = bi.longValue();
					if (bi.equals(BigInteger.valueOf(lv)))
						return lv;
					return bi;
				} catch (ArithmeticException ae) {
					double dv = bd.doubleValue();
					if (bd.equals(BigDecimal.valueOf(dv)))
						return dv;
					return bd;
				}
			} catch (NumberFormatException fe) {
				throw new IllegalArgumentException("bad number formar  '" + str + "'");
			}

		}

		Object parseValue() {
			Object val;
			skip();
			if (isTermntr(m_Next))
				val = parseString();
			else if (m_Next == '{') {
				var dict = Dict$();
				parseObject(dict);
				val = dict;
			} else if (m_Next == '[') {
				var list = List$();
				parseArray(list);
				val = list;
			} else if (isAlpha(m_Next))
				val = parseLiteral();
			else
				val = parseNumber();
			skip();
			return val;
		}

		char nextChar() {
			if (m_Index < m_Text.length())
				return m_Next = m_Text.charAt(m_Index++);
			else
				return m_Next = (char) 0;
		}

		void expect(char chr) {
			if (m_Next != chr)
				throw new IllegalArgumentException("expected '" + chr + "' found '" + m_Next + "'");
			nextChar();
		}

		boolean isAlpha(char ch) {
			return Character.isAlphabetic(ch);
		}

		boolean isWhite(char ch) {
			return ch == ' ' || ch == '\n' || ch == '\t' || ch == '\r';
		}

		boolean nextIs(char chr) {
			if (m_Next == chr) {
				nextChar();
				return true;
			} else
				return false;
		}

		void skip() {
			while (isWhite(m_Next))
				nextChar();
		}
	}

	Json() {
	}

}
