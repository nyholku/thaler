package thaler;

public class Loader {
	public static void main(String... args) {
		try {
			Loader.class.getClassLoader().loadClass("Main").getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
