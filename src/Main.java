
import static apackage.Klass.*;
import static thaler.Console.*;
import static thaler.List.*;
import static thaler.Dict.*;
import static thaler.Math.*;
import static thaler.Slice.*;
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
	{
		printf("Hello world!\n");
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
		for (var i : list.slice(-1, null, -1))
			println(i);
		for (var i : list.slice(0, null, 1))
			println(i);
		var y = Klass$();
		print(y);
		var l = List$(1, "abc", 3.7);
		println(l);
		if (equal(1, 1))
			println("equal!");
		var d = Dict$("p", 1, "q", 3.2, "r", "huuhaa");
		println(d);
		var d2 = Dict$("p", 1, "q", 3.2, "r", List$("XML", "JPG"));
		println(d2);

		var x = sqrt(double$(input("Enter number")));
		println("You entered %f\n", x * x);

	}
}
