package main.entities.brain;

import java.util.ArrayList;

public class Brain {
	private Neuron[] inputNeurons;
	private Neuron[] outputNeurons;

	/**
	 * Constructor for the brain.
	 *
	 * @param numberOfInputNeurons  the number of input neurons that should be
	 *                              created
	 * @param numberOfOutputNeurons the number of output neurons that should be
	 *                              created
	 */
	public Brain(int numberOfInputNeurons, int numberOfOutputNeurons) {
		inputNeurons = new Neuron[numberOfInputNeurons];
		outputNeurons = new Neuron[numberOfOutputNeurons];

		for (int i = 0; i < inputNeurons.length; ++i) {
			inputNeurons[i] = new Neuron();
		}
		for (int i = 0; i < outputNeurons.length; ++i) {
			outputNeurons[i] = new Neuron();
		}
	}

	/**
	 * Connects an input and output neuron
	 *
	 * @param inputNeuronIndex  the index of the input neuron
	 * @param outputNeuronIndex the index of the output neuron
	 */
	public void addAxon(int inputNeuronIndex, int outputNeuronIndex) {
		inputNeurons[inputNeuronIndex].addOutputNeuronIndex(outputNeuronIndex);
	}

	/**
	 * Removes a connection between an input and output neuron
	 *
	 * @param inputNeuronIndex  the index of the input neuron
	 * @param outputNeuronIndex the index of the output neuron
	 */
	public void removeAxon(int inputNeuronIndex, int outputNeuronIndex) {
		inputNeurons[inputNeuronIndex].removeOutputNeuronIndex(outputNeuronIndex);
	}

	/**
	 * Sets input neuron values and updates output accordingly
	 *
	 * @param input an array of input values that overwrites old input neuron values
	 */
	public void update(double[] input) {
		for (int i = 0; i < inputNeurons.length; ++i) {
			double deltaValue = inputNeurons[i].setValue(input[i]);
			if (deltaValue != 0) {
				ArrayList<Integer> outputNeuronIndexes = inputNeurons[i].getOutputNeuronIndexes();
				for (int j = 0; j < inputNeurons[i].getOutputNeuronIndexes().size(); ++j) {
					outputNeurons[outputNeuronIndexes.get(j)].setValue(deltaValue);
				}
			}
		}
	}

	/**
	 * Returns the values of the output neurons
	 *
	 * @return an array of the values contained by all of the output neurons
	 */
	public int[] getOutput() {
		int[] outputValues = new int[outputNeurons.length];
		for (int i = 0; i < outputNeurons.length; ++i) {
			outputValues[i] = (int) Math.round(outputNeurons[i].getValue());
		}
		return null;
	}

	public Neuron[] getInputNeurons() {
		return inputNeurons;
	}

	public Neuron[] getOutputNeurons() {
		return outputNeurons;
	}
}
