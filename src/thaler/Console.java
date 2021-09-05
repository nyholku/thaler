package thaler;

import java.util.Locale;
import java.util.Scanner;

public class Console {
	public static void printf(String format, Object... args) {
		System.out.printf(Locale.US, format, args);
	}

	public static void print(String format, Object... args) {
		printf(format, args);
	}

	public static void println(String format, Object... args) {
		print(format, args);
		println();
	}

	public static String input(String prompt) {
		printf("%s ", prompt);
		return input();
	}

	public static void print(Object x) {
		System.out.print(x);
	}

	public static void println(Object x) {
		System.out.println(x);
	}

	public static void println() {
		System.out.println();
	}

	public static String input() {
		return new Scanner(System.in).nextLine();
	}

	public static Double double$(String x) {
		println(x);
		return Double.parseDouble(x);
	}
}
