package main.entities.brain;

import java.util.ArrayList;

public class Brain {
	private Neuron[] inputNeurons;
	private Neuron[] outputNeurons;

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

	public void addAxon(int inputNeuronIndex, int outputNeuronIndex) {
		inputNeurons[inputNeuronIndex].addOutputNeuronIndex(outputNeuronIndex);
	}

	public void removeAxon(int inputNeuronIndex, int outputNeuronIndex) {
		inputNeurons[inputNeuronIndex].removeOutputNeuronIndex(outputNeuronIndex);
	}

	public void setInput(double[] input) {
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

	public int[] getOutput() {
		int[] outputValues = new int[outputNeurons.length];
		for (int i = 0; i < outputNeurons.length; ++i) {
			outputValues[i] = (int) Math.round(outputNeurons[i].getValue());
		}
		return null;
	}
}
