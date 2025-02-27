package com.test.test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication
public class TestApplication {

	public static void main(String[] args) {
		// SpringApplication.run(TestApplication.class, args);
		// List<String> list = IntStream.range(1, 26).mapToObj(num -> {
		// if (num % 3 == 0 && num % 5 == 0)
		// return "FizzBuzz";
		// else if (num % 3 == 0)
		// return "Fizz";
		// else if (num % 5 == 0)
		// return "Buzz";
		// else
		// return num;
		// }).map(line -> line.toString()).collect(Collectors.toList());

		// list.forEach(item -> System.out.println(item));
		// System.out.println(list instanceof List<String>);

		List<String> list2 = new ArrayList<>();
		for (int i = 1; i <= 25; i++) {
			if (i % 3 == 0 && i % 5 == 0)
				list2.add("FizzBuzz");
			else if (i % 3 == 0)
				list2.add("Fizz");

			else if (i % 5 == 0)
				list2.add("Buzz");

			else
				list2.add(String.valueOf(i));

		}
		list2.forEach(i -> System.out.println(i));

	}

}
