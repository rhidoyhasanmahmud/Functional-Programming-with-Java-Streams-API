# Functional-Programming-with-Java-Streams-API

### Table of Contents

| No. | Questions |
| --- | --------- |
|1  | [What is Stream?](#what-is-stream)|
|2  | [Stream vs Collection](#Stream-vs-Collection)|
|3  | [Creating Streams](#Creating-Streams)|
|4  | [Stream Collectors](#Stream-Collectors)|

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


