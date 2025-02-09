package com.web.web_app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebAppApplication.class, args);
		String data = "asd";

		// Singleton single = getInstance(data);
		long startTime = System.nanoTime();

		Singleton single = Singleton.getInstance(data);
		long stopTime = System.nanoTime();

		System.out.println("Time in nanosecs " + (stopTime - startTime));

		List<Object> list = new ArrayList<Object>();
		list.add(1);
		list.add(2);
		list.add("a");
		list.add("b");

		List<Integer> intList = new ArrayList<Integer>();
		System.out.println(list.get(0) instanceof Integer);
		// single.printData(10);
	}

}
