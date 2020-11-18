package com.pet.anna.app.model;

import lombok.Data;

@Data
public class Layer {

    private Neuron[] neurons;

    private Layer(double[] input) {
        neurons = new Neuron[input.length];
        for (int i = 0; i < input.length; i++) {
            neurons[i] = Neuron.input(input[i]);
        }
    }

    private Layer(int size, Layer prevLayer) {
        neurons = new Neuron[size];
        for (int i = 0; i < size; i++) {
            neurons[i] = Neuron.create(0, prevLayer);
        }
    }

    public static Layer input(double[] input) {
        return new Layer(input);
    }

    public static Layer create(int size, Layer prevLayer) {
        return new Layer(size, prevLayer);
    }

    public void calculate() {
        for (Neuron neuron : neurons) {
            neuron.calculate();
        }
    }

    public int size() {
        return neurons.length;
    }
}