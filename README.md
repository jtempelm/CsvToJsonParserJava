## Assignment
Write a program that takes a CSV string and transforms it into multiple json objects.

The first row of the CSV string contains the headers.

Example input

 ```
 a,b,c,attr_foo,attr_bar
 x,y,z,TRUE,FALSE
 x2,y2,z2,FALSE,TRUE
 x3,y3,z3,TRUE,TRUE
 ```

row 1 converts to

 ```JSON Node example
   {
     "a": "x",
     "b": "y",
     "c": "z",
     "attrs": [
       "foo"
     ]
   }
 ```

row 2 converts to
 ```
   {
     "a": "x2",
     "b": "y2",
     "c": "z2",
     "attrs": [
       "bar"
     ]
   }
 ```

row 3 converts to
 ```
   {
     "a": "x3",
     "b": "y3",
     "c": "z3",
     "attrs": [
       "foo", "bar"
     ]
   }
 ```

Notes -
* The headers are fully dynamic.
* For the CSV, you don't have to use a file just the text, you may use a multi line string.
* Focus your attention on the `attr_` headers and how they're tags that convert into a list.
* There is only one `attr_` prefix and you can assume it is always `attr_`.
* Quick and dirty is fine.
* You do not have to talk through the problem but you can if you want to.
* You are encouraged to use built in libraries as much as possible.
