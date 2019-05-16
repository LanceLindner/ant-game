package main.entities.brain;

import java.util.ArrayList;

public class Brain {
	private Neuron[] inputNeurons;
	private Neuron[] outputNeurons;

	public ArrayList<Neuron> activeInput = new ArrayList<Neuron>();

	public Brain(int numberOfInputNeurons, int numberOfOutputNeurons) {
		inputNeurons = new Neuron[numberOfInputNeurons];
		outputNeurons = new Neuron[numberOfOutputNeurons];

		for (int i = 0; i < inputNeurons.length; ++i) {
			inputNeurons[i] = new Neuron(i, 0);
		}
		for (int i = 0; i < outputNeurons.length; ++i) {
			outputNeurons[i] = new Neuron(i, 0);
		}
	}

	@Override
	public Brain clone() {
		Brain newBrain = new Brain(inputNeurons.length, outputNeurons.length);

		for (int i = 0; i < inputNeurons.length; ++i) {
			newBrain.getInputNeurons()[i].setX(inputNeurons[i];
		}
		for (int i = 0; i < outputNeurons.length; ++i) {
			outputNeurons[i] = new Neuron(i, 0);
		}

		return newBrain;
	}

	public void setInput(double[] input) {
		for (int i = 0; i < inputNeurons.length; ++i) {
			inputNeurons[i].setValue(input[i]);
		}
	}

	public int[] getOutput() {
		int[] outputValues = new int[outputNeurons.length];
		for (int i = 0; i < outputNeurons.length; ++i) {
			outputValues[i] = (int) Math.round(outputNeurons[i].getValue());
		}
		return null;
	}
}
