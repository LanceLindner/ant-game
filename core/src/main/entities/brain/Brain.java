package main.entities.brain;

import java.util.ArrayList;

import main.entities.ants.Neuron;

public class Brain {
	private Neuron[] inputNeurons;
	private Neuron[] hiddenNeurons;
	private Neuron[] outputNeurons;

	public ArrayList<Neuron> allNeurons = new ArrayList<Neuron>();
	public ArrayList<Neuron> activeInput = new ArrayList<Neuron>();

	public Brain(int numberOfInputNeurons, int numberOfHiddenNeurons, int numberOfOutputNeurons) {
		initializeEmptyBrain(numberOfInputNeurons, numberOfHiddenNeurons, numberOfOutputNeurons);
	}

	public void initializeEmptyBrain(int numberOfInputNeurons, int numberOfHiddenNeurons, int numberOfOutputNeurons) {
		inputNeurons = new Neuron[numberOfInputNeurons];
		hiddenNeurons = new Neuron[numberOfHiddenNeurons];
		outputNeurons = new Neuron[numberOfOutputNeurons];

		for (Neuron neuron : inputNeurons) {
			neuron = new Neuron();
			allNeurons.add(neuron);
		}
		for (Neuron neuron : hiddenNeurons) {
			neuron = new Neuron();
			allNeurons.add(neuron);
		}
		for (Neuron neuron : outputNeurons) {
			neuron = new Neuron();
			allNeurons.add(neuron);
		}
	}

	public void update(int[] input) {

	}

	public int[] getOutput() {
		int[] outputValues = new int[outputNeurons.length];
		for (int i = 0; i < outputNeurons.length; ++i) {
			outputValues[i] = (int) outputNeurons[i].getValue();
		}
		return null;
	}
}
