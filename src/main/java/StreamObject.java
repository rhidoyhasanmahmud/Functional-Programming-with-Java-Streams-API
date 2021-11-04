import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamObject {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 5, 4, 3);

        /*
        Both the ways mentioned above provide us the stream object.
        1. Either we call the stream() method over the collection object,
        or
        2. we pass the values directly to the Stream.of() method.
         */
        Stream<Integer> numberStream = numbers.stream();
        Stream<Integer> newNumberStream = Stream.of(1, 2, 3, 4, 5);

        // map() -> map() method is used to perform an operation over the collection list.

        List<Integer> cubedNumbers = numbers.stream().map(num -> num * num * num).collect(Collectors.toList());
        System.out.println(cubedNumbers); // [1, 8, 125, 64, 27]

        // filter() -> filter() method used to filter out the results as per our conditions.

        List<Integer> evenNumbers = numbers.stream().filter(x -> x % 2 == 0).collect(Collectors.toList());
        System.out.println(evenNumbers); // [2, 4]

        // sorted() -> Now if you wish to sort the given collection, Streams at your service

        List<Integer> sortedList = numbers.stream().sorted().collect(Collectors.toList());
        System.out.println(sortedList); // [1, 2, 3, 4, 5]

        // flatMap() -> flatMap() is used to convert 2 levels of stream into 1 level of stream.

        List<Integer> list1 = Arrays.asList(1, 2, 3); // [1, 2, 3]
        List<Integer> list2 = Arrays.asList(4, 5, 6); // [4, 5, 6]
        List<Integer> list3 = Arrays.asList(7, 8, 9); // [7, 8, 9]

        List<List<Integer>> listOfNumbers = Arrays.asList(list1, list2, list3);
        System.out.println(listOfNumbers); // [[1, 2, 3], [4, 5, 6], [7, 8, 9]]

        List<Integer> listOfAllIntegers = listOfNumbers.stream().flatMap(x -> x.stream()).collect(Collectors.toList());
        System.out.println(listOfAllIntegers); // [1, 2, 3, 4, 5, 6, 7, 8, 9]

        // distinct() -> If the given collection has duplicate items, we can use the distinct() method to remove the
        // duplicate items and have a filtered collection.

        List<String> countries = Arrays.asList("India", "Australia", "SriLanka", "Russia", "Australia", "SriLanka", "India");

        List<String> distinctCountries = countries.stream().distinct().collect(Collectors.toList());
        System.out.println(distinctCountries); // [India, Australia, SriLanka, Russia]

        // peek() -> peek() is an intermediate action. peek() returns a stream consisting of the elements of the
        // traversed stream, additionally performing the provided action on each element as elements are consumed
        // from the resulting stream.

        numbers.stream()
                .filter(x -> x % 2 == 0)
                .peek(e -> System.out.println("The Even Number is " + e))
                .collect(Collectors.toList());
        /*
        The Even Number is 2
        The Even Number is 4
        */

        // collect() -> collect() operation helps to collect the stream into a collection. This method takes a
        // Collector implementation that provides useful reduction operations.

        List<String> listOfCountries = Arrays.asList("India", "Indonesia", "Nepal", "Afghanistan");
        List<String> output = listOfCountries.stream().filter(x -> x.toString().startsWith("I")).collect(Collectors.toList());
        System.out.println(output); // [India, Indonesia]

        // count() -> count() terminal operation helps us find the count of the processed collection.

        Long totalNumberOfCountry = listOfCountries.stream().filter(x -> x.toString().startsWith("A")).count();
        System.out.println(totalNumberOfCountry); // 1

        // allMatch() -> allMatch() operation helps us to get the answer: Do all the elements of the stream
        // match the condition?

        Boolean areAllElementsStartingWithI = listOfCountries.stream().allMatch(x -> x.toString().startsWith("I"));
        System.out.println(areAllElementsStartingWithI); // false

        // anyMatch() -> As compared to the allMatch() anyMatch() helps to check if either one of the elements
        // in the stream matched the condition.

        Boolean areAllElementsStartingWithA = listOfCountries.stream().anyMatch(x -> x.toString().startsWith("A"));
        System.out.println(areAllElementsStartingWithA); // true

        // forEach() -> forEach() method is used to iterate over the resultant collection, same as that of the traditional for loop.

        listOfCountries.stream()
                .filter(x -> x.toString().startsWith("A"))
                .forEach(System.out::println);
        // Afghanistan


    }
}
