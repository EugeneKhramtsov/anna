package com.pet.anna.app.model;

import static com.pet.anna.app.tools.RandomUtils.createRandomArray;

public class Neuron {

    private double value;
    private Layer prevLayer;
    private double[] weights;

    private Neuron(double value, Layer prevLayer) {
        this.value = value;
        this.prevLayer = prevLayer;
        this.weights = createRandomArray(prevLayer.size(), -1, 1);
    }

    public static Neuron create(double value, Layer prevLayer) {
        return new Neuron(value, prevLayer);
    }

    public static Neuron input(double value) {
        return new Neuron(value, null);
    }

    public void calculate() {
        value = sigmoid(sum());
    }

    private double sum() {
        return 0;
    }

    private double sigmoid(double x) {
        return 0;
    }

    private boolean isInputLayer() {
        return prevLayer == null;
    }
}
