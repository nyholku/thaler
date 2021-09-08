
import static apackage.Klass.*;
import static thaler.Console.*;
import static thaler.List.*;

import thaler.*;
import static thaler.Dict.*;
import static thaler.Math.*;
import static thaler.Range.*;
import static thaler.Slice.*;
import static thaler.Generator.*;

import static thaler.$.*;

//import thaler.List;

//import static thaler.Sequence.*;

// $() => slicing
// classname$() instantiation
// $$("strin") json parsin to create dictionaru
// Short hands required for
// list creation from objects, a string
// dictionary creation from objects, a string
// string as list of chars
// parse() => list or dict or sequence

// Ideas:
// Dict == NamedTuple ??
// USage of $: list creation $()

public class Main {
	// No static void main(String...args) necessary ;) 
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

		printf("Exit\n");
		System.exit(0);

		try (var $ = $(List$(Range$(10)))) {
			for (var i : $) {
				println(i + 2);
			}
		}

		for (var r : List$(Range$(10)))
			println(r + 2);
		System.exit(0);
		if (true) {
			var list2 = List$("[ 'a' , 'b', 'c' ]");
			var list3 = asList("abcdefghi");
			println(asString(list3));
			println(list3);
			del(list3, Slice$(1, null, 2));
			println(list3);

			println(List$("[4,5,6]"));
			println(Dict$("{ 'number' : 123 , 'array' : [1 , 2 , 3] , 'string' : 'abcdef' , 'subobj' : {'field':'value'}}"));

			//		for (var i : Slice$(100, 0, 100, 1))
			//			println(i);
		}
		var list = List$("a", "b", "c", "d", "e");
		var y = Klass$();
		print(y);
		var l2 = List$(1, "abc", 3.7);
		println(l2);
		if (equal(1, 1))
			println("equal!");
		var d2 = Dict$("p", 1, "q", 3.2, "r", "huuhaa");
		println(d2);
		var d3 = Dict$("p", 1, "q", 3.2, "r", List$("XML", "JPG"));
		println(d3);

		var x = sqrt(double$(input("Enter number")));
		println("You entered %f\n", x * x);

	}
}
