package com.pet.anna.app;

import com.pet.anna.app.model.MathNeuralNetwork;

import java.util.Arrays;

//@SpringBootApplication
public class AnnaApplication {

	public static void main(String[] args) {
		//SpringApplication.run(AnnaApplication.class, args);
		MathNeuralNetwork network = new MathNeuralNetwork(4, 2, 3, 4);
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
