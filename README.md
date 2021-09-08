# thaler
Tongue in cheek plapen to see how far Java syntax can be pushed

Thaler was named after the mighty dollar sign which I think gets
used to all too little in Java world ;)

In thaler $ is pushed to go to places where no dollar sign has been before.

The main usage is to instantiate objects.

Instead of `new List()` you can write just `List$()`.

This is achieved with static helpe functions in the main Thaler class name `$`

In fact to gain access to all the hippy and trendy stuff in Thaler all you need to
do is
```
import thaler.*;
import static thaler.$.*;
```
