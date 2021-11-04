# Functional-Programming-with-Java-Streams-API

### Table of Contents

| No. | Questions |
| --- | --------- |
|1  | [What is Stream?](#what-is-stream)|
|2  | [Stream vs Collection](#Stream-vs-Collection)|
|3  | [Creating Streams](#Creating-Streams)|
|4  | [Stream Collectors](#Stream-Collectors)|
|5  | [Stream Operations](#Stream-Operations)|
|6  | [Stream Short circuit Operations](#Stream-Short-circuit-Operations)|
|7  | [Parallel Stream](#Parallel-Stream)|
|8  | [Stream Operations Summary](#Stream-Operations-Summary)|

1. ### What is Stream?

All of us have watched online videos on YouTube. When we start watching a video, a small portion of the video file is
first loaded into our computer and starts playing. we don’t need to download the complete video before we start watching
it. This is called video streaming.

At a very high level, we can think of that small portions of the video file as a stream and the whole video as a
Collection.

Stream operations can either be executed sequentially or parallel. when performed parallel, it is called a parallel
stream.

**[⬆ Back to Top](#table-of-contents)**

2. ### Stream vs Collection

A Collection is an in-memory data structure, which holds all the values that the data structure currently has.

A Stream is conceptually a pipeline, in which elements are computed on demand.

**[⬆ Back to Top](#table-of-contents)**

3. ### Creating Streams

The given below ways are the most popular different ways to build streams from collections.

`Stream.of()` are creating a stream of a fixed number of integers.

```java
public class StreamBuilders {
    public static void main(String[] args) {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        stream.forEach(p -> System.out.println(p));
    }
}
```

`Stream.of(array)` are creating a stream from the array. The elements in the stream are taken from the array.

```java
public class StreamBuilders {
    public static void main(String[] args) {
        Stream<Integer> stream = Stream.of(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        stream.forEach(p -> System.out.println(p));
    }
}
```

` List.stream()` are creating a stream from the List. The elements in the stream are taken from the List.

```java
public class StreamBuilders {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();

        for (int i = 1; i < 10; i++) {
            list.add(i);
        }

        Stream<Integer> stream = list.stream();
        stream.forEach(p -> System.out.println(p));
    }
}
```

**[⬆ Back to Top](#table-of-contents)**

`Stream.generate() or Stream.iterate()` are creating a stream from generated elements.

```java
public class StreamBuilders {
    public static void main(String[] args) {
        Stream<Integer> randomNumbers = Stream
                .generate(() -> (new Random()).nextInt(100));

        randomNumbers.limit(20)
                .forEach(System.out::println);
    }
}
```

**[⬆ Back to Top](#table-of-contents)**

`Stream of String chars or tokens` are creating a stream from the characters of a given string. In the second part, we
are creating the stream of tokens received from splitting from a string.

```java
public class StreamBuilders {
    public static void main(String[] args) {
        IntStream stream = "12345_abcdefg".chars();
        stream.forEach(p -> System.out.println(p));

        //OR

        Stream<String> stream = Stream.of("A$B$C".split("\\$"));
        stream.forEach(p -> System.out.println(p));
    }
}
```

**[⬆ Back to Top](#table-of-contents)**

4. ### Stream Collectors

`Collect Stream elements to a List`  are creating a stream on integers 1 to 10. Then we are processing the stream
elements to find all even numbers.

```java
public class StreamBuilders {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();

        for (int i = 1; i < 10; i++) {
            list.add(i);
        }

        Stream<Integer> stream = list.stream();
        List<Integer> evenNumbersList = stream.filter(i -> i % 2 == 0)
                .collect(Collectors.toList());
        System.out.print(evenNumbersList);
    }
}
```

`Collect Stream elements to an Array` The given example is similar to the first example shown above. The only difference
is that we are collecting the even numbers in an Array.

```java
public class StreamBuilders {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();

        for (int i = 1; i < 10; i++) {
            list.add(i);
        }

        Stream<Integer> stream = list.stream();
        Integer[] evenNumbersArr = stream.filter(i -> i % 2 == 0).toArray(Integer[]::new);
        System.out.print(evenNumbersArr);
    }
}
```

There are plenty of other ways also to collect stream into a Set, Map or into multiple ways. Just go through Collectors
class and try to keep them in mind.

**[⬆ Back to Top](#table-of-contents)**

5. ### Stream Operations

> Intermediate Operations : Intermediate operations return the stream itself so you can chain multiple methods calls in a row.

`Stream.filter()` The filter() method accepts a Predicate to filter all elements of the stream. This operation is
intermediate which enables us to call another stream operation (e.g. forEach()) on the result.

```text
memberNames.stream()
           .filter((s) -> s.startsWith("A"))
           .forEach(System.out::println);

Output:
A
Aa
Abc   
```

`Stream.map()` The map() intermediate operation converts each element in the stream into another object via the given
function.

```text
memberNames.stream().filter((s) -> s.startsWith("A"))
                    .map(String::toUpperCase)
                    .forEach(System.out::println);
                    
Output:
A
AA
ABC   
```

`Stream.sorted()` The sorted() method is an intermediate operation that returns a sorted view of the stream.

```text
memberNames.stream().sorted()
                    .map(String::toUpperCase)
                    .forEach(System.out::println);

Output:
A
AA
ABC                
```

Please note that the sorted() method only creates a sorted view of the stream without manipulating the ordering of the
source Collection.

> Terminal operations : Terminal operations return a result of a certain type after processing all the stream elements. Once the terminal operation is invoked on a Stream, the iteration of the Stream and any of the chained streams will get started. Once the iteration is done, the result of the terminal operation is returned.

`Stream.forEach()` The forEach() method helps in iterating over all elements of a stream and perform some operation on
each of them. The operation to be performed is passed as the lambda expression.

```text
memberNames.forEach(System.out::println);
```

`Stream.collect()` The collect() method is used to receive elements from a steam and store them in a collection.

```text
List<String> memNamesInUppercase = memberNames.stream().sorted()
                            .map(String::toUpperCase)
                            .collect(Collectors.toList());
         
System.out.print(memNamesInUppercase);

Output: [A, AA, ABC]
```

`Stream.match()` Various matching operations can be used to check whether a given predicate matches the stream elements.
All of these matching operations are terminal and return a boolean result.

```java
public class AllStreamOperations {
    public static void main(String[] args) {
        List<String> listOfCountries = Arrays.asList("India", "Indonesia", "Nepal", "Afghanistan");

        boolean matchedResult = listOfCountries.stream()
                .anyMatch((s) -> s.startsWith("A"));

        System.out.println(matchedResult); // true

        matchedResult = listOfCountries.stream()
                .allMatch((s) -> s.startsWith("A"));

        System.out.println(matchedResult); // false

        matchedResult = listOfCountries.stream()
                .noneMatch((s) -> s.startsWith("A"));

        System.out.println(matchedResult); // false
    }
}
```

`Stream.count()` The count() is a terminal operation returning the number of elements in the stream as a long value.

```text
long totalMatched = memberNames.stream()
    .filter((s) -> s.startsWith("A"))
    .count();
 
System.out.println(totalMatched);
```

`Stream.reduce()` The reduce() method performs a reduction on the elements of the stream with the given function. The
result is an Optional holding the reduced value.

In the given example, we are reducing all the strings by concatenating them using a separator #.

```java
public class AllStreamOperations {
    public static void main(String[] args) {
        List<String> listOfCountries_1 = Arrays.asList("India", "Indonesia", "Nepal", "Afghanistan");

        Optional<String> reduced = listOfCountries_1.stream()
                .reduce((s1, s2) -> s1 + "#" + s2);

        reduced.ifPresent(System.out::println); // India#Indonesia#Nepal#Afghanistan
    }
}
```

6. ### Stream Short circuit Operations

Though stream operations are performed on all elements inside a collection satisfying a Predicate, it is often desired
to break the operation whenever a matching element is encountered during iteration.

In external iteration, we will do with the if-else block. In the internal iterations such as in streams, there are
certain methods we can use for this purpose.

`Stream.anyMatch()` The anyMatch() will return true once a condition passed as predicate satisfies. Once a matching
value is found, no more elements will be processed in the stream.

In the given example, as soon as a String is found starting with the letter 'A', the stream will end and the result will
be returned.

```text
boolean matched = memberNames.stream()
        .anyMatch((s) -> s.startsWith("A"));
 
System.out.println(matched); // true 
```

`Stream.findFirst()` The findFirst() method will return the first element from the stream and then it will not process
any more elements.

```text
String firstMatchedName = memberNames.stream()
            .filter((s) -> s.startsWith("L"))
            .findFirst()
                        .get();
 
System.out.println(firstMatchedName); // LoL
```

7. ### Parallel Stream

To enable parallelism, all we have to do is to create a parallel stream, instead of a sequential stream. And to
surprise, this is really very easy.

In any of the above-listed stream examples, anytime we want to do a particular job using multiple threads in parallel
cores, all we have to call parallelStream() method instead of stream() method.

```java
public class AllStreamOperations {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i < 10; i++) {
            list.add(i);
        }

        //Here creating a parallel stream
        Stream<Integer> stream = list.parallelStream();

        Integer[] evenNumbersArr = stream.filter(i -> i % 2 == 0).toArray(Integer[]::new);
        System.out.print(evenNumbersArr);
    }
}
```

8. ### Stream Operations Summary

`Creating Streams`

1. concat()
2. empty()
3. generate()
4. iterate()
5. of()

`Intermediate Operations`

1. filter()
2. map()
3. flatMap()
4. distinct()
5. sorted()
6. peek()
7. limit()
8. skip()

`Terminal Operations`

1. forEach()
2. forEachOrdered()
3. toArray()
4. reduce()
5. collect()
6. min()
7. max()
8. count()
9. anyMatch()
10. allMatch()
11. noneMatch()
12. findFirst()
13. findAny()

