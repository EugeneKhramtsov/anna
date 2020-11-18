package com.pet.anna.app.model;

public class NeuralNetwork {

    private Layer[] layers;

    public void train(double[] input, double[] target, double trainSpeed, int numberOfCycles) {
        for (int i = 0; i < numberOfCycles; i++) {
            calculate(input);
            calculateError(target);
            updateWeights(trainSpeed);
        }
    }

    public double[] calculate(double[] input) {
        for (Layer layer : layers) {
            layer.calculate();
        }
        return null;
    }

    private void calculateError(double[] target) {

    }

    private void updateWeights(double eta) {

    }
}