# thaler
Tongue in cheek project to see how far Java syntax can be pushed

Thaler was named after the mighty dollar sign which I think gets
used to all too little in Java world ;)


To gain access to all the hippy and trendy stuff in Thaler all you need to do is
```
import thaler.*;
import static thaler.$.*;
```
In thaler $ is pushed to go to places where no dollar sign has been before.

The main usage for $ is to instantiate objects, but there are other uses:

Instead of `new List()` you can write just `List$()`.

This is achieved with static helper functions in the main Thaler class name `$`

Following is Thaler by example:

##Hello World
```
import thaler.*;
import static thaler.$.*;

public class Main {{
		printf("Hello world!\n");
}}
```

This bit of magic is achieved by having Thaler to include an actual `static void main(String...args)` function
which uses reflection to instantiate Main class and thus execution of the initializer block of that class.

Not really worth it but people always say Java Hello World is so verbose...

Now that you know that `Name$` stands for instantiation following pieces are
pretty simple to comprehend, `Range` and `List` class both implement `Iterable<T>`
and `var` allows type inference to capture the actual Range/List type so that
we do not need to do casts here in order to use the specific class (Integer)
methods.

```
// Python like range
for (var i : Range$(3)) {
	println(i + 100);
	}
```

```
// Python like list
for (var i : List$(1, 2, 3)) {
	println(i + 200);
	}
```

```
// Python like list initialized from range
for (var i : List$(Range$(1, 2, 3))) {
	println(i + 300);
	}
```

String manipulation using slices is so cool in Python so Thaler attemps to offer that too

```
// Python style slice
var l = asList("ABCDEF");
println(l);
for (var i : l.slice(Slice$(3)))
	println(i);
```

```
// Three last elements of list as a slice and then turned into Java String
println(asString(l.slice(Slice$(-3))));
```


Generator are also so modern and in good taste so I wanted to offer them on the menu:

```
// Python style generator / co-routine 
var g = Generator$(0, ($) -> {
	int i = 0;
	while (true)
	$.yield(i++);
	});

for (var i : g) {
	println(i + 500);
	}
```

Above works but unfortunatel following leaks resourses:

```
for (var i : g) {
	println(i + 500);
	if (i>5)
		break;
	}

```

If you break out of the iterator loop you need to use the
resource initialisation is acquistion pattern that builds
on `AutoClosable`.

```
try (var $=$(g)) {
	for (var i:$) {
		println(i + 600);
		if (i > 4)
			break;
			}
		}
```
Here `$` is nothing but a local variable that catches the auto closable
wrapper created by the `$()` function which wrapper implements Iterables
and thus makes the for loop  and type inference work.


