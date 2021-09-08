
import thaler.*;
import static thaler.$.*;

public class Main {
	{
		printf("Hello world!\n");

		// Python like range
		for (var i : Range$(3)) {
			println(i + 100);
		}

		// Python like list
		for (var i : List$(1, 2, 3)) {
			println(i + 200);
		}

		// Python like list initialized from range
		for (var i : List$(Range$(1, 2, 3))) {
			println(i + 300);
		}

		// Python style slice
		var l = asList("ABCDEF");
		println(l);
		for (var i : l.slice(Slice$(3)))
			println(i);

		// Three last elements of list as a slice and then turned into Java String
		println(asString(l.slice(Slice$(-3))));

		// Python style generator / co-routine 
		var g = Generator$(0, ($) -> {
			int i = 0;
			while (true)
				$.yield(i++);
		});

		for (var i : g) {
			println(i + 500);
			if (i > 4)
				break;
		}

		// Python style dictionary initialised with JSON string, note no quoting of quotes as usually needed in Java
		Dict d = Dict$("{ 'number' : 123 , 'array' : [1 , 2 , 3] , 'string' : 'abcdef' , 'subobj' : {'field':'value'}}");
		// Output as JSON
		println(d);

		println(d.get("array"));
		println(d.get("subobj"));

		// delete slice of list
		var list3 = asList("abcdefghi");
		println(list3);
		del(list3, Slice$(1, null, 2)); 
		println(list3);

		// check if two numbers of any primitive or boxer class including BigInteger/BigDecimal are numerically equal
		if (equal(1, 1))
			println("equal!");

		// input a number from console, do some math with it and print it out
		var x = sqrt(double$(input("Enter number")));
		println("You entered %f\n", x * x);

		printf("Done\n");
	}
}
