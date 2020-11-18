package com.pet.anna.app.model;

import com.pet.anna.app.tools.RandomUtils;

/**
 * @author Yevhenii.Khramtsov
 */

public class MathNeuralNetwork {

    private final int[] layerSizes;

    private final int networkSize;

    private final double[][] output;

    private final double[][][] weights;

    private final double[][] bias;

    private final double[][] error;

    private final double[][] outputDerivative;

    public MathNeuralNetwork(int... layerSizes) {
        this.layerSizes = layerSizes;
        this.networkSize = layerSizes.length;

        this.output = new double[networkSize][];
        this.error = new double[networkSize][];
        this.outputDerivative = new double[networkSize][];
        for (int i = 0; i < networkSize; i++) {
            this.output[i] = new double[layerSizes[i]];
            this.error[i] = new double[layerSizes[i]];
            this.outputDerivative[i] = new double[layerSizes[i]];
        }

        this.weights = new double[networkSize][][];
        this.bias = new double[networkSize][];
        for (int i = 1; i < networkSize; i++) {
            this.weights[i] = RandomUtils.createRandomArray(layerSizes[i], layerSizes[i - 1], -1, 1);
            this.bias[i] = RandomUtils.createRandomArray(layerSizes[i], -1, 1);
        }
    }

    public double[] getResult() {
        return output[networkSize - 1];
    }

    public void calculate(double[] input) {
        output[0] = input;
        for (int layer = 1; layer < networkSize; layer++) {
            for (int neuron = 0; neuron < layerSizes[layer]; neuron++) {
                output[layer][neuron] = sigmoid(sum(layer, neuron));
                outputDerivative[layer][neuron] = output[layer][neuron] * (1 - output[layer][neuron]);
            }
        }
    }

    public double sum(int layer, int neuron) {
        if (layer <= 0) {
            throw new IllegalArgumentException("Can not calculate sum for layer less that 1");
        }
        double sum = 0;

        for (int k = 0; k < output[layer - 1].length; k++) {
            sum += weights[layer][neuron][k] * output[layer - 1][k];
        }
        sum += bias[layer][neuron];

        return sum;
    }

    public double sigmoid(double value) {
        return 1 / (1 + Math.exp(-value));
    }

    public void train(double[] input, double[] target, double eta) {
        calculate(input);
        calculateError(target);
        updateWeights(eta);
    }

    public void calculateError(double[] target) {
        for (int neuron = 0; neuron < layerSizes[networkSize - 1]; neuron++) {
            error[networkSize - 1][neuron] =
                    (output[networkSize - 1][neuron] - target[neuron]) * outputDerivative[networkSize - 1][neuron];
        }
        for (int layer = networkSize - 2; layer > 0; layer--) {
            for (int neuron = 0; neuron < layerSizes[layer]; neuron++) {
                double sum = 0;
                for (int nextNeuron = 0; nextNeuron < layerSizes[layer + 1]; nextNeuron++) {
                    sum += weights[layer + 1][nextNeuron][neuron] * error[layer + 1][nextNeuron];
                }
                error[layer][neuron] = sum * outputDerivative[layer][neuron];
            }
        }
    }

    public void updateWeights(double eta) {
        for (int layer = 1; layer < networkSize; layer++) {
            for (int neuron = 0; neuron < layerSizes[layer]; neuron++) {
                for (int prevNeuron = 0; prevNeuron < layerSizes[layer - 1]; prevNeuron++) {
                    weights[layer][neuron][prevNeuron] += -eta * output[layer - 1][prevNeuron] * error[layer][neuron];
                }
                bias[layer][neuron] += -eta * error[layer][neuron];
            }
        }
    }

    @Override
    public String toString() {
        final StringBuilder result = new StringBuilder();
        for (int i = 0; i < networkSize; i++) {
            result.append(String.format("-%d-", i));
        }
        result.append("\n");
        final int maxHeight = 4;
        for (int j = 0; j < maxHeight; j++) {
            for (int i = 0; i < networkSize; i++) {
                if (output[i].length > j) {
                    result.append(String.format("[%d]", j));
                } else {
                    result.append("   ");
                }
            }
            result.append("\n");
        }
        return result.toString();
    }
}