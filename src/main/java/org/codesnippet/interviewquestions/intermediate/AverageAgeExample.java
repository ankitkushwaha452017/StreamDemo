package org.codesnippet.interviewquestions.intermediate;

import org.codesnippet.interviewquestions.intermediate.dto.Person;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AverageAgeExample {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
            new Person("Alice", 25),
            new Person("Bob", 30),
            new Person("Charlie", 28),
            new Person("David", 35)
        );

        // Approach 1: mapToInt() + average() [MOST DIRECT]
        System.out.println("=== Approach 1: mapToInt() + average() ===");
        double avg1 = people.stream()
                .mapToInt(Person::getAge)
                .average()
                .orElse(0.0);
        System.out.println("Average Age: " + avg1);

        // Approach 2: map() + reduce() [WITHOUT mapToInt]
        System.out.println("\n=== Approach 2: map() + reduce() ===");
        double avg2 = people.stream()
                .map(Person::getAge)
                .reduce(0, (sum, age) -> sum + age)
                / (double) people.size();
        System.out.println("Average Age: " + avg2);

        // Approach 3: mapToDouble() + average()
        System.out.println("\n=== Approach 3: mapToDouble() + average() ===");
        double avg3 = people.stream()
                .map(Person::getAge)
                .mapToDouble(age -> age)
                .average()
                .orElse(0.0);
        System.out.println("Average Age: " + avg3);

        // Approach 4: Collectors.averagingInt() [COLLECTOR APPROACH - IMPORTANT FOR INTERVIEWS]
        System.out.println("\n=== Approach 4: Collectors.averagingInt() ===");
        double avg4 = people.stream()
                .collect(Collectors.averagingInt(Person::getAge));
        System.out.println("Average Age: " + avg4);

        // Approach 5: Collectors.summarizingInt() [GET MULTIPLE STATISTICS]
        System.out.println("\n=== Approach 5: Collectors.summarizingInt() ===");
        var stats = people.stream()
                .collect(Collectors.summarizingInt(Person::getAge));
        System.out.println("Count: " + stats.getCount());
        System.out.println("Sum: " + stats.getSum());
        System.out.println("Min: " + stats.getMin());
        System.out.println("Max: " + stats.getMax());
        System.out.println("Average: " + stats.getAverage());
    }
}
