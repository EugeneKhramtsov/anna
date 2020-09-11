package com.pet.anna.app;

import com.pet.anna.app.model.NeuralNetwork;
import com.pet.anna.app.tools.RandomUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;

//@SpringBootApplication
public class AnnaApplication {

	public static void main(String[] args) {
		//SpringApplication.run(AnnaApplication.class, args);
		NeuralNetwork network = new NeuralNetwork(4, 2, 3, 4);
		double[] input = {0.1, 20.2, 0.3, 0.4};
		double[] target = {0, 0, 0, 1};
		for (int i = 0; i < 10000; i++) {
			network.train(input, target, 0.7);
		}
		System.out.println(network.toString());
		System.out.println("input: " + Arrays.toString(input));
		System.out.println("target: " + Arrays.toString(target));
		System.out.println("result: " + Arrays.toString(network.getResult()));
	}
}
